package com.ly.mt.core.data.payment.mapper;

import com.ly.mt.core.data.payment.entity.PaymentDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * PaymentDetail操作接口
 *
 * @author taoye
 */
@Mapper
public interface PaymentDetailMapper {
    /**
     * 查询PaymentDetail
     *
     * @param paymentDetail 查询条件
     * @return PaymentDetail
     * @author taoye
     */
    PaymentDetail getPaymentDetail(PaymentDetail paymentDetail);
}