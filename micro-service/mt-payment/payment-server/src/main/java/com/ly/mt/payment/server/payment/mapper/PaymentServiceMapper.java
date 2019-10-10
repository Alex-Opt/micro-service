package com.ly.mt.payment.server.payment.mapper;

import com.ly.mt.core.base.entity.payment.PaymentDetailVo;
import com.ly.mt.core.base.entity.payment.PaymentOrderVo;
import com.ly.mt.core.base.entity.trade.TradeOrderRefundInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaymentServiceMapper {
    /**
     * @Description 插入支付流水
     */
    void insertPaymentDetail(PaymentDetailVo paymentDetailVo);

    /**
     * @Description 根据订单号获取订单信息
     */
    List<PaymentOrderVo> getOrderByOrderNo(Map<String, String> map);

    /**
     * 根据purchaseId查询购货单信息
     * @param map
     * @return
     */
    PaymentOrderVo getPurchaseOrderById(Map<String, String> map);

    /**
     * @Description 根据订单号获取最小的订单状态
     * @Author taoye
     */
    int getOrderStatusByOrderNo(PaymentOrderVo orderVo);

    /**
     * @Description 更新订单支付状态
     */
    void updateOrderStatusPaymentSuccess(PaymentOrderVo paymentOrderVo);

    /**
     * 根据退款单号获取退款单信息
     * @param refundNo
     * @return
     */
    TradeOrderRefundInfo getTradeOrderRefundInfoById(@Param("refundNo") Long refundNo);


    /**
     * 根据退款单号更新退款单状态
     * @param refundNo
     * @return
     */
    int updateTradeOrderRefundStatusById(@Param("refundNo") Long refundNo);

}