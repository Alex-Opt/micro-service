package com.ly.mt.mis.mis.role.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 运营系统角色权限
 *
 * @author taoye
 */
public interface MisRoleService {
    /**
     * 新增
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson insertRoleFunc(JSONObject jsonObject) throws Exception;


    /**
     * 角色功能权限
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson listRoleFunc(JSONObject jsonObject) throws Exception;
}