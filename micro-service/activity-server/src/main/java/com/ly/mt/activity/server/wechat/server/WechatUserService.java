package com.ly.mt.activity.server.wechat.server;

import com.alibaba.fastjson.JSONObject;

/**
 * @description
 * 微信用户操作service
 * @author panjingtian
 * @date 2019/8/13 10:36 PM
 */
public interface WechatUserService {


    /**
     * 添加微信
     * @param  jsonObject {@link com.ly.mt.core.common.entity.hd.request.WechatUserRequestBody}
     * @return
     */
    JSONObject addWechatUser(JSONObject jsonObject);

    /**
     * 根据主键查询微信用户
     * @param jsonObject key  = id {@link String}
     * @return
     */
    JSONObject findUserById(JSONObject jsonObject);


    /**
     * 根据openid查询微信用户
     * @param jsonObject key = openId {@link String}
     * @return
     */
    JSONObject findUserByOpenId(JSONObject jsonObject);

}
