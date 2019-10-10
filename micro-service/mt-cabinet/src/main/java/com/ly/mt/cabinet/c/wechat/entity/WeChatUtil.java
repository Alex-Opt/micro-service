package com.ly.mt.cabinet.c.wechat.entity;

import com.ly.mt.core.base.util.MD5Util;
import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

@Component
public class WeChatUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeChatUtil.class);
    @Autowired
    private WeChatPropertiesConfig weChatPropertiesConfig;
    @Autowired
    private XMLUtils xmlUtils;

    /**
     * @param bill_create_ip
     * @param gzgOrderId
     * @param openId         为空:H5支付，不为空：JSAPI支付
     * @return
     */
    public String packageParam(String bill_create_ip, String amountStr, String gzgOrderId, String openId) {

        String prePayXml = null;
        double amount = Double.parseDouble(amountStr);
        try {
            //获取配置信息，appId，商户id，商户名称，商户key，回调地址
            String appid = weChatPropertiesConfig.getAppid();
            String merchantId = String.valueOf(weChatPropertiesConfig.getMerchantId());
            String merchantKey = weChatPropertiesConfig.getMerchantKey();
            String merchantName = weChatPropertiesConfig.getMerchantName();
            String notifyUrl = weChatPropertiesConfig.getNotifyUrl();
            String key = weChatPropertiesConfig.getKey();

//            Map<String, String> treeMap = new TreeMap<String, String>();
            SortedMap<String, String> treeMap = new TreeMap<String, String>();
            treeMap.put("appid", appid);
            treeMap.put("mch_id", merchantId);// 设置商户号
            treeMap.put("nonce_str", UUIDUtils.createUUID());//随机数
            treeMap.put("body", "魔笛电子烟");//商品描述
            treeMap.put("out_trade_no", gzgOrderId);//商户系统内部的订单号,32个字符内、可包含字母,确保在商户系统唯一,详细说明
            treeMap.put("total_fee", ((int) (amount * 100)) + "");
            LOGGER.info("传输给微信的金额================：{}", ((int) (amount * 100)) + "");
            treeMap.put("spbill_create_ip", bill_create_ip);
            treeMap.put("notify_url", notifyUrl);//通知回调地址
            treeMap.put("sign_type", "MD5");
//            treeMap.put("timeStamp",String.valueOf(System.currentTimeMillis()/1000));
            if (!"".equals(openId)) {
                treeMap.put("trade_type", "JSAPI");//JSAPI支付
                treeMap.put("openid", openId);//JSAPI支付
            } else {
                treeMap.put("trade_type", "MWEB");//H5支付
            }

//            String sceneinfo = "{\\\"h5_info\\\":{\\\"type\\\":\\\"Wap\\\",\\\"wap_url\\\":\\\"http://mall.motivape.cn\\\",\\\"wap_name\\\":\\\"MOTI官方商城\\\"}}";
//            treeMap.put("scene_info",sceneinfo);
//            treeMap.put("openid", null);
//            treeMap.put("attach", URLEncoder.encode("测试微信支付", "UTF-8"));
//            treeMap.put("attach", "魔笛电子烟支付");

            String sign = generateSign(treeMap, key);
            treeMap.put("sign", sign);

            String xml = generateXML(treeMap);
            LOGGER.info("call wechat MWEB pay param is {}", xml);
            prePayXml = xml;
            LOGGER.info("转换完成的xml文件：{}", prePayXml);
            //调用微信接口
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prePayXml;
    }

    public String packageOrderQueryXml(Map<String, Object> map) throws Exception {
        TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        treeMap.put("appid", String.valueOf(map.get("appid")));
        treeMap.put("mch_id", String.valueOf(map.get("mch_id")));
        treeMap.put("transaction_id", String.valueOf(map.get("transaction_id")));
        treeMap.put("nonce_str", UUIDUtils.createUUID());
//		treeMap.put("out_trade_no", this.out_trade_no);

        StringBuilder sb = new StringBuilder();
        for (String key : treeMap.keySet()) {
            sb.append(key).append("=").append(treeMap.get(key)).append("&");
        }
        sb.append("key=" + weChatPropertiesConfig.getMerchantKey());
//        String sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
        String sign = MD5Util.md5(sb.toString()).toUpperCase();
        LOGGER.info("query order param sign-->", sign);
        System.out.println("0---查询订单参数sign签名打印=============" + sign);
        treeMap.put("sign", sign);

        String xmlStr = xmlUtils.map2Xml(treeMap);
        return xmlStr;
    }

    /**
     * 获取微信回调数据
     *
     * @param request
     * @return
     */
    public Map<String, Object> getWeChatReplay(HttpServletRequest request) {
        Map<String, Object> map = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String result = new String(outSteam.toByteArray(), "utf-8");
            map = xmlUtils.doXMLParse(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String map2XmlParam(Map<String, String> param) {

        return null;
    }


    /**
     * 　　* 创建签名
     * 　　* @param parameters
     * 　　* @param key
     * 　　* @return
     */
    @SuppressWarnings("rawtypes")
    public static String generateSign(SortedMap<String, String> maps, String key) throws Exception {
        StringBuffer sbkey = new StringBuffer();
        Set es = maps.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sbkey.append(k + "=" + v + "&");
            }
        }
        //System.out.println("字符串:"+sb.toString());
        sbkey = sbkey.append("key=" + key);
        LOGGER.info("MD5加密的字符串{}", sbkey.toString());
        //MD5加密,结果转换为大写字符
//        String sign = MD5Util.MD5Encode(sbkey.toString(), "UTF-8").toUpperCase();
        String sign = MD5Util.md5(sbkey.toString()).toUpperCase();
        System.out.println("MD5加密值后sign =" + sign);
        return sign;


    }

    @SuppressWarnings("rawtypes")
    public static String generateXML(Map<String, String> maps) {
        Set<String> keySet = maps.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>\n");
        for (String k : keyArray) {
            xml.append("<" + k + ">").append(maps.get(k)).append("</" + k + ">\n");

        }
        xml.append("</xml>");
        LOGGER.info("生成的xml文件：{}", xml.toString());
        return xml.toString();
    }


    public String generateSignMD52(String timeStamp, String nonce_str, String prepay_id) throws Exception {
        SortedMap<String, String> maps = new TreeMap<String, String>();
        maps.put("appId", weChatPropertiesConfig.getAppid());
        maps.put("timeStamp", timeStamp);
        maps.put("nonceStr", nonce_str);
        maps.put("package", "prepay_id=" + prepay_id);
        maps.put("signType", "MD5");
        LOGGER.info("二次签名的值：{}", maps);
        return generateSign(maps, weChatPropertiesConfig.getKey());
    }

}
