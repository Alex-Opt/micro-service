package com.ly.mt.marketing.server.profits.service;

import com.alibaba.fastjson.JSONObject;

/**
 *@Description 收益日志service
 *@Author  zhuyh
 */
public interface ShopProfitsLogsService {
    /**
     *@Description  获取有奖励的订单
     *@Author  zhuyh
     */
    JSONObject getRewardOrders(String json) throws Exception;

    /**
     *@Description 查询奖励明细
     *@Author  zhuyh
     */
    JSONObject getRewards(String json) throws Exception;

    /**
     *@Description 查询奖励明细累计总数
     *@Author  zhuyh
     */
    JSONObject getRewardsTotal(String json) throws Exception;
    /**
     *@Description 查询奖励明细
     *@Author  zhuyh
     */
    JSONObject getLodes(String json) throws Exception;

    /**
     *@Description 查询奖励明细累计总数
     *@Author  zhuyh
     */
    JSONObject getLodeTotal(String json) throws Exception;

    /**
     *@Description 查询抢单周期累计
     *@Author  zhuyh
     */
    JSONObject getGrabCycleSum(String json) throws Exception;


    /**
     *@Description 分页查询抢单日志
     *@Author  zhuyh
     */
    JSONObject getGrabs(String json) throws Exception;
    /**
     *@Description 查询专属单周期累计
     *@Author  zhuyh
     */
    JSONObject getOrderCycleSum(String json) throws Exception;


    /**
     *@Description 分页查询专属单日志
     *@Author  zhuyh
     */
    JSONObject getOrders(String json) throws Exception;
}
