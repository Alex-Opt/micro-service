package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetStoreProfitLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CabinetStoreProfitLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetStoreProfitLog record);

    int insertSelective(CabinetStoreProfitLog record);

    CabinetStoreProfitLog selectByPrimaryKey(String id);

    @Select("select * from cabinet_store_profit_log where gzg_order_id = #{gzg_order_id} limit 1")
    CabinetStoreProfitLog getCabinetStoreProfitLog(@Param("gzg_order_id") String gzg_order_id);


    int updateByPrimaryKeySelective(CabinetStoreProfitLog record);

    int updateByPrimaryKey(CabinetStoreProfitLog record);
}