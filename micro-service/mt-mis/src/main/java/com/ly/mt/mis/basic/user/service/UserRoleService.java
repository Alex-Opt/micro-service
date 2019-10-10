package com.ly.mt.mis.basic.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 系统管理-用户角色管理
 *
 * @author taoye
 */
public interface UserRoleService {
    /**
     * 新增
     *
     * @param jsonObject 入参
     * @return 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson insertUserRole(JSONObject jsonObject) throws Exception;

    /**
     * 删除
     *
     * @param jsonObject 入参
     * @return 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson deleteUserRole(JSONObject jsonObject) throws Exception;

    /**
     * 已分配角色表格
     *
     * @param jsonObject 入参
     * @return 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadUserRoleDatagrid(JSONObject jsonObject) throws Exception;


    /**
     * 未分配角色表格
     *
     * @param jsonObject 入参
     * @return 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadRoleDatagrid(JSONObject jsonObject) throws Exception;
}