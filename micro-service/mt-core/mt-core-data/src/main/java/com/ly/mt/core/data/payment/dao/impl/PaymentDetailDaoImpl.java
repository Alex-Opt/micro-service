package com.ly.mt.core.data.payment.dao.impl;

import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.payment.dao.PaymentDetailDao;
import com.ly.mt.core.data.payment.entity.PaymentDetail;
import com.ly.mt.core.data.payment.mapper.PaymentDetailMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;

/**
 * PaymentDetail操作接口
 *
 * @author taoye
 */
@Service
public class PaymentDetailDaoImpl extends BaseDaoServiceImpl implements PaymentDetailDao {
    @Resource
    private PaymentDetailMapper mapper;

    @Override
    public PaymentDetail getPaymentDetailFromMysql(PaymentDetail paymentDetail) {
        paymentDetail = mapper.getPaymentDetail(paymentDetail);
        if (null != paymentDetail) {
            return paymentDetail;
        } else {
            return new PaymentDetail();
        }
    }
}