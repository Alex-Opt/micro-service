package com.ly.mt.core.data.basic.dao;

import com.ly.mt.core.data.basic.entity.BasicUserRole;

import java.util.List;

/**
 * BasicUserRole操作接口
 *
 * @author taoye
 */
public interface BasicUserRoleDao {
    /**
     * 新增BasicUserRole
     *
     * @param basicUserRole 新增数据
     * @author taoye
     */
    void insertBasicUserRole(BasicUserRole basicUserRole);

    /**
     * 删除BasicUserRole
     *
     * @param basicUserRole 删除条件
     * @author taoye
     */
    void deleteBasicUserRole(BasicUserRole basicUserRole);

    /**
     * 从redis根据roleId查询List<BasicUserRole>
     * redis不存在则查询mysql
     *
     * @param roleId 角色ID
     * @return List<BasicUserRole>
     * @author taoye
     */
    List<BasicUserRole> listBasicUserRoleByRoleIdFromRedis(String roleId);

    /**
     * 从redis根据userId查询List<BasicUserRole>
     * redis不存在则查询mysql
     *
     * @param userId 用户ID
     * @return List<BasicUserRole>
     * @author taoye
     */
    List<BasicUserRole> listBasicUserRoleByUserIdFromRedis(String userId);
}