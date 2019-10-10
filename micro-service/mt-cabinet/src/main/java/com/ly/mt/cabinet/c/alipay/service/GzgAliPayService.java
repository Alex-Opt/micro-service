package com.ly.mt.cabinet.c.alipay.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.c.alipay.entity.AliPayReqVO;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.Map;

/**
 * alipay
 * @author evan
 * @date 2019-06-04
 */
public interface GzgAliPayService {
    /**
     * 支付宝支付
     * @param aliPayReqVO
     * @return
     */
    public ResponseJson pay(AliPayReqVO aliPayReqVO) throws Exception;

    /**
     * 异步通知
     * @param map
     */
    public ResponseJson aliNotify(Map<String,String> map);

    /**
     * 查询订单
     * @param json
     * @return
     */
    public boolean query(String json) throws Exception;

    /**
     * 支付结果
     * @param orderNo
     * @return
     */
    public ResponseJson payResult(String orderNo) throws Exception;
}
