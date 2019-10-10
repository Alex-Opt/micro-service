package com.ly.mt.core.wechat.service.impl;

import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.XmlUtil;
import com.ly.mt.core.wechat.service.RedService;
import com.ly.mt.core.wechat.util.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.util.Map;
import java.util.TreeMap;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_RANDOM;
import static com.ly.mt.core.wechat.config.WeChatConstant.*;
import static com.ly.mt.core.wechat.dict.ResultCode.RESULT_CODE_SUCCESS;
import static com.ly.mt.core.wechat.dict.ReturnCode.RETURN_CODE_SUCCESS;
import static com.ly.mt.core.wechat.util.WeChatUtil.postWeChatWithCert;

/**
 * 微信红包接口
 *
 * @author taoye
 */
@Service
public class RedServiceImpl implements RedService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RedServiceImpl.class);

    @Override
    public void sendRed(String mchBillno, String reOpenid, int totalAmount, int totalNum, String wishing, String actName, String remark, String sceneId) throws Exception {
        Assert.notNull(mchBillno, "RedServiceImpl.sendRed mchBillno must not be null");
        Assert.notNull(reOpenid, "RedServiceImpl.sendRed reOpenid must not be null");
        Assert.notNull(totalAmount, "RedServiceImpl.sendRed totalAmount must not be null");
        Assert.notNull(totalNum, "RedServiceImpl.sendRed totalNum must not be null");
        Assert.notNull(wishing, "RedServiceImpl.sendRed wishing must not be null");
        Assert.notNull(actName, "RedServiceImpl.sendRed actName must not be null");
        Assert.notNull(remark, "RedServiceImpl.sendRed remark must not be null");
        Assert.notNull(sceneId, "RedServiceImpl.sendRed remark must not be null");
        // 参数封装
        TreeMap<String, Object> param = new TreeMap<>();
        // 随机字符串
        param.put("nonce_str", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM));
        // 商户订单号
        param.put("mch_billno", mchBillno);
        // 商户号
        param.put("mch_id", MERCHANT_ID);
        // 公众账号appid
        param.put("wxappid", APP_ID);
        // 商户名称
        param.put("send_name", "雷炎科技");
        // 用户openid
        param.put("re_openid", reOpenid);
        // 付款金额
        param.put("total_amount", totalAmount);
        // 红包发放总人数
        param.put("total_num", totalNum);
        // 红包祝福语
        param.put("wishing", wishing);
        // Ip地址
        String dockerIp = InetAddress.getLocalHost().getHostAddress();
        param.put("client_ip", IP_MPA.get(dockerIp));
        // 活动名称
        param.put("act_name", actName);
        // 备注
        param.put("remark", remark);
        // 场景id
        param.put("scene_id", sceneId);
        // 签名
        String sign = WeChatUtil.getWeChatSign(param, MERCHANT_KEY);
        param.put("sign", sign);
        // map2xml
        String requestXml = XmlUtil.transMap2Xml(param);
        String responseXml = postWeChatWithCert(requestXml, WE_CHAT_API_SEND_RED_PACK);
        // 解析返回数据
        Map<String, Object> map = XmlUtil.transXml2Map(responseXml);
        String returnCode = String.valueOf(map.get("return_code"));
        if (!RETURN_CODE_SUCCESS.getCode().equals(returnCode)) {
            LOGGER.error("RedServiceImpl.sendRed error return_code = {}, return_msg = {}", returnCode, map.get("return_msg"));
            throw new RuntimeException("RedServiceImpl.sendRed error");
        }
        String resultCode = String.valueOf(map.get("result_code"));
        if (!RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
            LOGGER.error("RedServiceImpl.sendRed error err_code = {}, err_code_des = {}", map.get("err_code"), map.get("err_code_des"));
            throw new RuntimeException("RedServiceImpl.sendRed error");
        }
    }


    @Override
    public Map<String, Object> getRedStatus(String mchBillno) throws Exception {
        Assert.notNull(mchBillno, "RedServiceImpl.getRedStatus mchBillno must not be null");
        // 参数封装
        TreeMap<String, Object> param = new TreeMap<>();
        // 随机字符串
        param.put("nonce_str", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM));
        // 商户订单号
        param.put("mch_billno", mchBillno);
        // 商户号
        param.put("mch_id", MERCHANT_ID);
        // 公众账号appid
        param.put("appid", APP_ID);
        // 商户名称
        param.put("bill_type", "MCHT");
        // 签名
        String sign = WeChatUtil.getWeChatSign(param, MERCHANT_KEY);
        param.put("sign", sign);
        // map2xml
        String requestXml = XmlUtil.transMap2Xml(param);
        String responseXml = postWeChatWithCert(requestXml, WE_CHAT_API_GET_HB_INFO);
        // 解析返回数据
        Map<String, Object> map = XmlUtil.transXml2Map(responseXml);
        String returnCode = String.valueOf(map.get("return_code"));
        if (!RETURN_CODE_SUCCESS.getCode().equals(returnCode)) {
            LOGGER.error("RedServiceImpl.getRedStatus error return_code = {}, return_msg = {}", returnCode, map.get("return_msg"));
            throw new RuntimeException("RedServiceImpl.getRedStatusStatus return_code error");
        }
        String resultCode = String.valueOf(map.get("result_code"));
        if (!RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
            LOGGER.error("RedServiceImpl.getRedStatus error err_code = {}, err_code_des = {}", map.get("err_code"), map.get("err_code_des"));
            throw new RuntimeException("RedServiceImpl.getRedStatusStatus result_code error");
        }
        return map;
    }
}