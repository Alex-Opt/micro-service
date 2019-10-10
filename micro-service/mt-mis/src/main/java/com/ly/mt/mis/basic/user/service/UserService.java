package com.ly.mt.mis.basic.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 用户管理
 *
 * @author taoye
 */
public interface UserService {
    /**
     * 新增
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson insertUser(JSONObject jsonObject) throws Exception;

    /**
     * 修改
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson updateUser(JSONObject jsonObject) throws Exception;

    /**
     * 禁用/启用
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson updateValidStatus(JSONObject jsonObject) throws Exception;

    /**
     * 密码重置
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson resetPassword(JSONObject jsonObject) throws Exception;

    /**
     * 校验帐号是否存在
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson checkLoginName(JSONObject jsonObject) throws Exception;

    /**
     * 校验手机号是否存在
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson checkMobile(JSONObject jsonObject) throws Exception;
}
