package com.ly.mt.center.data.basic.mapper;

import com.ly.mt.center.data.basic.entity.CityOneHour;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityOneHourMapper {
    /**
     * @Description 保存CityOneHour
     * @Author taoye
     */
    void insertCityOneHour(CityOneHour cityOneHour);

    /**
     * @Description 删除CityOneHour
     * @Author taoye
     */
    void deleteCityOneHour(CityOneHour cityOneHour);

    /**
     * @Description 更新CityOneHour
     * @Author taoye
     */
    int updateCityOneHour(CityOneHour cityOneHour);

    /**
     * @Description 查询CityOneHour
     * @Author taoye
     */
    CityOneHour getCityOneHour(CityOneHour cityOneHour);

    /**
     * 根据areaId查询
     * @param areaId
     * @return
     */
    @Select("select * from  city_one_hour where area_id = #{areaId}")
    List<CityOneHour> findByAreaId(@Param("areaId") Long areaId);
}