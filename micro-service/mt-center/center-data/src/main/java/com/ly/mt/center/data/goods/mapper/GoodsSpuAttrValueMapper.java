package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsSpuAttrValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsSpuAttrValueMapper {
    /**
     * @Description 插入GoodsSpuAttrValue
     * @Author taoye
     */
    void insertGoodsSpuAttrValue(GoodsSpuAttrValue goodsSpuAttrValue);

    /**
     * @Description 根据id删除GoodsSpuAttrValue
     * @Author taoye
     */
    void deleteGoodsSpuAttrValueById(GoodsSpuAttrValue goodsSpuAttrValue);

    /**
     * @Description 根据id更新GoodsSpuAttrValue
     * @Author taoye
     */
    int updateGoodsSpuAttrValueById(GoodsSpuAttrValue goodsSpuAttrValue);

    /**
     * @Description 根据条件查询GoodsSpuAttrValue
     * @Author taoye
     */
    GoodsSpuAttrValue getGoodsSpuAttrValue(GoodsSpuAttrValue goodsSpuAttrValue);

    /**
     * @Description 根据id查询GoodsSpuAttrValue
     * @Author taoye
     */
    GoodsSpuAttrValue getGoodsSpuAttrValueById(GoodsSpuAttrValue goodsSpuAttrValue);

    /**
     * 根据spuId查询商品属性值数据
     * @param goodsSpuAttrValue
     * @return
     */
    List<Map<String, Object>> getGoodsSpuAttrValueBySpuId(GoodsSpuAttrValue goodsSpuAttrValue);

    /**
     * 根据spuId查询商品属性值数据
     * @param goodsSpuAttrValue
     * @return
     */
    List<Map<String, Object>> getGoodsSpuAllAttrValueBySpuId(GoodsSpuAttrValue goodsSpuAttrValue);
}