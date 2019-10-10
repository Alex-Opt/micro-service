package com.ly.mt.mall.h5.wxshare.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Author wanglong
 */
public interface WxShareService {
    /**
     * 获取分享需要的参数（生成签名）
     * @param url
     */
    ResponseJson getShareParam(String url);

    /**
     * [微信小程序二维码生成接口]
     * @author zhanglifeng
     * @param params
     * @return
     */
    ResponseJson getWxaCodeUnLimit(JSONObject params);
}
