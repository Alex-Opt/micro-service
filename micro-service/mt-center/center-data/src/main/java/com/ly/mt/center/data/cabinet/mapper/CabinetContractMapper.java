package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetContract;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CabinetContractMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetContract record);

    int insertSelective(CabinetContract record);

    CabinetContract selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CabinetContract record);

    int updateByPrimaryKey(CabinetContract record);
}