package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.gzg.b.server.base.constant.GzgCabinet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GzgCabinetMapper {
    GzgCabinet selectByCodeAndCabinetCode(@Param("gzgCode") String gzgCode,@Param("cabinetNo") Integer cabinetNo);
    int updateByPrimaryKey(GzgCabinet gzgCabinet);
}
