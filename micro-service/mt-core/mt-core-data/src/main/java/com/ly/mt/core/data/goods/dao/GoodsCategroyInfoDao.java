package com.ly.mt.core.data.goods.dao;

import com.ly.mt.core.data.goods.entity.GoodsCategroyInfo;

import java.util.List;

/**
 * GoodsCategroyInfo操作接口
 *
 * @author taoye
 */
public interface GoodsCategroyInfoDao {
    /**
     * 从reids根据id查询GoodsCategroyInfoDao
     * redis不存在则查询mysql
     *
     * @param id 类目id
     * @return GoodsCategroyInfoDao
     * @author taoye
     */
    GoodsCategroyInfo getGoodsCategroyInfoDaoByIdFromRedis(String id);


    /**
     * 从redis根据parentId查询List<GoodsCategroyInfo>
     * redis不存在则查询mysql
     *
     * @param parentId 父id
     * @return List<GoodsCategroyInfo>
     * @author taoye
     */
    List<GoodsCategroyInfo> listGoodsCategroyInfoByParentIdFromRedis(String parentId);
}