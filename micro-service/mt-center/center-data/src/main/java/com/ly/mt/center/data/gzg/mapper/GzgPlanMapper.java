package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgPlan;
import com.ly.mt.center.data.gzg.entity.GzgReplenishOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgPlanMapper {

    GzgPlan getGzgPlan(GzgPlan gzgPlan);


}