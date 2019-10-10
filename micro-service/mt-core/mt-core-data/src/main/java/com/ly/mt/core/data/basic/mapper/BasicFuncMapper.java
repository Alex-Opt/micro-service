package com.ly.mt.core.data.basic.mapper;

import com.ly.mt.core.data.basic.entity.BasicFunc;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BasicFunc操作接口
 *
 * @author taoye
 */
@Mapper
public interface BasicFuncMapper {
    /**
     * 新增BasicFunc
     *
     * @param basicFuncs 新增数据
     * @author taoye
     */
    void insertBasicFunc(List<BasicFunc> basicFuncs);

    /**
     * 更新BasicFunc
     *
     * @param basicFunc 更新数据
     * @author taoye
     */
    void updateBasicFunc(BasicFunc basicFunc);

    /**
     * 查询BasicFunc
     *
     * @param basicFunc 查询条件
     * @return BasicFunc
     * @author taoye
     */
    BasicFunc getBasicFunc(BasicFunc basicFunc);

    /**
     * 查询List<BasicFunc>
     *
     * @param basicFunc 查询条件
     * @return List<BasicFunc>
     * @author taoye
     */
    List<BasicFunc> listBasicFunc(BasicFunc basicFunc);

    /**
     * 根据parentId查询最大sort
     *
     * @param parentId 父功能ID
     * @return maxSort
     * @author taoye
     */
    String getMaxSortByParentId(String parentId);
}
