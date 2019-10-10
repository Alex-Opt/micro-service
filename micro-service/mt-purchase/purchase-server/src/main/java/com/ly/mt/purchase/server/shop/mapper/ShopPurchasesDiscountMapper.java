package com.ly.mt.purchase.server.shop.mapper;

import com.ly.mt.core.common.entity.purchase.ShopPurchasesDiscount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * B端用户优惠情况汇总
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-16 22:34:34
 */
@Mapper
public interface ShopPurchasesDiscountMapper {

    /**
     * 根据shopId查询出平台为当前用户节省的列表
     * @param shopId
     * @return
     */
    List<ShopPurchasesDiscount> selectByShopId(Long shopId);
}
