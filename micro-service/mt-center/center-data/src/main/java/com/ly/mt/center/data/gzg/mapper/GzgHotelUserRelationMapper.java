package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgHotelUserRelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgHotelUserRelationMapper {
    /**
     * @Description 保存GzgHotelUserRelation
     * @Author taoye
     */
    void insertGzgHotelUserRelation(GzgHotelUserRelation gzgHotelUserRelation);

    /**
     * @Description 删除GzgHotelUserRelation
     * @Author taoye
     */
    void deleteGzgHotelUserRelation(GzgHotelUserRelation gzgHotelUserRelation);

    /**
     * @Description 更新GzgHotelUserRelation
     * @Author taoye
     */
    int updateGzgHotelUserRelation(GzgHotelUserRelation gzgHotelUserRelation);

    /**
     * @Description 查询GzgHotelUserRelation
     * @Author taoye
     */
    GzgHotelUserRelation getGzgHotelUserRelation(GzgHotelUserRelation gzgHotelUserRelation);
}