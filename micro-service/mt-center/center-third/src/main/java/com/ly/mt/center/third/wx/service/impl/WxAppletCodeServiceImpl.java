package com.ly.mt.center.third.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.third.base.util.HttpXmlClient;
import com.ly.mt.center.third.wx.service.WxAppletCodeService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.redis.RedisKey.REDIS_WX_APPLET_ACCESS_TOKEN;

/**
 * [微信小程序-分享的二维码（太阳码）生成接口服务层]
 *
 * @author zhanglifeng
 * @date 2019-09-23
 */
@Service
public class WxAppletCodeServiceImpl extends BaseServiceImpl implements WxAppletCodeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxTemplateMessageSendServiceImpl.class);
    @Resource
    YmlConfig yml;
    @Resource
    private WxLoginServiceImpl wxLoginService;

    @Override
    public ResponseJson wxaCodeGetUnlimited(JSONObject jsonObject) {
        LOGGER.info("---------------二维码生成接口的入参---------------------------：{}", jsonObject);
        wxLoginService.getWxAppletAccessToken(new JSONObject());
        LOGGER.info("-------------------三方服务间的调用没有问题，获取到最新的accessToken-----------------------");
        String access_token = redisService.get(REDIS_WX_APPLET_ACCESS_TOKEN);
        String wxaCodeUnLimitUri = yml.getWxaCodeUnlimitUri();
        LOGGER.info("----------------------------------------微信小程序模版消息发送的入参：{}", JSONObject.toJSONString(jsonObject));
        String resultStr = HttpXmlClient.postSend(wxaCodeUnLimitUri + access_token, JSONObject.toJSONString(jsonObject));
        LOGGER.info("----------------------------------------微信小程序模版消息发送的出参：{}", resultStr);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultStr);
    }
}
