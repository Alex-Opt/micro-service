package com.ly.mt.cabinet.b.service;

import com.ly.mt.cabinet.b.common.request.WithdrawalVO;
import com.ly.mt.cabinet.b.common.request.WxUserVo;
import com.ly.mt.cabinet.b.util.Resp;

public interface WithdrawalService {

    /**
     * 检查微信用户信息
     * @param userId
     * @return
     */
    Resp checkWxUser(String userId);
    /**
     * 绑定微信用户信息
     * @param wxUserVo
     * @return
     */
    Resp addWxUser(WxUserVo wxUserVo);

    /**
     * 解除绑定微信用户信息
     * @param
     * @return
     */
    Resp deleteWxUser(long userId);

    /**
     * 根据userId查询用户奖励金额
     * @param
     * @return
     */
    Resp queryReplenishReward(WithdrawalVO withdrawalVO);
    /**
     * 申请提现
     * @param withdrawalAmount
     * @return
     */
    Resp applyWithdrawal(String withdrawalAmount, String userId, String ip,String type);

    /**
     * 查询提现结果
     * @return
     */
    Resp queryWithdrawal(String tradeNo);

    Resp alWithdrawal();
}
