package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetStoreEmplooyRelationship;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CabinetStoreEmplooyRelationshipMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetStoreEmplooyRelationship record);

    int insertSelective(CabinetStoreEmplooyRelationship record);

    CabinetStoreEmplooyRelationship selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CabinetStoreEmplooyRelationship record);

    int updateByPrimaryKey(CabinetStoreEmplooyRelationship record);
}