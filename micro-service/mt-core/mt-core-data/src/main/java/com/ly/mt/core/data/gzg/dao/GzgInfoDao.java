package com.ly.mt.core.data.gzg.dao;

import com.ly.mt.core.data.cabinet.entity.CabinetPlan;
import com.ly.mt.core.data.gzg.entity.GzgInfo;

import java.util.List;
import java.util.Map;

/**
 * GzgInfo操作接口
 *
 * @author taoye
 */
public interface GzgInfoDao {
    /**
     * 从reids根据code查询GzgInfo
     * redis不存在则查询mysql
     *
     * @param code code
     * @return GzgInfo
     * @author taoye
     */
    GzgInfo getGzgInfoByCodeFromRedis(String code);

    /**
     * 从mysql根据cabinetPlanId统计GzgInfo条数
     *
     * @param cabinetPlans 查询条件
     * @return List<Map<cabinetPlanId, count>>
     * @author taoye
     */
    Map<Long, Map<String, Long>> getCountByCabinetPlansFromMysql(List<CabinetPlan> cabinetPlans);
}