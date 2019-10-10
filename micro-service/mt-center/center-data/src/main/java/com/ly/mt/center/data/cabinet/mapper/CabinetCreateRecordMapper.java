package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetCreateRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CabinetCreateRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetCreateRecord record);

    int insertSelective(CabinetCreateRecord record);

    CabinetCreateRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CabinetCreateRecord record);

    int updateByPrimaryKey(CabinetCreateRecord record);
}