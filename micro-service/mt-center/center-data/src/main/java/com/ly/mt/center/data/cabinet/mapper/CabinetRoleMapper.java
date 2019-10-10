package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CabinetRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetRole record);

    int insertSelective(CabinetRole record);

    CabinetRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CabinetRole record);

    int updateByPrimaryKey(CabinetRole record);
}