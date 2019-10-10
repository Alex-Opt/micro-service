package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsFront;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsFrontMapper {
    /**
     * @Description 保存GoodsFront
     * @Author
     */
    void insertGoodsFront(GoodsFront goodsFront);

    /**
     * @Description 更新GoodsFront
     * @Author
     */
    void updateGoodsFront(GoodsFront goodsFront);

    /**
     * @Description 查询SpuId
     * @Author
     */
    List<String> getGoodsFrontSpuId(GoodsFront goodsFront);

    /**
     * @Description 查询GoodsFront
     * @Author
     */
    List<GoodsFront> getGoodsFront(GoodsFront goodsFront);

    /**
     * @Description 根据spuId查询GoodsFront
     * @Author
     */
    List<GoodsFront> getGoodsFrontBySpuId(GoodsFront goodsFront);

    /**
     * @Description 根据spuId查询sku数据
     * @Author
     */
    List<Map<String,String>> getGoodsSkuBySpuId(GoodsFront goodsFront);

    /**
     * @Description 根据skuId查询sku数据
     * @Author
     */
    Map<String,String> getGoodsSkuBySkuId(GoodsFront goodsFront);

    /**
     * 根据appType和actiClass查询数据
     * @param goodsFront
     * @return
     */
    GoodsFront queryGoodsFrontByActiClass(GoodsFront goodsFront);
}