package com.ly.mt.order.server.border.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 抢单模块
 *
 * @author zhanglifeng
 * @date 2019-06-13
 */
public interface BOrderBattleService {

    /**
     * 查询小B抢单的列表信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject queryOrderBattleList(String json) throws Exception;

    /**
     * 抢单接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject grabOrder(String json) throws Exception;

    /**
     * 释放抢单接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject releaseGrabOrder(String json) throws Exception;

    /**
     * 取消抢单接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject cancelGrabOrder(String json) throws Exception;

    /**
     * 校验商品有效性接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject checkGoodsEffectiveness(String json) throws Exception;


    /**
     * 确认收货接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject confirmFinishOrder(String json) throws Exception;

    /**
     * 查询校验商品过期时间，订单状态等接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getOrderBattleInfo(String json) throws Exception;

    /**
     * 查询订单商品详情接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject queryOrderDetail(String json) throws Exception;

    /**
     * 商品校验完成，发送订单到蜂鸟系统的接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject sendOrderToHummingBird(String json) throws Exception;

    /**
     * 查询骑手位置接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject queryCarrierPosition(String json) throws Exception;

}
