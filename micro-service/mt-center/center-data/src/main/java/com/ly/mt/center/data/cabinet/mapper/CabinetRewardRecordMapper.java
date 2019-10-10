package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetReplenishReward;
import com.ly.mt.center.data.cabinet.entity.CabinetRewardRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 奖励明细记录
 */
@Mapper
public interface CabinetRewardRecordMapper {
    /**
     * 插入一条数据
     * @param cabinetRewardRecord
     * @return
     */
    int insert(CabinetRewardRecord cabinetRewardRecord);

    /**
     * 根据店铺id查询奖励明细
     * @param store_id  店铺id
     * @return
     */
    List<CabinetRewardRecord> queryRecordByStoreId(String store_id);

    /**
     * 根据主键id查询一条奖励明细数据
     * @param id
     * @return
     */
    CabinetRewardRecord queryRecordById(String id);


    /**
     * 根据id更新奖励记录的结算状态
     * @param cabinetRewardRecord
     * @return
     */
    int updateRecordById(CabinetRewardRecord cabinetRewardRecord);


    /**
     *根据create_time,查询以前的未结算明细记录
     * @param cabinetRewardRecord
     * @return
     */
    List<CabinetRewardRecord>  queryRecordListByCreateTime(CabinetRewardRecord cabinetRewardRecord);
    /**
     * 根据id,批量更新奖励记录的结算状态
     * @param idArray, update_time
     * @return
     */
    int updateRewardByIdArray(@Param("idArray") String[] idArray, @Param("update_time") String update_time);
}
