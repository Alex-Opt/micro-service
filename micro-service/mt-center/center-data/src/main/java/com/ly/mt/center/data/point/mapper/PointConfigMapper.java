package com.ly.mt.center.data.point.mapper;

import com.ly.mt.center.data.point.entity.PointConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointConfigMapper {
    /**
     * @Description 插入PointConfig
     * @Author taoye
     */
    void insertPointConfig(PointConfig pointConfig);

    /**
     * @Description 根据id删除PointConfig
     * @Author taoye
     */
    void deletePointConfigById(PointConfig pointConfig);

    /**
     * @Description 根据id更新PointConfig
     * @Author taoye
     */
    int updatePointConfigById(PointConfig pointConfig);

    /**
     * @Description 根据条件查询PointConfig
     * @Author taoye
     */
    PointConfig getPointConfig(PointConfig pointConfig);

    /**
     * @Description 根据id查询PointConfig
     * @Author taoye
     */
    PointConfig getPointConfigById(PointConfig pointConfig);
}