package com.ly.mt.center.third.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author zhanglifeng
 * @description 微信小程序 模版消息发送接口
 */
public interface WxTemplateMessageSendService {

    /**
     * 根据模版id,用户微信open_id,发送模版消息到微信小程序
     * @param jsonObject
     * @return
     */
    ResponseJson sendTemplateMessage(JSONObject jsonObject);
}
