package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.core.common.entity.gzg.GzgInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GzgInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GzgInfo record);

    int insertSelective(GzgInfo record);

    GzgInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GzgInfo record);

    int updateByPrimaryKey(GzgInfo record);

    int batchInsert(@Param("list") List<GzgInfo> gzgInfos);

    List<GzgInfo> findByHotelId(@Param("hotelId") Long hotelId);

    GzgInfo selectByGzgCode(@Param("gzgCode") long gzgCode);
}