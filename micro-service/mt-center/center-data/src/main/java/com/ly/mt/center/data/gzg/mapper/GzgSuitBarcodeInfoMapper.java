package com.ly.mt.center.data.gzg.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 格子柜六号格子的商品套装对应的商品明细操作
 * @author zhanglifeng
 * @date 2019-08-27
 */
@Mapper
public interface GzgSuitBarcodeInfoMapper {
    /**
     * 根据sku_id查询多个单品的商品信息
     * @param spu_id
     * @return
     */
    List<String> queryBySuitSpuId(Long spu_id);
}
