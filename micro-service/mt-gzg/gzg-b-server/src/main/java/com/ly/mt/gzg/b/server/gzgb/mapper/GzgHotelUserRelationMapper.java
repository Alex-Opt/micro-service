package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.core.common.entity.gzg.GzgHotelUserRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GzgHotelUserRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GzgHotelUserRelation record);

    int insertSelective(GzgHotelUserRelation record);

    GzgHotelUserRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GzgHotelUserRelation record);

    int updateByPrimaryKey(GzgHotelUserRelation record);

    int delByHotelIdAndUserId(@Param("hotelId") Long hotelId, @Param("userId") Long userId);

    GzgHotelUserRelation findByHotelIdAndUserId(@Param("hotelId") Long hotelId, @Param("userId")String userId);

    int batchInsert(@Param("list") List<GzgHotelUserRelation> list);

    GzgHotelUserRelation selectByUserId(long userId);

    @Select("select user_id from gzg_hotel_user_relation where hotel_id=#{hotelId} and tyep=#{type}")
    List<Long> selectByHotelIdAndType(@Param("hotelId") long hotelId,@Param("type") int type);
}