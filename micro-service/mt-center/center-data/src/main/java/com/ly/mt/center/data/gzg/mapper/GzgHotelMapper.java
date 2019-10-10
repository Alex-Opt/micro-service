package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgHotel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgHotelMapper {
    /**
     * @Description 保存GzgHotel
     * @Author taoye
     */
    void insertGzgHotel(GzgHotel gzgHotel);

    /**
     * @Description 删除GzgHotel
     * @Author taoye
     */
    void deleteGzgHotel(GzgHotel gzgHotel);

    /**
     * @Description 更新GzgHotel
     * @Author taoye
     */
    int updateGzgHotel(GzgHotel gzgHotel);

    /**
     * @Description 查询GzgHotel
     * @Author taoye
     */
    GzgHotel getGzgHotel(GzgHotel gzgHotel);
}