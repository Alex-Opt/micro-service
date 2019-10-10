package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.core.common.entity.gzg.GzgPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GzgPlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GzgPlan record);

    int insertSelective(GzgPlan record);

    GzgPlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GzgPlan record);

    int updateByPrimaryKey(GzgPlan record);

    List<Map<String, String>> findSkuIdAndName(Long hotelId);

    List<Map<Integer,String>> getPlans();
}