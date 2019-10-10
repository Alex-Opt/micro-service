package com.ly.mt.core.data.basic.mapper;

import com.ly.mt.core.data.basic.entity.BasicRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BasicRole操作接口
 *
 * @author taoye
 */
@Mapper
public interface BasicRoleMapper {
    /**
     * 新增BasicRole
     *
     * @param basicRole 新增数据
     * @return 主键id
     * @author taoye
     */
    int insertBasicRole(BasicRole basicRole);

    /**
     * 更新BasicRole
     *
     * @param basicRole 更新条件和更新数据
     * @return 更新条数
     * @author taoye
     */
    int updateBasicRole(BasicRole basicRole);

    /**
     * 查询ErpRole
     *
     * @param basicRole 查询条件
     * @return BasicRole
     * @author taoye
     */
    BasicRole getBasicRole(BasicRole basicRole);

    /**
     * 查询List<BasicRole>
     *
     * @param basicRole 查询条件
     * @return List<BasicRole>
     * @author taoye
     */
    List<BasicRole> listBasicRole(BasicRole basicRole);

}