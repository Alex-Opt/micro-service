package com.ly.mt.center.third.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description 生成微信公众号签名
 * @Author wanghongliang
 */
public interface WxGenerateSignatureService {
    /**
     * @Description 生成微信公众号签名
     * @Author wanghongliang
     */
    ResponseJson generateWxSignature(JSONObject jsonObject);
}