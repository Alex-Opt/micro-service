package com.ly.mt.core.data.cabinet.mapper;

import com.ly.mt.core.data.cabinet.entity.CabinetCashRecord;
import com.ly.mt.core.data.cabinet.entity.CabinetReplenishReward;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CabinetReplenishRewardMapper {

    /**
     * 查询奖励数据
     * @param userId
     * @param type
     * @return
     */
    CabinetReplenishReward getUserCabinetRewardByUserId(@Param("userId") String userId,@Param("type") String type);

    /**
     * 根据id更新用户的奖励信息
     * @param cabinetReplenishReward
     * @return
     */
    int updateRewardById(CabinetReplenishReward cabinetReplenishReward);
}