package com.ly.mt.purchase.server.shop.mapper;

import com.ly.mt.core.common.entity.purchase.ShopPurchasesVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购买情况
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-17 22:18:18
 */
@Mapper
public interface PurchaseMapper {

    /**
     * 最新购买情况
     * @return
     */
    ShopPurchasesVO selectNewestPurchasesInfo();
}
