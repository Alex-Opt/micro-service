package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgHotelStock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgHotelStockMapper {
    /**
     * @Description 保存GzgHotelStock
     * @Author taoye
     */
    void insertGzgHotelStock(GzgHotelStock gzgHotelStock);

    /**
     * @Description 删除GzgHotelStock
     * @Author taoye
     */
    void deleteGzgHotelStock(GzgHotelStock gzgHotelStock);

    /**
     * @Description 更新GzgHotelStock
     * @Author taoye
     */
    int updateGzgHotelStock(GzgHotelStock gzgHotelStock);

    /**
     * @Description 查询GzgHotelStock
     * @Author taoye
     */
    GzgHotelStock getGzgHotelStock(GzgHotelStock gzgHotelStock);
}