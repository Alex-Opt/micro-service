package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.gexin.fastjson.JSON;
import com.gexin.fastjson.TypeReference;
import com.ly.mt.core.common.entity.gzg.GzgOrderVo;
import com.ly.mt.gzg.b.server.base.entity.request.RefundReqVO;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgOrdersServiceMapper;
import com.ly.mt.gzg.b.server.gzgb.service.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RefundServiceImpl implements RefundService {

    @Autowired
    private GzgOrdersServiceMapper gzgOrdersServiceMapper;
    @Override
    public JSONObject wxRefund(String json) {
        log.info("call wx refund param-data={}",json);
        long start = System.currentTimeMillis();
        RefundReqVO refundReqVO = JSON.parseObject(json,new TypeReference<RefundReqVO>(){});
        GzgOrderVo gzgOrderVo = gzgOrdersServiceMapper.selectByPrimaryKey(refundReqVO.getOrderId());
        log.info("the data of gzg-order={}",gzgOrderVo);
        Double orderMoney = gzgOrderVo.getOrderMoney();
        //AlipayClient alipayClient = new DefaultAlipayClient();
        return null;
    }

    @Override
    public JSONObject alipayRefund(String json) {
        return null;
    }
}
