package com.ly.mt.purchase.server.cart.mapper;

import com.ly.mt.core.common.entity.goods.GoodsAttrValue;
import com.ly.mt.core.common.entity.goods.GoodsSkuPicture;
import com.ly.mt.core.common.entity.goods.GoodsSpuInfo;
import com.ly.mt.core.common.entity.purchase.CartGoodsSkuInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartServiceMapper {

    /**
     * 通过Id查询sku信息
     * @param id
     * @return
     */
    CartGoodsSkuInfo selectByPrimaryKey(Long id);

    /**
     * 查询商品sku图片数据
     * @param skuId
     * @return
     */
    List<GoodsSkuPicture> queryGoodsSkuPictureBySkuId(Long skuId);

    /**
     * 查询属性值
     * @param ids
     * @return
     */
    List<GoodsAttrValue> queryAttrValueById(String[] ids);

    /**
     * 根据id查询一条商品spu
     *
     * @param id
     * @return
     */
    GoodsSpuInfo selectSpuInfoById(Long id);
}
