package com.ly.mt.user.server.user.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 用户信息修改接口
 * @Author taoye
 */
public interface UserInfoService {
    /**
     * @Description 身份认证
     * @Author taoye
     */
    JSONObject authentication(String json) throws Exception;

    /**
     * @Description 头像修改
     * @Author taoye
     */
    JSONObject uploadAvatarPic(String json) throws Exception;

    /**
     * @Description 绑定手机
     * @Author taoye
     */
    JSONObject bindMobile(String json) throws Exception;

    /**
     * @Description 修改信息
     * @Author taoye
     */
    JSONObject updateInfo(String json) throws Exception;

    /**
     * @Description 根据登录用户id获取用户信息
     * @Author taoye
     */
    JSONObject loadInfoByLoginId(String json) throws Exception;

    /**
     * @Description 根据用户手机号获取用户信息
     * @Author taoye
     */
    JSONObject loadInfoByUserMobile(String json) throws Exception;

    /**
     * @Description 修改密码
     * @Author taoye
     */
    JSONObject modifyPassword(String json) throws Exception;

    /**
     * @Description 用户信息补全
     * @Author taoye
     */
    JSONObject perfectInfo(String json) throws Exception;

    /**
     * 修改用户名
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject updateLoginName(String json) throws Exception;

    /**
     * 保存用户clientId
     * @param json
     * @return
     */
    JSONObject saveClientId(String json);
}