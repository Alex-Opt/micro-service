package com.ly.mt.core.data.basic.dao;

import com.ly.mt.core.data.basic.entity.BasicRole;

import java.util.List;

/**
 * BasicRole操作接口
 *
 * @author taoye
 */
public interface BasicRoleDao {
    /**
     * 新增BasicRole
     *
     * @param basicRole 新增数据
     * @return 角色id
     * @author taoye
     */
    String insertBasicRole(BasicRole basicRole);

    /**
     * 更新BasicRole
     *
     * @param basicRole 更新条件和更新数据
     * @return 更新条数
     * @author taoye
     */
    int updateBasicRole(BasicRole basicRole);

    /**
     * 从redis根据id查询BasicRole
     *
     * @param id 角色id
     * @return BasicRole
     * @author taoye
     */
    BasicRole getBasicRoleByIdFromRedis(String id);

    /**
     * 从redis根据parentId查询List<BasicRole>
     *
     * @param parentId 父角色id
     * @return List<BasicRole>
     * @author taoye
     */
    List<BasicRole> listBasicRoleByParentIdFromRedis(String parentId);

    /**
     * 从mysql查询List<BasicRole>
     *
     * @param basicRole 查询条件
     * @return List<BasicRole>
     * @author taoye
     */
    List<BasicRole> listBasicRoleFromMysql(BasicRole basicRole);
}