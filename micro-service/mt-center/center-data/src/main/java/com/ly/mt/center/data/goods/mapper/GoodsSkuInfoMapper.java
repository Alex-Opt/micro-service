package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsSkuInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsSkuInfoMapper {
    /**
     * @Description 插入GoodsSkuInfo
     * @Author taoye
     */
    void insertGoodsSkuInfo(GoodsSkuInfo goodsSkuInfo);

    /**
     * @Description 根据id删除GoodsSkuInfo
     * @Author taoye
     */
    void deleteGoodsSkuInfoById(GoodsSkuInfo goodsSkuInfo);

    /**
     * @Description 根据id更新GoodsSkuInfo
     * @Author taoye
     */
    int updateGoodsSkuInfoById(GoodsSkuInfo goodsSkuInfo);

    /**
     * @Description 根据条件查询GoodsSkuInfo
     * @Author taoye
     */
    GoodsSkuInfo getGoodsSkuInfo(GoodsSkuInfo goodsSkuInfo);

    /**
     * @Description 根据id查询GoodsSkuInfo
     * @Author taoye
     */
    GoodsSkuInfo getGoodsSkuInfoById(GoodsSkuInfo goodsSkuInfo);

    /**
     * 根据spuId和attrbutes查询sku数据
     * @param goodsSkuInfo
     * @return
     */
    GoodsSkuInfo getGoodsSkuInfoBySpuIdAndAttr(GoodsSkuInfo goodsSkuInfo);
    /**
     * 根据spuId查询sku数据
     * @param goodsSkuInfo
     * @return
     */
    List<GoodsSkuInfo> getGoodsSkuInfoBySpuId(GoodsSkuInfo goodsSkuInfo);

    /**
     * 展柜补货sku列表 只查询spuId
     * @param goodsSkuInfo
     * @return
     */
    List<GoodsSkuInfo> getGoodsSkuInfoBySpuIdNew(GoodsSkuInfo goodsSkuInfo);
}