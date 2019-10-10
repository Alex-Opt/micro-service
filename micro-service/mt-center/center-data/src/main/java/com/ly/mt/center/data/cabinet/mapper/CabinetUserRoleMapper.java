package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetUserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CabinetUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetUserRole record);

    int insertSelective(CabinetUserRole record);

    CabinetUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CabinetUserRole record);

    int updateByPrimaryKey(CabinetUserRole record);
}