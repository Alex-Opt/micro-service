package com.ly.mt.core.data.goods.mapper;

import com.ly.mt.core.data.goods.entity.GoodsInfoView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GoodsInfoView操作接口
 *
 * @author taoye
 */
@Mapper
public interface GoodsInfoViewMapper {
    /**
     * 查询List<GoodsInfoView>
     *
     * @param goodsInfoView 查询条件
     * @return List<GoodsInfoView>
     * @author taoye
     */
    List<GoodsInfoView> listGoodsInfoView(GoodsInfoView goodsInfoView);
}