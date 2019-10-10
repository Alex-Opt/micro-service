package com.ly.mt.cabinet.c.order.service;

import com.ly.mt.cabinet.c.order.entity.WebOrderVo;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgOrderSyncCabinetProfitService {
    /**
     * 订单C端完成后异步更新店铺收益信息和收益日志
     * @param
     * @return
     * @throws Exception
     */
    void syncCabinetStoreProfit(String orderId);


}
