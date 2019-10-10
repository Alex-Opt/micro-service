package com.ly.mt.cabinet.c.order.service;

import com.ly.mt.cabinet.c.order.entity.WebOrderVo;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgOrderService {
    /**
     * 用户点击商品行程订单
     * @param webGZG
     * @return
     * @throws Exception
     */
    ResponseJson gzgOrderGenerate(WebOrderVo webGZG) throws Exception;

    /**
     * 根据用户id获取用户所有订单信息
     * @param userId
     * @return
     * @throws Exception
     */
    ResponseJson getGzgOrderListByUserId(String userId) throws  Exception;

    /**
     * 判断订单是否超时或支付完成
     * @param orderId
     * @return
     * @throws Exception
     */
    ResponseJson isOrderTimeOutOrComplete(String orderId) throws  Exception;


    /**
     * 重新支付返回订单信息
     * @param orderId
     * @return
     * @throws Exception
     */
    ResponseJson retryOrder(String orderId,String userId) throws  Exception;


    /**
     * 订单中心查询订单详情
     * @param orderId
     * @return
     * @throws Exception
     */
    ResponseJson orderDetail(String orderId) throws  Exception;


}
