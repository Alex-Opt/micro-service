package com.ly.mt.center.data.cabinet.mapper;
import com.ly.mt.center.data.cabinet.entity.CabinetStoreProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CabinetStorePropertyMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetStoreProperty record);

    int insertSelective(CabinetStoreProperty record);

    CabinetStoreProperty selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CabinetStoreProperty record);

    int updateByPrimaryKey(CabinetStoreProperty record);

    CabinetStoreProperty selectByStoreId(@Param("storeId") String storeId);
}