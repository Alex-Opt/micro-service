package com.ly.mt.center.data.cabinet.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CabinetAreaMapper {
    @Select("select name as label,area_id as value from basic_area_trading where area_id=#{mId}")
    List<Map<String,Integer>> selectCircleByMId(@Param("mId") Integer mId);
}
