package com.ly.mt.center.third.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description 微信h5支付
 * @Author taoye
 */
public interface WxPayService {
    /**
     * @Description 支付状态
     * @Author taoye
     */
    ResponseJson status(JSONObject jsonObject);

    /**
     * @Description 支付回调
     * @Author taoye
     */
    ResponseJson notify(JSONObject jsonObject);

    /**
     * @Description 发起微信支付
     * @Author taoye
     */
    ResponseJson pay(JSONObject jsonObject);


    /**
     * @Description 微信授权登陆接口
     * @Author zhanglifeng
     */
    ResponseJson confirmAuthorization(JSONObject jsonObject);


    /**
     * 微信退款
     *
     * @param jsonObject
     * @return
     */
    ResponseJson refund(JSONObject jsonObject);

    /**
     * 获取微信分享需要的参数
     *
     * @param jsonObject
     * @return
     */
    ResponseJson getShareParam(JSONObject jsonObject);

    /**
     * 微信小程序后者微信浏览器内支付在调用统一下单接口后的再次签名接口
     *
     * @param jsonObject
     * @return
     */
    ResponseJson paySignAgain(JSONObject jsonObject);


    /**
     * 微信提现：申请商户号转账到微信个人账户
     *
     * @param jsonObject
     * @return
     */
    ResponseJson withdrawal(JSONObject jsonObject);

    /**
     * 查询企业付款到微信个人结果
     * @param jsonObject
     * @return
     */
    ResponseJson queryWithdrawal(JSONObject jsonObject);

}