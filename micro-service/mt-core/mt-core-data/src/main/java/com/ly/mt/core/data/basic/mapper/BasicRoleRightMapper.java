package com.ly.mt.core.data.basic.mapper;

import com.ly.mt.core.data.basic.entity.BasicRoleRight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BasicRoleRight操作接口
 *
 * @author taoye
 */
@Mapper
public interface BasicRoleRightMapper {
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
     * 查询List<BasicRoleRight>
     *
     * @param basicRoleRight 查询条件
     * @return List<BasicRoleRight>
     * @author taoye
     */
    List<BasicRoleRight> listBasicRoleRight(BasicRoleRight basicRoleRight);
}