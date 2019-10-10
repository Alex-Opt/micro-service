package com.ly.mt.activity.server.wechat.server;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.hd.request.BaseWechatHelpReauesyBody;
import com.ly.mt.core.common.entity.hd.request.WechatHelpUserRequestBody;

/**
 * @description
 *
 * 微信助力活动操作
 *
 * @author panjingtian
 * @date 2019/8/13 10:41 PM
 */
public interface WechatHelpService {


    /**
     * 注册微信助力类型活动用户
     * @param jsonObject {@link WechatHelpUserRequestBody}
     * @return
     */
    JSONObject addWechatHelpuser(JSONObject jsonObject);


    /**
     * 查询用户任务进行的状态
     * @param jsonObject {@link BaseWechatHelpReauesyBody}
     * @return
     */
    JSONObject findWechatHelpuser(JSONObject jsonObject);

    //更新微信助力用户，包括更新校验手机号等信息，以活动id和openid为条件
    JSONObject updateWechatHelp(JSONObject jsonObject);

    //进行助力,更新助力子任务表
    JSONObject doHelp(JSONObject jsonObject);

    JSONObject motiPicCommit(JSONObject jsonObject);

    /**
     * 查看认证状态
     * @param jsonObject {@link BaseWechatHelpReauesyBody}
     * @return
     */
    JSONObject lookHelpUserStatus(JSONObject jsonObject);

    /**
     * 查看用户优惠券
     * @param jsonObject    {@link BaseWechatHelpReauesyBody}
     * @return
     */
    JSONObject findUserCouponCode(JSONObject jsonObject);

    /**
     * 发送短信，并且更新手机号
     * @param jsonObject
     * @return
     */
    JSONObject updatePhone(JSONObject jsonObject);

    /**
     * 10984开始计数
     * @param jsonObject
     * @return
     */
    JSONObject findUserCount(JSONObject jsonObject);

    /**
     * 发送验证码
     * @param jsonObject
     * @return
     */
    JSONObject sendCode(JSONObject jsonObject);


    /**
     * 根据openid查询兑换码
     * @param jsonObject
     * @return
     */
    JSONObject findCodeByopenId(JSONObject jsonObject);

}
