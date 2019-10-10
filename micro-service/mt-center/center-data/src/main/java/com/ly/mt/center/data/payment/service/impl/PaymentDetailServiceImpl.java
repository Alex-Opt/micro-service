package com.ly.mt.center.data.payment.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.payment.entity.PaymentDetail;
import com.ly.mt.center.data.payment.mapper.PaymentDetailMapper;
import com.ly.mt.center.data.payment.service.PaymentDetailService;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class PaymentDetailServiceImpl extends BaseServiceImpl implements PaymentDetailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PaymentDetailServiceImpl.class);
    @Resource
    PaymentDetailMapper mapper;

    /**
     * @Description 插入PaymentDetail
     * @Author taoye
     */
    @Override
    public ResponseJson insertPaymentDetail(JSONObject jsonObject) {
        try {
            PaymentDetail paymentDetail = JSONObject.toJavaObject(jsonObject, PaymentDetail.class);
            if (StringUtil.isEmpty(paymentDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertPaymentDetail(paymentDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PaymentDetailServiceImpl.insertPaymentDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}