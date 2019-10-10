package com.ly.mt.core.data.gzg.mapper;

import com.ly.mt.core.data.gzg.entity.GzgHotel;
import org.apache.ibatis.annotations.Mapper;

/**
 * GzgHotel操作接口
 *
 * @author taoye
 */
@Mapper
public interface GzgHotelMapper {
    /**
     * 根据id查询GzgHotel
     *
     * @author taoye
     */
    GzgHotel getGzgHotel(GzgHotel gzgHotel);
}