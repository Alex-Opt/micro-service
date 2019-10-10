package com.ly.mt.core.data.cabinet.dao;

import com.ly.mt.core.data.cabinet.entity.CabinetReplenishReward;

public interface CabinetReplenishRewardDao {

    /**
     * 根据userId和type 查询奖励数据
     * @param userId
     * @param type
     * @return
     */
    CabinetReplenishReward getUserCabinetRewardByUserId(String userId, String type);

    /**
     * 根据id更新用户的奖励信息
     *
     * @param cabinetReplenishReward
     * @return
     */
    int updateRewardById(CabinetReplenishReward cabinetReplenishReward);
}
