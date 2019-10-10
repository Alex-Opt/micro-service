package com.ly.mt.purchase.server.order.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 进货订单页面
 *
 * @author xiaobei-ihmhny
 * @date 2019-6-26 6:2:30
 */
public interface BOrderService {


    /**
     * B端 订单确认页相关信息
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getOrderInfo(String json) throws Exception;

}
