package com.ly.mt.mis.basic.role.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 系统管理-角色用户管理
 *
 * @author taoye
 */
public interface RoleUserService {
    /**
     * 角色包含用户表格
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadRoleUserDatagrid(JSONObject jsonObject) throws Exception;
}