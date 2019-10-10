package com.ly.mt.core.data.gzg.dao;

import com.ly.mt.core.data.gzg.entity.GzgHotel;

/**
 * GzgHotel操作接口
 *
 * @author taoye
 */
public interface GzgHotelDao {
    /**
     * 从reids根据id查询GzgHotel
     * redis不存在则查询mysql
     *
     * @param id 酒店id
     * @return GzgHotel
     * @author taoye
     */
    GzgHotel getGzgHotelByIdFromRedis(String id);
}