package com.ly.mt.marketing.server.profits.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @创建人 zhuyh
 * @描述 收益汇总
 */
public interface ShopProfitsService {

    /**
     *@Description 查询总收益
     *@Author  zhuyh
     */
    JSONObject getProfitsSumByLoginId(String json) throws Exception;

    /**
     *@Description 查询收益排行
     *@Author  zhuyh
     */
    JSONObject getProfitsTop(String json) throws Exception;

    /**
     *@Description 查询抢单奖励收益详情
     *@Author  zhuyh
     */
    JSONObject getRewardProfitsDetails(String json) throws Exception;

    /**
     *@Description 查询抢单收益详情详情
     *@Author  zhuyh
     */
    JSONObject getGrabProfitsDetails(String json) throws Exception;

    /**
     *@Description 查询专属订单收益详情
     *@Author  zhuyh
     */
    JSONObject getOrderProfitsDetails(String json) throws Exception;

    /**
     *@Description 查询淘金收益详情
     *@Author  zhuyh
     */
    JSONObject getLodeProfitsDetails(String json) throws Exception;

    /**
     *@Description 查询正在抢单赚取的人数
     *@Author  zhuyh
     */
    JSONObject getOrderUserCount(String json) throws Exception;
}
