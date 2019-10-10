package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsSpuAttr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsSpuAttrMapper {
    /**
     * @Description 插入GoodsSpuAttr
     * @Author taoye
     */
    void insertGoodsSpuAttr(GoodsSpuAttr goodsSpuAttr);

    /**
     * @Description 根据id删除GoodsSpuAttr
     * @Author taoye
     */
    void deleteGoodsSpuAttrById(GoodsSpuAttr goodsSpuAttr);

    /**
     * @Description 根据id更新GoodsSpuAttr
     * @Author taoye
     */
    int updateGoodsSpuAttrById(GoodsSpuAttr goodsSpuAttr);

    /**
     * @Description 根据条件查询GoodsSpuAttr
     * @Author taoye
     */
    GoodsSpuAttr getGoodsSpuAttr(GoodsSpuAttr goodsSpuAttr);

    /**
     * @Description 根据id查询GoodsSpuAttr
     * @Author taoye
     */
    GoodsSpuAttr getGoodsSpuAttrById(GoodsSpuAttr goodsSpuAttr);

    /**
     * 根据spuId查询商品属性数据
     * @param goodsSpuAttr
     * @return
     */
    List<Map<String, Object>> getGoodsSpuAttrBySpuId(GoodsSpuAttr goodsSpuAttr);

    /**
     * 查询一小时达商品SpuId对应的sku attr
     * @param spu_id
     * @return
     */
    List<String> getGoodsSkuAttrByHourSpuId(Long spu_id);
}