package com.ly.mt.core.data.basic.mapper;

import com.ly.mt.core.data.basic.entity.BasicUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BasicUserRole操作接口
 *
 * @author taoye
 */
@Mapper
public interface BasicUserRoleMapper {
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
     * 查询List<BasicUserRole>
     *
     * @param basicUserRole 查询条件
     * @return List<BasicUserRole>
     * @author taoye
     */
    List<BasicUserRole> listBasicUserRole(BasicUserRole basicUserRole);
}