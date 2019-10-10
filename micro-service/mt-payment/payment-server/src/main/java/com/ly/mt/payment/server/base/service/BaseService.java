package com.ly.mt.payment.server.base.service;

import com.alipay.api.AlipayClient;
import com.ly.mt.core.base.entity.payment.PaymentOrderVo;
import com.ly.mt.core.redis.RedisKey;

import java.util.Map;
import java.util.TreeMap;

public interface BaseService {
    AlipayClient getAlipayClient();

    String getWeChatSign(TreeMap<String, Object> map, String merchantKey) throws Exception;

    PaymentOrderVo getH5PaymentOrder(RedisKey re, String orderNo);

    /*PaymentOrderVo getBDuanPaymentOrder(RedisEnum re, String orderNo);*/

    /**
     * @Description 调用外部接口——post
     * @Author taoye
     */
    String postExternalServer(String serverUrl, String param);

    /**
     * @Description 调用外部接口——get
     * @Author taoye
     */
    String getExternalServer(String serverUrl, Map<String, Object> map);

}