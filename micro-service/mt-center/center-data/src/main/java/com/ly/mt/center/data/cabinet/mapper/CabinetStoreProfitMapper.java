package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetStoreProfit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CabinetStoreProfitMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetStoreProfit record);

    int insertSelective(CabinetStoreProfit record);

    CabinetStoreProfit selectByPrimaryKey(String id);

    CabinetStoreProfit getCabinetStoreProfitByStoreId(CabinetStoreProfit record);

    int updateByPrimaryKeySelective(CabinetStoreProfit record);

    int updateByPrimaryKey(CabinetStoreProfit record);
}