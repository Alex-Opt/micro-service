package com.ly.mt.core.data.cabinet.mapper;

import com.ly.mt.core.data.cabinet.entity.CabinetCashRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CabinetCashRecordMapper {

    int deleteByPrimaryKey(String id);

    int insert(CabinetCashRecord record);

    int insertSelective(CabinetCashRecord record);

    /**
     * 跟据id修改
     * @param record
     * @return
     */
    int updateById(CabinetCashRecord record);

    /**
     * 根据用户id查询今日申请成功数据（status=0,1,2）；当userId为空，查询今日所有成功数据
     * @param
     * @return
     */
    List<CabinetCashRecord> getRecordByUserIdToday(@Param("userId") String userId, @Param("todayStartTime") String todayStartTime, @Param("todayEndTime") String todayEndTime);


    /**
     * 根据状态status查询提现申请数据
     * @param record
     * @return
     */
    List<CabinetCashRecord> getRecordByStatus(CabinetCashRecord record);
    /**
     * 
     * @param id
     * @return
     */
    CabinetCashRecord getByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CabinetCashRecord record);

    int updateByPrimaryKey(CabinetCashRecord record);
}