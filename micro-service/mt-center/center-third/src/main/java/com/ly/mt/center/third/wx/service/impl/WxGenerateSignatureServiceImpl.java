package com.ly.mt.center.third.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.third.base.util.WxUtil;
import com.ly.mt.center.third.wx.service.WxGenerateSignatureService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @program: my-blue-tooth
 * @description:
 * @author: wanghongliang
 * @create: 2019-08-23 14:34
 **/
@Service
public class WxGenerateSignatureServiceImpl extends BaseServiceImpl implements WxGenerateSignatureService {

    private final static Logger LOGGER = LoggerFactory.getLogger(WxLoginServiceImpl.class);
    @Resource
    YmlConfig yml;

    /**
     * 生成微信公众号签名串
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson generateWxSignature(JSONObject jsonObject) {
        String appId =yml.getWxPayAppId();//微信appid
        String appSecret =yml.getWxPayAppSecret();//微信appSercrte
        String accessTokenUrl = yml.getAccessTokenUri();//微信获取tokenUrl
        String ticketUrl = yml.getJsapiTicketUri();//微信获取ticketUrl
        String url = String.valueOf(jsonObject.get("url"));
        //获取微信公众号token
        String accessToken = WxUtil.getAccessToken(appId,appSecret,accessTokenUrl);
        //获取微信公众号ticket
        String jsapiTicket = WxUtil.getTicket(accessToken,ticketUrl);
        //时间戳和随机字符串
        String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳

        //将参数排序并拼接字符串
        String str = "jsapi_ticket="+jsapiTicket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
        //将字符串进行sha1加密
        String signature =WxUtil.SHA1(str);
        JSONObject resultObject =  new JSONObject();
        resultObject.put("timestamp",timestamp);
        resultObject.put("nonceStr",noncestr);
        resultObject.put("signature",signature);
        resultObject.put("appId",appId);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultObject);
    }
}