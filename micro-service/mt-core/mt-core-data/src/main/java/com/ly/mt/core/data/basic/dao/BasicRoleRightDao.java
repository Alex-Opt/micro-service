package com.ly.mt.core.data.basic.dao;

import com.ly.mt.core.data.basic.entity.BasicRoleRight;

import java.util.List;

/**
 * BasicRoleRight操作接口
 *
 * @author taoye
 */
public interface BasicRoleRightDao {
    /**
     * 新增BasicRoleRight
     *
     * @param basicRoleRights 新增数据
     * @author taoye
     */
    void insertBasicRoleRights(List<BasicRoleRight> basicRoleRights);


    /**
     * 删除BasicRoleRight
     *
     * @param basicRoleRight 删除条件
     * @author taoye
     */
    void deleteBasicRoleRight(BasicRoleRight basicRoleRight);


    /**
     * 从mysql查询BasicRoleRight
     *
     * @param basicRoleRight 查询条件
     * @reture BasicRoleRight
     * @author taoye
     */
    List<BasicRoleRight> listBasicRoleRightFromMysql(BasicRoleRight basicRoleRight);


    /**
     * 从redis根据roleId查询List<BasicRoleRight>
     * redis不存在则查询mysql
     *
     * @param roleId    角色id
     * @param rightType 权限类型
     * @return List<BasicRoleFunc>
     * @author taoye
     */
    List<BasicRoleRight> listBasicRoleRightByRoleIdAndRightTypeFromRedis(String roleId, String rightType);
}