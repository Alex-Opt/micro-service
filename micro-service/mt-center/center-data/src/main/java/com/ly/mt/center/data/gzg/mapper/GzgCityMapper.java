package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgCity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgCityMapper {
    /**
     * @Description 保存GzgCity
     * @Author taoye
     */
    void insertGzgCity(GzgCity gzgCity);

    /**
     * @Description 删除GzgCity
     * @Author taoye
     */
    void deleteGzgCity(GzgCity gzgCity);

    /**
     * @Description 更新GzgCity
     * @Author taoye
     */
    int updateGzgCity(GzgCity gzgCity);

    /**
     * @Description 查询GzgCity
     * @Author taoye
     */
    GzgCity getGzgCity(GzgCity gzgCity);
}