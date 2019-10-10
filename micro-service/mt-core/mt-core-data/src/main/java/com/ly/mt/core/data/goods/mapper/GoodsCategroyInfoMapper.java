package com.ly.mt.core.data.goods.mapper;

import com.ly.mt.core.data.goods.entity.GoodsCategroyInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GoodsCategroyInfo操作接口
 *
 * @author taoye
 */
@Mapper
public interface GoodsCategroyInfoMapper {
    /**
     * 查询GoodsCategroyInfo
     *
     * @param goodsCategroyInfo 查询条件
     * @return GoodsCategroyInfo
     * @author taoye
     */
    GoodsCategroyInfo getGoodsCategroyInfo(GoodsCategroyInfo goodsCategroyInfo);

    /**
     * 查询List<GoodsCategroyInfo>
     *
     * @param goodsCategroyInfo 查询条件
     * @return List<GoodsCategroyInfo>
     * @author taoye
     */
    List<GoodsCategroyInfo> listGoodsCategroyInfo(GoodsCategroyInfo goodsCategroyInfo);
}