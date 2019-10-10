package com.ly.mt.core.data.shop.mapper;

import com.ly.mt.core.data.shop.entity.ShopPurchasesItems;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ShopPurchasesItems操作接口
 *
 * @author taoye
 */
@Mapper
public interface ShopPurchasesItemsMapper {
    /**
     * 查询List<ShopPurchasesItems>
     *
     * @author taoye
     */
    List<ShopPurchasesItems> listShopPurchasesItems(ShopPurchasesItems shopPurchasesItems);
}