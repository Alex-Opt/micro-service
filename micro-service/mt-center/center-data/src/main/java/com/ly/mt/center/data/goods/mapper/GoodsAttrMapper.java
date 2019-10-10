package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsAttr;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsAttrMapper {
    /**
     * @Description 保存GoodsAttr
     * @Author taoye
     */
    void insertGoodsAttr(GoodsAttr goodsAttr);

    /**
     * @Description 删除GoodsAttr
     * @Author taoye
     */
    void deleteGoodsAttr(GoodsAttr goodsAttr);

    /**
     * @Description 更新GoodsAttr
     * @Author taoye
     */
    int updateGoodsAttr(GoodsAttr goodsAttr);

    /**
     * @Description 查询GoodsAttr
     * @Author taoye
     */
    GoodsAttr getGoodsAttr(GoodsAttr goodsAttr);
}