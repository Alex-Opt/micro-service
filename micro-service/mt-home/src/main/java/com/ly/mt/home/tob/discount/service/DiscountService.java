package com.ly.mt.home.tob.discount.service;

import com.ly.mt.home.tob.discount.vo.ShopPurchasesDiscountVo;

import java.util.List;

/**
 * 商家进货优惠接口
 *
 * @author add by linan
 * @date 20190709
 */
public interface DiscountService {

    /**
     * 累计优惠价
     *
     * @param
     * @return
     */
    String totalDiscount();

    /**
     * 添加优惠记录
     */
    void addDiscount(ShopPurchasesDiscountVo shopPurchasesDiscountVo);

    /**
     * 更新优惠记录
     */
    void updateDiscountStatus(String id, String status);


    /**
     * 查询优惠记录
     */
    List<ShopPurchasesDiscountVo> getDiscountByPurchasesId(String purchasesId);
}
