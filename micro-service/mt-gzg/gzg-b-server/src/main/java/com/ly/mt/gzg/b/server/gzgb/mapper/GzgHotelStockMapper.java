package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.core.common.entity.gzg.GzgHotelStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GzgHotelStockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GzgHotelStock record);

    int insertSelective(GzgHotelStock record);

    GzgHotelStock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GzgHotelStock record);

    int updateByPrimaryKey(GzgHotelStock record);

    List<Long> findByHotelId(Long hotelId);

    GzgHotelStock findByHotelIdAndSkuId(@Param("hotelId") Long hotelId, @Param("skuId")Long skuId);

    List<GzgHotelStock> findAllByHotelId(@Param("hotelId") Long hotelId);

    GzgHotelStock findByHIdAndSId(@Param("hotelId") Long hotelId, @Param("skuId") Long skuId);
}