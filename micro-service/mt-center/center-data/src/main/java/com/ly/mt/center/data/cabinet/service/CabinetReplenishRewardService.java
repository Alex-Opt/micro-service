package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 个人奖励中心
 * @author zhanglifeng
 * @date 2019-08-28
 */
public interface CabinetReplenishRewardService {
    /**
     * 如果没有用户的奖励信息，则保存一条用户的奖励信息
     * @param jsonObject
     * @return
     */
    ResponseJson insertCabinetReplenishReward(JSONObject jsonObject);

    /**
     * 根据userId,type查询用户的奖励记录
     * @param jsonObject
     * @return
     */
    ResponseJson queryCabinetReplenishRewardByUserId(JSONObject jsonObject);

    /**
     * 根据id查询一条用户的奖励记录
     * @param jsonObject
     * @return
     */
    ResponseJson queryCabinetReplenishRewardById(JSONObject jsonObject);

    /**
     * 如果存在用户的奖励，则更新对应的金额信息
     * @param jsonObject
     * @return
     */
    ResponseJson updateCabinetReplenishRewardById(JSONObject jsonObject);

    /**
     * 修改用户的可提现金额和累计提现金额
     * @param jsonObject
     * @return
     */
    ResponseJson updateReplenishRewardById(JSONObject jsonObject);
}
