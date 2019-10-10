package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgBUserRelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgBUserRelationMapper {
    /**
     * @Description 保存GzgBUserRelation
     * @Author taoye
     */
    void insertGzgBUserRelation(GzgBUserRelation gzgBUserRelation);

    /**
     * @Description 删除GzgBUserRelation
     * @Author taoye
     */
    void deleteGzgBUserRelation(GzgBUserRelation gzgBUserRelation);

    /**
     * @Description 更新GzgBUserRelation
     * @Author taoye
     */
    int updateGzgBUserRelation(GzgBUserRelation gzgBUserRelation);

    /**
     * @Description 查询GzgBUserRelation
     * @Author taoye
     */
    GzgBUserRelation getGzgBUserRelation(GzgBUserRelation gzgBUserRelation);
}