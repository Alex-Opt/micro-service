package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.gzg.b.server.base.entity.GzgChannelCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GzgChannelCodeMapper {
    int updateByPrimaryKey(GzgChannelCode record);
    GzgChannelCode selectByBarCode(@Param("barCode") long barCode);
}