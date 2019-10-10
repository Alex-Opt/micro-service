package com.ly.mt.core.data.cabinet.mapper;

import com.ly.mt.core.data.cabinet.entity.CabinetReplenishReward;
import com.ly.mt.core.data.cabinet.entity.CabinetRewardRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CabinetRewardRecordMapper {

    /**
     * 根据create_time,查询以前的未结算明细记录
     *
     * @param create_time
     * @return
     */
    List<CabinetRewardRecord> getRecordList(String create_time);

    /**
     * 根据idList,批量更新奖励记录的结算状态
     * @param idList
     * @param update_time
     * @return
     */
    int updateCabinetRewardRecordByIdList(@Param("idList") List idList, @Param("update_time") String update_time);

    /**
     * 根据id更新奖励记录的结算状态
     * @param cabinetRewardRecord
     * @return
     */
    int updateRecordById(CabinetRewardRecord cabinetRewardRecord);
}