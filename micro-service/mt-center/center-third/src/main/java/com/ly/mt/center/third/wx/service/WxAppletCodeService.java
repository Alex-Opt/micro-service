package com.ly.mt.center.third.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * [微信小程序-小程序码生成接口]
 * note:我们推荐生成并使用小程序码，它具有更好的辨识度.这里我们选用圆形的的二维码。接口选用接口B
 * 接口 B：适用于需要的码数量极多的业务场景生成小程序码，可接受页面参数较短，生成个数不受限。
 * @author zhanglifeng
 * @date 2019-09-23
 */
public interface WxAppletCodeService {
    /**
     * [B 类型微信小程序 页面 分享 ---- 二维码生成接口]
     * @return
     */
    ResponseJson wxaCodeGetUnlimited(JSONObject jsonObject);
}
