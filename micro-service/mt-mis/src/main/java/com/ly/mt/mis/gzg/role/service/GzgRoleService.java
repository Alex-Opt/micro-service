package com.ly.mt.mis.gzg.role.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 格子柜角色权限
 *
 * @author taoye
 */
public interface GzgRoleService {
    /**
     * 新增
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson insertRoleArea(JSONObject jsonObject) throws Exception;


    /**
     * 角色功能权限
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson listRoleArea(JSONObject jsonObject) throws Exception;
}