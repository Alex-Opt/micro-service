package com.ly.mt.mis.basic.role.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 系统管理-角色管理
 *
 * @author taoye
 */
public interface RoleService {
    /**
     * 新增
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson insertRole(JSONObject jsonObject) throws Exception;

    /**
     * 修改
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson updateRole(JSONObject jsonObject) throws Exception;

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
     * 角色表格树
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadRoleTreegrid(JSONObject jsonObject) throws Exception;

    /**
     * 角色表格树
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson restRoleTreegrid(JSONObject jsonObject) throws Exception;

    /**
     * 角色下拉表格
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadRoleCombotree(JSONObject jsonObject) throws Exception;
}