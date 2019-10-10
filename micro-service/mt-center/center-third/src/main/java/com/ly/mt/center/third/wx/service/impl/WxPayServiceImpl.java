package com.ly.mt.center.third.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.third.wx.entity.WxPayOrder;
import com.ly.mt.center.third.wx.service.WxPayService;
import com.ly.mt.core.base.dict.WxPayTradeType;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.feign.DataCenterMethod;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.center.third.wx.dict.WxPayErrorCode.WX_PAY_ERR_CODE_ORDER_NOT_EXIST;
import static com.ly.mt.center.third.wx.dict.WxPayResultCode.WX_PAY_RESULT_CODE_SUCCESS;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_RANDOM;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_FALL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.dict.WxPayTradeType.WX_PAY_TRADE_TYPE_JSAPI;
import static com.ly.mt.core.base.dict.WxPayTradeType.WX_PAY_TRADE_TYPE_MWEB;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.redis.RedisKey.REDIS_ENTITY_PAY_ORDER_WX;
import static com.ly.mt.core.redis.RedisKey.REDIS_USER_WX_OPENID;


/**
 * @Description 微信支付接口
 * @Author taoye
 */
@Service
public class WxPayServiceImpl extends BaseServiceImpl implements WxPayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxPayServiceImpl.class);
    @Resource
    YmlConfig yml;

    private static String access_token = "";
    public static long access_token_time_out = 0;

    private static String jsapi_ticket = "";
    private static long jsapi_ticket_time_out = 0;

    /**
     * @Description 支付状态查询
     * @Author taoye
     */
    @Override
    public ResponseJson status(JSONObject jsonObject) {
        try {
            // 参数封装
            TreeMap<String, Object> treeMap = new TreeMap<>();
            treeMap.put("appid", jsonObject.getString("appid"));
            treeMap.put("mch_id", yml.getWxPayMerchantId());
            treeMap.put("out_trade_no", jsonObject.getString("out_trade_no"));
            treeMap.put("nonce_str", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM));
            String sign = getWeChatSign(treeMap, yml.getWxPayMerchantKey());
            treeMap.put("sign", sign);
            String xml = XmlUtil.transMap2Xml(treeMap);
            // 调用微信接口
            String resultXml = restTemplatePost(yml.getWxPayApiOrderQuery(), xml);
            // 解析返回数据
            Map<String, Object> resultMap = XmlUtil.transXml2Map(resultXml);
            String returnCode = String.valueOf(resultMap.get("return_code"));
            if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(returnCode)) {
                LOGGER.error("wapPay调用微信支付接口失败,错误信息:{}", resultMap.get("return_msg"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            String resultCode = String.valueOf(resultMap.get("result_code"));
            if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
                String errCode = String.valueOf(resultMap.get("err_code"));
                LOGGER.error("wapPay调用微信支付接口失败,错误代码:{},错误描述:{}", resultMap.get("err_code"), resultMap.get("err_code_des"));
                if (WX_PAY_ERR_CODE_ORDER_NOT_EXIST.getCode().equals(errCode)) {
                    return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, TRADE_STATUS_PAY_FALL.getId());
                }
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 返回结果
            JSONObject result = new JSONObject();
            result.put("transaction_id", jsonObject.getString("transaction_id"));
            result.put("total_fee", jsonObject.getString("total_fee"));
            String trade_state = String.valueOf(resultMap.get("trade_state"));
            if (WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(trade_state)) {
                // 支付成功
                result.put("trade_status", TRADE_STATUS_PAY_SUCCESS.getId());
            } else {
                // 支付失败
                result.put("trade_status", TRADE_STATUS_PAY_FALL.getId());
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, result);
        } catch (Exception e) {
            LOGGER.error("查询微信支付状态接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 支付回调
     * @Author taoye
     */
    @Override
    public ResponseJson notify(JSONObject jsonObject) {
        try {
            Map<String, String> map = JSONObject.toJavaObject(jsonObject, Map.class);
            // 解析返回数据
            String return_code = map.get("return_code");
            if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(return_code)) {
                LOGGER.error("支付回调返回支付失败,错误信息:{}", map.get("return_msg"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            String result_code = map.get("result_code");
            if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(result_code)) {
                LOGGER.error("微信支付回调返回支付失败,错误代码={},错误信息={},", map.get("err_code"), map.get("err_code_des"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 验签
            LOGGER.info("wechat notify map:{}, signKey:{}", map, yml.getWxPayMerchantKey());
            if (!checkSign(map)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 获取系统订单
            String out_trade_no = map.get("out_trade_no");
            String orderJson = redisService.get(REDIS_ENTITY_PAY_ORDER_WX, out_trade_no);
            if (StringUtil.isEmpty(orderJson)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 校验金额
            WxPayOrder wxPayOrder = JSONObject.toJavaObject(JSONObject.parseObject(orderJson), WxPayOrder.class);
            // 金额单位转换 分—>元
            double total_amount = Double.valueOf(map.get("total_fee"));
            String total_fee = String.valueOf(total_amount / 100);
            if (!checkMoney(wxPayOrder.getTotal_fee(), total_fee)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("微信支付回调处理出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 发起微信支付-统一下单接口
     * @Author taoye
     */
    @Override
    public ResponseJson pay(JSONObject jsonObject) {
        try {
            // 入参
            WxPayOrder wxPayOrder = JSONObject.toJavaObject(jsonObject, WxPayOrder.class);
            String trade_type = wxPayOrder.getTrade_type();
            if (!WxPayTradeType.checkTradeType(trade_type)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—交易类型不存在");
            }
            String scene_info = wxPayOrder.getScene_info();
            if (WX_PAY_TRADE_TYPE_MWEB.getType().equals(trade_type) && StringUtil.isEmpty(scene_info)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—MWEB支付时scene_info不能为空");
            }
            // 缓存数据
            redisService.setEntity(REDIS_ENTITY_PAY_ORDER_WX, wxPayOrder.getOut_trade_no(), wxPayOrder, 24L, TimeUnit.HOURS);
            // 单位转换 元—>分
            int total_fee = (int) ((double) (Double.valueOf(wxPayOrder.getTotal_fee()) * 100));
            TreeMap<String, Object> treeMap = new TreeMap<>();
            treeMap.put("appid", wxPayOrder.getAppid());
            treeMap.put("mch_id", yml.getWxPayMerchantId());
            treeMap.put("nonce_str", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM));
            treeMap.put("body", wxPayOrder.getBody());
            if (StringUtil.isNotEmpty(wxPayOrder.getAttach())) {
                treeMap.put("attach", wxPayOrder.getAttach());
            }
            treeMap.put("out_trade_no", wxPayOrder.getOut_trade_no());
            treeMap.put("total_fee", total_fee);
            treeMap.put("spbill_create_ip", wxPayOrder.getIp());
            treeMap.put("notify_url", yml.getWxPayApiNotify());
            treeMap.put("trade_type", trade_type);
            //trade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识
            if (trade_type.equals(WX_PAY_TRADE_TYPE_JSAPI.getType())) {
                treeMap.put("openid", wxPayOrder.getOpenId());
            }
            treeMap.put("scene_info", scene_info);
            String sign = getWeChatSign(treeMap, yml.getWxPayMerchantKey());
            treeMap.put("sign", sign);
            String xml = XmlUtil.transMap2Xml(treeMap);
            LOGGER.info("微信支付请求参数", xml);
            // 调用微信接口
            String resultXml = restTemplatePost(yml.getWxPayApiUnifiedOrder(), xml);
            // 解析返回数据
            Map<String, Object> resultMap = XmlUtil.transXml2Map(resultXml);
            String returnCode = String.valueOf(resultMap.get("return_code"));
            if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(returnCode)) {
                LOGGER.error("wapPay调用微信支付接口失败,错误信息:{}", resultMap.get("return_msg"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            String resultCode = String.valueOf(resultMap.get("result_code"));
            if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
                LOGGER.error("wapPay调用微信支付接口失败,错误代码:{},错误描述:{}", resultMap.get("err_code"), resultMap.get("err_code_des"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 返回数据
            if (WX_PAY_TRADE_TYPE_MWEB.getType().equals(trade_type)) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultMap.get("mweb_url"));
            } else {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultMap.get("prepay_id"));
            }
        } catch (Exception e) {
            LOGGER.error("发起微信支付出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @Override
    public ResponseJson confirmAuthorization(JSONObject jsonObject) {
        LOGGER.info("================进入微信授权接口=========================");
        String code = jsonObject.getString("code");
        String userId = jsonObject.getString("userId");
        if (StringUtil.isEmpty(code)) {
            LOGGER.info("微信授权接口获取code值为空！");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        Map map = new ConcurrentHashMap(4);
        map.put("appid", yml.getWxPayAppId());
        map.put("secret", yml.getWxPayAppSecret());
        map.put("code", code);
        map.put("grant_type", "authorization_code");
        String returnJson = restTemplateGet(yml.getWxPayConfirmAccessUri(), map);
        LOGGER.info("微信授权接口返回值：" + returnJson);
        JSONObject json = JSONObject.parseObject(returnJson);
        String openId = json.getString("openid");
        String refreshToken = json.getString("refresh_token");
        LOGGER.info("获取用户：" + userId + "对应的openId:" + openId);
        //先把openId刷到缓存。供wx支付接口用
        redisService.set(REDIS_USER_WX_OPENID, userId, openId);
        //异步刷新用户的 openId 到数据库,并刷新用户 access_token
        updateUserOpenId(openId, refreshToken, userId);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    /*@Override
    public ResponseJson refund(JSONObject parameter) {
        byte[] cert = weChatConfig.readCert();
        TreeMap<String,Object> data = JSON.parseObject(parameter.toJSONString(),new TypeReference<TreeMap>(){});
        data.put("appid",yml.gtAppId);
        data.put("mch_id",yml.getWxPayMerchantId());
        data.put("nonce_str", UUID.randomUUID().toString());
        try {
            data.put("sign",MD5Util.md5(getWeChatSign(data,yml.getWxPayMerchantKey())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/


    @Override
    public ResponseJson refund(JSONObject jsonObject) {
        String refundId = jsonObject.getString("refundId");
        String orderNo = jsonObject.getString("orderNo");
        String refund_fee = jsonObject.getString("refundFee");
        String orderMoney = jsonObject.getString("orderMoney");
        // 单位转换 元—>分
        try {
            int totalMoney = (int) ((double) (Double.valueOf(orderMoney) * 100));
            int refundFee = (int) ((double) (Double.valueOf(refund_fee) * 100));
            TreeMap<String, Object> paraMap = new TreeMap<>();
            paraMap.put("appid", yml.getWxPayAppId());
            paraMap.put("mch_id", yml.getWxPayMerchantId());
            paraMap.put("nonce_str", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM));
            paraMap.put("out_trade_no", orderNo);
            paraMap.put("out_refund_no", refundId);
            paraMap.put("total_fee", totalMoney);
            paraMap.put("refund_fee", refundFee);
            String sign = getWeChatSign(paraMap, yml.getWxPayMerchantKey());
            paraMap.put("sign", sign);
            String xml = XmlUtil.transMap2Xml(paraMap);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            //指向你的证书的绝对路径，带着证书去访问
            FileInputStream instream = new FileInputStream(
                    new File("https://moti-dev.oss-cn-beijing.aliyuncs.com/image/wechat/apiclient_cert.p12"));//P12文件目录
            try {
                //下载证书时的密码、默认密码是你的MCHID mch_id
                keyStore.load(instream, yml.getWxPayMerchantId().toCharArray());//这里写密码
            } finally {
                instream.close();
            }
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, yml.getWxPayMerchantId().toCharArray()).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf).build();
            HttpPost httppost = new HttpPost(yml.getWxPayApiRefund());
            try {
                StringEntity se = new StringEntity(xml);
                httppost.setEntity(se);
                LOGGER.info("executing request" + httppost.getRequestLine());
                CloseableHttpResponse responseEntry = httpclient.execute(httppost);
                try {
                    HttpEntity entity = responseEntry.getEntity();
                    // System.out.println(responseEntry.getStatusLine());
                    if (entity != null) {
                        // 解析返回数据
                        Map<String, Object> resultMap = XmlUtil.transXml2Map(entity.getContent().toString());
                        String returnCode = String.valueOf(resultMap.get("return_code"));
                        if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(returnCode)) {
                            LOGGER.error("wapPay调用微信退款接口失败,错误信息:{}", resultMap.get("return_msg"));
                            return ResponseUtil.getResponseObj(RESULT_CODE_SYSTEM_ERROR, resultMap.get("return_msg"));
                        }
                        String resultCode = String.valueOf(resultMap.get("result_code"));
                        if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
                            LOGGER.error("wapPay调用微信退款接口失败,错误代码:{},错误描述:{}", resultMap.get("err_code"), resultMap.get("err_code_des"));
                            return ResponseUtil.getResponseObj(RESULT_CODE_SYSTEM_ERROR, resultMap.get("err_code_des"));
                        }
                        //退款成功 更改退款单状态
                        LOGGER.info("wapPay调用微信退款接口成功");
                        JSONObject updateJson = new JSONObject();
                        updateJson.put("id", refundId);
                        updateJson.put("refund_status", "1");
                        callDataCenter(DataCenterMethod.TRADE_ORDER_REFUND_UPDATE, updateJson);
                        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
                    } else {
                        return ResponseUtil.getResponseCode(RESULT_CODE_SYSTEM_ERROR);
                    }
                } finally {
                    responseEntry.close();
                }
            } finally {
                httpclient.close();
            }

        } catch (Exception e) {
            LOGGER.error("微信退款异常");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }


    @Override
    public ResponseJson withdrawal(JSONObject jsonObject) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            TreeMap<String, Object> param = new TreeMap<>();
            param.put("mch_appid", jsonObject.getString("appid")); //公众账号appid
            param.put("mchid", yml.getWxPayMerchantId()); //商户号
            param.put("nonce_str", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM)); //随机字符串
            param.put("partner_trade_no", jsonObject.getString("tradeNo")); //商户订单号
            param.put("openid", jsonObject.getString("openid")); //用户openid
            param.put("check_name", "NO_CHECK"); //校验用户姓名选项 OPTION_CHECK，不需要检验真实姓名
            //parm.put("re_user_name", "123"); //check_name设置为FORCE_CHECK或OPTION_CHECK，则必填
            //int amount = (int) ((double) (Double.valueOf(jsonObject.getString("amount")) * 100));
            int amount = Integer.valueOf(jsonObject.getString("amount"));
            param.put("amount", amount); //转账金额，单位为分
            param.put("desc", jsonObject.getString("desc")); //企业付款描述信息
            param.put("spbill_create_ip", jsonObject.getString("ip")); //Ip地址
            //param.put("spbill_create_ip", "39.96.190.37"); //Ip地址
            String sign = getWeChatSign(param, yml.getWxPayMerchantKey());
            param.put("sign", sign);
            String parmXml = XmlUtil.transMap2Xml(param);
            LOGGER.info("withdrawal微信申请提现请求参数 parmXml:" + parmXml);
            httpClient = loadKeyStory();
            HttpPost httppost = new HttpPost(yml.getTransfers());
            //这里要设置编码，不然xml中有中文的话会提示签名失败或者展示乱码
            httppost.addHeader("Content-Type", "text/xml");
            StringEntity se = new StringEntity(parmXml, "UTF-8");
            httppost.setEntity(se);
            response = httpClient.execute(httppost);
            String resultXml = EntityUtils.toString(response.getEntity(), "UTF-8");
            LOGGER.info("withdrawal微信申请提现返回值 resultXml:" + resultXml);
            // 解析返回数据
            Map<String, Object> resultMap = XmlUtil.transXml2Map(resultXml);
            String returnCode = String.valueOf(resultMap.get("return_code"));
            if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(returnCode)) {
                LOGGER.error("withdrawal调用微信提现接口失败,错误信息:{}", resultMap.get("return_msg"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            String resultCode = String.valueOf(resultMap.get("result_code"));
            if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
                LOGGER.error("withdrawal调用微信提现接口失败,错误代码:{},错误描述:{}", resultMap.get("err_code"), resultMap.get("err_code_des"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("微信申请提现出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Override
    public ResponseJson queryWithdrawal(JSONObject jsonObject) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            TreeMap<String, Object> param = new TreeMap<>();
            param.put("appid", jsonObject.getString("appid")); //公众账号appid
            param.put("mch_id", yml.getWxPayMerchantId()); //商户号
            param.put("nonce_str", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM)); //随机字符串
            param.put("partner_trade_no", jsonObject.getString("tradeNo")); //商户订单号
            String sign = getWeChatSign(param, yml.getWxPayMerchantKey());
            param.put("sign", sign);
            String parmXml = XmlUtil.transMap2Xml(param);
            LOGGER.info("queryWithdrawal查询微信提现请求参数 parmXml:" + parmXml);
            httpClient = loadKeyStory();
            HttpPost httppost = new HttpPost(yml.getGettransferinfo());
            //这里要设置编码，不然xml中有中文的话会提示签名失败或者展示乱码
            httppost.addHeader("Content-Type", "text/xml");
            StringEntity se = new StringEntity(parmXml, "UTF-8");
            httppost.setEntity(se);
            response = httpClient.execute(httppost);
            String resultXml = EntityUtils.toString(response.getEntity(), "UTF-8");
            LOGGER.info("queryWithdrawal查询微信提现返回值 resultXml:" + resultXml);
            // 解析返回数据
            Map<String, Object> resultMap = XmlUtil.transXml2Map(resultXml);
          /*  String returnCode = String.valueOf(resultMap.get("return_code"));
            if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(returnCode)) {
                LOGGER.error("queryWithdrawal调用微信提现接口失败,错误信息:{}", resultMap.get("return_msg"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            String resultCode = String.valueOf(resultMap.get("result_code"));
            if (!WX_PAY_RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
                LOGGER.error("queryWithdrawal调用微信提现接口失败,错误代码:{},错误描述:{}", resultMap.get("err_code"), resultMap.get("err_code_des"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }*/
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultMap);
        } catch (Exception e) {
            LOGGER.error("微信申请提现出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 获取微信分享需要的参数
     *
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson getShareParam(JSONObject jsonObject) {
        //分享的 url
        String url = jsonObject.getString("url");
        String jsapiTicket = this.getJsapiTicket();
        Map<String, String> map = shareSign(jsapiTicket, url);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, map);
    }


    /**
     * 获取JsapiTicket
     *
     * @return
     */
    private String getJsapiTicket() {

        // 不为空，检查有效期，如果没有失效直接返回token
        if (!StringUtil.isEmpty(jsapi_ticket) && jsapi_ticket_time_out != 0) {
            // 检查有效期
            long now = System.currentTimeMillis() / 1000; // 当前时间
            if (now < jsapi_ticket_time_out) {// 在有效期内
                return jsapi_ticket; // 返回有效的token
            }
        }
        // 走到此处说明没有有效的token
        String accessToken = getAccessToken();
        String url = yml.getJsapiTicketUri() + "?access_token=" + accessToken + "&type=jsapi";
        String json = restTemplateGet(url, null);
        JSONObject jsonObj = JSONObject.parseObject(json);
        Integer errcode = (Integer) jsonObj.get("errcode");
        if (errcode == 0) {
            String ticket = (String) jsonObj.get("ticket");
            int expires_in = (Integer) jsonObj.get("expires_in");
            jsapi_ticket_time_out = System.currentTimeMillis() / 1000 + expires_in; // 计算失效时间
            jsapi_ticket = ticket;// 缓存
            return ticket;
        } else {
            String errmsg = (String) jsonObj.get("errmsg");
            throw new RuntimeException("获取Jsapi ticket出错，errcode:" + errcode + ",errmsg:" + errmsg);
        }
    }

    /**
     * 根据api获取微信接口权限access_token
     *
     * @return
     */
    public String getAccessToken() {
        // 不为空，检查有效期，如果没有失效直接返回token
        if (!StringUtil.isEmpty(access_token) && access_token_time_out != 0) {
            // 检查有效期
            long now = System.currentTimeMillis() / 1000; // 当前时间
            if (now < access_token_time_out) {// 在有效期内
                return access_token; // 返回有效的token
            }
        }
        // 走到此处说明没有有效的token
        String appid = yml.getWxPayAppId();
        String appsecret = yml.getWxPayAppSecret();
        String url = yml.getAccessTokenUri() + "&appid=" + appid + "&secret=" + appsecret;
        String json = restTemplateGet(url, null);
        JSONObject jsonObj = JSONObject.parseObject(json);
        String accessToken = (String) jsonObj.get("access_token");

        if (StringUtil.isEmpty(accessToken)) {
            Integer errcode = (Integer) jsonObj.get("errcode");
            String errmsg = (String) jsonObj.get("errmsg");
            throw new RuntimeException("access_token获取异常: errcode:" + errcode + ",errmsg:" + errmsg);
        }
        int expires_in = (Integer) jsonObj.get("expires_in");

        access_token_time_out = System.currentTimeMillis() / 1000 + expires_in; // 计算失效时间
        access_token = accessToken;// 缓存
        return accessToken;
    }

    @Async
    void updateUserOpenId(String openId, String refreshToken, String userId) {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("wx_open_id", openId);
        jsonObject1.put("id", userId);
        LOGGER.info("--------539-------更新数据报错日志定位-----------------" + jsonObject1.toString());
        try {
            callDataCenter(DataCenterMethod.USER_UPDATE, jsonObject1);
        } catch (Exception e) {
            LOGGER.error("调用数据中心，更新用户openId时出现异常", e);
        }
        Map map = new ConcurrentHashMap(4);
        map.put("appid", yml.getWxPayAppId());
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);
        String refreshResultJson = restTemplateGet(yml.getWxPayRefreshTokenUri(), map);
        LOGGER.info("调用刷新用户授权登陆的接口返回结果：" + refreshResultJson);
    }

    /**
     * @Description 微信接口签名算法
     * @Author taoye
     */

    private String getWeChatSign(TreeMap<String, Object> map, String merchantKey) throws Exception {
        StringBuffer sb = new StringBuffer();
        // 所有参与传参的参数按照accsii排序（升序）
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + merchantKey);
        return MD5Util.md5(sb.toString()).toUpperCase();
    }

    /**
     * 微信分享签名
     *
     * @param jsapi_ticket
     * @param url
     * @return
     */
    private Map<String, String> shareSign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM);
        String timestamp = System.currentTimeMillis() / 1000 + "";
        String string1;
        String signature = "";
        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
        // System.out.println(string1);
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", yml.getWxPayAppId());
        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * @Description 验签
     * @Author taoye
     */
    private boolean checkSign(Map<String, String> map) {
        try {
            LOGGER.info("支付回调验签的入参map:{}", map);
            String sign = map.get("sign");
            TreeMap<String, Object> treeMap = new TreeMap<>();
            treeMap.putAll(map);
            treeMap.remove("serverName");
            treeMap.remove("functionName");
            treeMap.remove("sign");
            /*treeMap.put("appid", yml.getWxPayAppId());*/
            treeMap.put("appid", map.get("appid"));
            treeMap.put("mch_id", yml.getWxPayMerchantId());
            String weChatSign = getWeChatSign(treeMap, yml.getWxPayMerchantKey());
            if (!sign.equals(weChatSign)) {
                LOGGER.error("微信支付回调验签失败");
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("微信支付回调验签出错:", e);
            return false;
        }
    }


    /**
     * @Description 校验订单金额
     * @Author taoye
     */
    private boolean checkMoney(String orderMoney, String totalAmount) {
        if (new BigDecimal(orderMoney).compareTo(new BigDecimal(totalAmount)) != 0) {
            LOGGER.error("微信支付回调验证订单金额失败:totalAmount={},orderMoney={}", totalAmount, orderMoney);
            return false;
        }
        return true;
    }

    @Override
    public ResponseJson paySignAgain(JSONObject jsonObject) {
        String appId = jsonObject.getString("appId");
        String prepayId = jsonObject.getString("prepayId");
        String merchantKey = jsonObject.getString("merchantKey");
        try {
            TreeMap<String, Object> treeMap = new TreeMap<>();
            Long time = System.currentTimeMillis() / 1000;
            treeMap.put("appId", appId);
            treeMap.put("timeStamp", time + "");
            treeMap.put("nonceStr", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM));
            treeMap.put("package", "prepay_id=" + prepayId);
            treeMap.put("signType", "MD5");
            String sign = getWeChatSign(treeMap, merchantKey);
            treeMap.put("paySign", sign);
            LOGGER.info("入参TreeMap: " + treeMap);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, treeMap);
        } catch (Exception e) {
            LOGGER.error("生成签名失败！" + e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "调用签名的方法异常！");
        }
    }

    /**
     * 加载微信证书
     *
     * @return
     * @throws Exception
     */
    public CloseableHttpClient loadKeyStory() throws Exception {
        //指定读取证书格式为PKCS12
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        ClassPathResource classPathResource = new ClassPathResource("static/apiclient_cert.p12");
        InputStream instream = classPathResource.getInputStream();
        try {
            //指定PKCS12的密码(商户ID)
            keyStore.load(instream, yml.getWxPayMerchantId().toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, yml.getWxPayMerchantId().toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        //设置httpclient的SSLSocketFactory
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }


}