package com.ly.mt.center.third.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.third.wx.service.WxTemplateMessageSendService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.redis.RedisKey.*;

/**
 * @author zhanglifeng
 * @description 微信小程序 模版消息发送接口
 */
@Service
public class WxTemplateMessageSendServiceImpl extends BaseServiceImpl implements WxTemplateMessageSendService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxTemplateMessageSendServiceImpl.class);
    @Resource
    YmlConfig yml;

    @Resource
    private WxLoginServiceImpl wxLoginService;

    @Override
    public ResponseJson sendTemplateMessage(JSONObject jsonObject) {
        //判断formId来源与openId的缓存还是订单支付的缓存。
        boolean flag = false;
        wxLoginService.getWxAppletAccessToken(new JSONObject());
        LOGGER.info("-------------------三方服务间的调用没有问题，获取到最新的accessToken-----------------------");
        String access_token = redisService.get(REDIS_WX_APPLET_ACCESS_TOKEN);
        String openId = jsonObject.getString("openId");
        String templateId = jsonObject.getString("templateId");
        Object data = jsonObject.get("data");
        LOGGER.info("------------------------data:{}", data);
        String  formId = getOneFormId(openId);
        if (StringUtil.isEmpty(formId)) {
            LOGGER.info("-----------------formId获取为空-----------------");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        String templateMessageSendUri = yml.getTemplateMessageSendUri();
        JSONObject sendMsgTemplate = new JSONObject(4);
        sendMsgTemplate.put("touser", openId);
        sendMsgTemplate.put("template_id", templateId);
        sendMsgTemplate.put("form_id", formId);
        //进入微信小程序的参数
        sendMsgTemplate.put("page","pages/mine/mine");
        sendMsgTemplate.put("data", data);
        LOGGER.info("----------------------------------------微信小程序模版消息发送的入参：{}", JSONObject.toJSONString(sendMsgTemplate));
        String resultStr = restTemplatePost(templateMessageSendUri + access_token, JSONObject.toJSONString(sendMsgTemplate));
        LOGGER.info("----------------------------------------微信小程序模版消息发送的出参：{}", resultStr);
        delFormIdByOpenId(openId, formId);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultStr);
    }

    /**
     * 根据openId从缓存中拿到一个formId
     *
     * @param openId
     * @return
     */
    private String getOneFormId(String openId) {
        String formIdCache = redisService.get(REDIS_WX_APPLET_FORM_ID, openId);
        List<String> formIdList = JSONObject.parseArray(formIdCache, String.class);
        if (formIdList != null && formIdList.size() > 0) {
            return formIdList.get(0);
        }
        return null;
    }

    /**
     * 删除使用过的formId
     *
     * @param openId
     * @param formId
     */
    private void delFormIdByOpenId(String openId, String formId) {
        String formIdCache = redisService.get(REDIS_WX_APPLET_FORM_ID, openId);
        List<String> formIdList = JSONObject.parseArray(formIdCache, String.class);
        if (formIdList.contains(formId)) {
            formIdList.remove(formId);
        }
        redisService.del(REDIS_WX_APPLET_FORM_ID, openId);
        redisService.set(REDIS_WX_APPLET_FORM_ID, openId, JSONObject.toJSONString(formIdList), 7L, TimeUnit.DAYS);
    }
}
