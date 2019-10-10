package com.ly.mt.center.data.payment.mapper;

import com.ly.mt.center.data.payment.entity.PaymentDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDetailMapper {
    /**
     * @Description 插入PaymentDetail
     * @Author taoye
     */
    void insertPaymentDetail(PaymentDetail paymentDetail);
}