package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsAttrValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface GoodsAttrValueMapper {
    /**
     * @Description 保存GoodsAttrValue
     * @Author taoye
     */
    void insertGoodsAttrValue(GoodsAttrValue goodsAttrValue);

    /**
     * @Description 删除GoodsAttrValue
     * @Author taoye
     */
    void deleteGoodsAttrValue(GoodsAttrValue goodsAttrValue);

    /**
     * @Description 更新GoodsAttrValue
     * @Author taoye
     */
    int updateGoodsAttrValue(GoodsAttrValue goodsAttrValue);

    /**
     * @Description 查询GoodsAttrValue
     * @Author taoye
     */
    GoodsAttrValue getGoodsAttrValue(GoodsAttrValue goodsAttrValue);

    /**
     * 根据属性值id查询属性值和属性
     * @param goodsAttrValue
     * @return
     */
    Map getGoodsAttrByValueId(GoodsAttrValue goodsAttrValue);
}