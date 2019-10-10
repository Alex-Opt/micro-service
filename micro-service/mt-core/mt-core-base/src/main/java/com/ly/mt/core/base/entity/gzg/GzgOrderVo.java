package com.ly.mt.core.base.entity.gzg;

import com.ly.mt.core.base.entity.trade.TradeOrderCouponVo;
import com.ly.mt.core.base.entity.trade.TradeOrderItemVo;
import com.ly.mt.core.base.entity.user.UserAddress;


import java.util.List;

/**
 * @Author gongj
 * @Description 订单主表  返回前端用Vo
 * @String 2019/5/21
 */
/** @deprecated */
public class GzgOrderVo {
    private String id;

    private String paymentNo;

    private String buyerId;

    private String buyerMemo;

    private String shopId;

    private String gyWarehouseId;

    private String sellerId;

    private Double orderOldMoney;

    private Double orderMoney;

    private Double orderDiscountMoney;

    private String distributionId;

    private String logisticsId;

    private String gyLogisticsCode;

    private Double freight;

    private String orderStatus;

    private String orderSource;

    private String orderYn;

    private String isRefund;

    private String refundTime;

    private String paymentType;

    private String pushStatus;

    private String orderType;

    private String addressId;

    private String autoReceiveTime;

    private String autoCancelTime;

    private String orderSnapshootKey;

    private String locked;

    private String lockTime;

    private String createTime;

    private String payTime;

    private String orderFinishTime;

    private String hotelId;


    /**
     * 存放订单下的商品明细集合
     */
    private List<TradeOrderItemVo> tradeOrderItemVos;

    /**
     * 存放该订单下优惠信息集合
     */
    private List<TradeOrderCouponVo> tradeOrderCouponVos;

    /**
     * 用户的该订单的地址信息
     */
    private UserAddress address;

    /**
     * 快递物流信息
     *
     * @returngzg
     */
    private Object logisticslist;
    /**
     * 格子打开状态，0：未打开，1：正常
     */
    private String cellState;


    /**
     * 格子柜的code码
     */
    private String code;


}