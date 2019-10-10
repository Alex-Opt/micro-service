package com.ly.mt.order.server.mq.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * mq消费业务处理层
 *
 * @author zhanglifeng
 * @date 2019-06-25
 */
public interface RabbitMqService {
    /**
     * 1.生成发货单信息表 orders_battle_info，并将这条发货单添加到redis的抢单列表中。｛"order_battle_id_发货单id",order_battle_info｝
     * 2.生成符合发货范围的小B列表 order_battle_shop
     * 3.根据发货单中生成的id捞取符合条件的小B，推送信息给小B。这个要用到mq
     * 4.推送成功后要更新一小时达的订单状态为推送成功。也即是新生成的订单只推送一次。
     * 自此，抢单需要的基本信息都已具备。等待小B查看抢单信息，进行抢单。
     *
     * @return
     * @throws Exception
     */
    List<Map<String,String>> handlerHomeBMq(String message);


    /**
     * 发送短信给兜底商家，并推送订单到蜂鸟。
     * @param list
     */
    void sendMsgAndFn(List<Map<String,String>> list);


    /**
     * 蜂鸟回调接口，接受发送的mq，更新订单数据
     *
     * @throws Exception
     */
    void handlerFnCallBack(String message);


    /**
     * 客户同意将订单由一小时变成次日达。
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject agreeNextDayOrder(String json) throws Exception;

    /**
     * 下单成功更新订单状态
     *
     * @param message
     */
    void updateTradeOrderStatus(String message);
}
