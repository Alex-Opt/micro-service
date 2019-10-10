package com.ly.mt.core.data.gzg.mapper;

import com.ly.mt.core.data.cabinet.entity.CabinetPlan;
import com.ly.mt.core.data.gzg.entity.GzgInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * GzgInfo操作接口
 *
 * @author taoye
 */
@Mapper
public interface GzgInfoMapper {
    /**
     * 查询GzgInfo
     *
     * @author taoye
     */
    GzgInfo getGzgInfo(GzgInfo gzgInfo);

    /**
     * 根据cabinetPlanId统计GzgInfo条数
     *
     * @param cabinetPlans 查询条件
     * @return Map<cabinetPlanId, count>
     * @author taoye
     */
    @MapKey("planId")
    Map<Long, Map<String, Long>> getCountByCabinetPlans(List<CabinetPlan> cabinetPlans);
}