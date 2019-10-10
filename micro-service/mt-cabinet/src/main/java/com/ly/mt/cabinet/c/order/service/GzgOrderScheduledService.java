package com.ly.mt.cabinet.c.order.service;

import com.ly.mt.cabinet.c.order.entity.WebOrderVo;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgOrderScheduledService {
    /**
     * 订单生成后一段时间判断订单是否支付，若未支付，超时，更新字段状态
     * @param orderId
     * @return
     * @throws Exception
     */
    void gzgOrderScheduled(String orderId) throws Exception;


}
