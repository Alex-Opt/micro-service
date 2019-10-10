package com.ly.mt.mis.base.service;

/**
 * 功能方法
 *
 * @author taoye
 */
public interface BaseService {
    /**
     * 获取登录用户id
     *
     * @return loginUserId
     * @author taoye
     */
    String getLoginUserId();

    /**
     * 获取登录用户name
     *
     * @return loginUserName
     * @author taoye
     */
    String getLoginUserName();

    /**
     * 获取between查询sql
     *
     * @param start between起始值
     * @param end   between截止值
     * @return sql
     * @author taoye
     */
    String getBetweenSql(String start, String end);

    /**
     * 根据userId获取roleNames
     *
     * @param userId 用户id
     * @return roleNames
     * @author taoye
     */
    String getRoleNamesByUserId(String userId);

    /**
     * 根据roleId获取userIds
     *
     * @param roleId 角色id
     * @return userIds
     * @author taoye
     */
    String getUserIdsByRoleId(String roleId);
}