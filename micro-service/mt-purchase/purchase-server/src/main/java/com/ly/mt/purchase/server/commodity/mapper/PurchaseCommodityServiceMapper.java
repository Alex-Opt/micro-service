package com.ly.mt.purchase.server.commodity.mapper;

import com.ly.mt.core.common.entity.purchase.CommodityDetail;
import com.ly.mt.core.common.entity.purchase.CommodityInfo;
import com.ly.mt.core.common.entity.purchase.ShopCouponInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PurchaseCommodityServiceMapper {

    /**
     * 获取商品信息
     * @param cId
     * @return
     */
    List<CommodityInfo> getCommodityInfo(Long cId);

    /**
     * 获取商品详情信息
     * @param skuId
     * @return
     */
    List<CommodityDetail> getCommodityDetail(@Param("skuId") Long skuId);

    /**
     * 根据spiId商品信息
     * @param spuId
     * @return
     */
    List<CommodityDetail> getCommodityBySpuId (Long spuId);

    /**
     * 获取商品阶梯价
     * @param skuId
     * @return
     */
    List<CommodityDetail> getCommodityLadderPrice(Long skuId);

    /**
     * 获取店铺优惠劵信息
     * @param shopId
     * @param userId
     * @return
     */
    ShopCouponInfo getShopCouponInfo(@Param("shopId") Long shopId,@Param("userId") Long userId);

}
