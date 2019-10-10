package com.ly.mt.task.order.service;

import com.alibaba.fastjson.JSONObject;

public interface WithdrawalService {

    /**
     * 根据微信提现申请成功的数据， 查询微信提现处理结果，维护提现流水表
     * @throws Exception
     */
    void processQueryWithdrawal() throws  Exception;

    /**
     * 结算奖励明细记录金额，（7天前的）
     * @throws Exception
     */
    void processRefreshRewardRecord() throws  Exception;

}