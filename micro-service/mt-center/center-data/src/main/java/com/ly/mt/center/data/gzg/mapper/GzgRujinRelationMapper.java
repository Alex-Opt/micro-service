package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgPlan;
import com.ly.mt.center.data.gzg.entity.GzgRujinRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GzgRujinRelationMapper {

    List<GzgRujinRelation> getGzgRujinRelationListByNameAndTid();


    GzgRujinRelation getGzgRujinRelationListByName(GzgRujinRelation gzgRujinRelation);

    GzgRujinRelation getGzgRujinRelationByTid(GzgRujinRelation gzgRujinRelation);

    GzgRujinRelation getGzgRujinRelationByTname(GzgRujinRelation gzgRujinRelation);

    void updateGzgRujinRelationById(GzgRujinRelation gzgRujinRelation);

    /**
     * 插入关系表
     * @param gzgRujinRelation
     * @return
     */
    int insertGzgRujinRelation(GzgRujinRelation gzgRujinRelation);


    GzgRujinRelation getGzgRujinRelationByNameAndTname(GzgRujinRelation gzgRujinRelation);


}