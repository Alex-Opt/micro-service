package com.ly.mt.purchase.server.order.mapper;

import com.ly.mt.core.common.entity.purchase.LadderPriceVO;
import com.ly.mt.core.common.entity.purchase.ShopAddressVO;
import com.ly.mt.core.common.entity.purchase.ShopCouponVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * B端下单接口
 *
 * @author xiaobei-ihmhny
 * @date 2019-6-25 22:51:39
 */
@Mapper
public interface BOrderMapper {

    /**
     * 根据用户id查询当前用户的地址信息
     * @param userId
     * @return
     */
    List<ShopAddressVO> listShopAddress(@Param("userId") Long userId);

    /**
     * 根据用户id，查询优惠券折扣率及优惠券优惠金额
     * @param userId
     * @return
     */
    List<ShopCouponVO> listShopCouponByUserId(@Param("userId") Long userId);

    /**
     * 根据用户的userId，计算促销信息
     * @param userId
     * @return
     */
    List<LadderPriceVO> listShopLadderPriceByUserId(@Param("userId") Long userId);
}
