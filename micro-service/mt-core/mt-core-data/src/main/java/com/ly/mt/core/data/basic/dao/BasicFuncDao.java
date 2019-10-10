package com.ly.mt.core.data.basic.dao;

import com.ly.mt.core.data.basic.entity.BasicFunc;

import java.util.List;

/**
 * BasicFunc操作接口
 *
 * @author taoye
 */
public interface BasicFuncDao {
    /**
     * 新增BasicFunc
     *
     * @param basicFuncs 保存数据
     * @author taoye
     */
    void insertBasicFunc(List<BasicFunc> basicFuncs);

    /**
     * 更新BasicFunc
     *
     * @param basicFunc 更新数据和条件
     * @author taoye
     */
    void updateBasicFunc(BasicFunc basicFunc);

    /**
     * 查询BasicFunc
     * id不为空时走getBasicFuncByIdFromRedis方法，id为空时查询mysql
     *
     * @param basicFunc 查询条件
     * @author taoye
     */
    BasicFunc getBasicFunc(BasicFunc basicFunc);

    /**
     * 从redis根据id查询BasicFunc
     *
     * @param id 主键id
     * @return BasicFunc
     * @author taoye
     */
    BasicFunc getBasicFuncByIdFromRedis(String id);

    /**
     * 从redis根据projectType、funcLevel查询List<BasicFunc>
     * redis不存在则查询mysql
     *
     * @param projectType 项目类型
     * @param funcLevel   功能等级
     * @return List<BasicFunc>
     * @author taoye
     */
    List<BasicFunc> listBasicFuncByProjectTypeAndFuncLevelFromRedis(String projectType, String funcLevel);

    /**
     * 从redis根据parentId查询List<BasicFunc>
     * redis不存在则查询mysql
     *
     * @param parentId 父功能id
     * @return List<BasicFunc>
     * @author taoye
     */
    List<BasicFunc> listBasicFuncByProjectTypeAndParentIdFromRedis(String projectType, String parentId);

    /**
     * 从redis根据parent_id查询最大排序号
     * redis不存在则查询mysql
     *
     * @param parentId 父功能id
     * @return maxSort
     * @author taoye
     */
    String getMaxSortByParentIdFromRedis(String parentId);
}