package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.core.common.entity.gzg.GzgHotel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgHotelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GzgHotel record);

    int insertSelective(GzgHotel record);

    GzgHotel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GzgHotel record);

    int updateByPrimaryKey(GzgHotel record);
}