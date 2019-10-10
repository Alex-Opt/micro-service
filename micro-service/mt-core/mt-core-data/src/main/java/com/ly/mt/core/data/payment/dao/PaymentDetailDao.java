package com.ly.mt.core.data.payment.dao;

import com.ly.mt.core.data.payment.entity.PaymentDetail;

/**
 * PaymentDetail操作接口
 *
 * @author taoye
 */
public interface PaymentDetailDao {
    /**
     * 从mysql查询PaymentDetail
     *
     * @param paymentDetail 查询条件
     * @return PaymentDetail
     * @author taoye
     */
    PaymentDetail getPaymentDetailFromMysql(PaymentDetail paymentDetail);
}