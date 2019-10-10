package com.ly.mt.core.data.cabinet.mapper;

import com.ly.mt.core.data.cabinet.entity.CabinetPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CabinetPlan操作接口
 *
 * @author taoye
 */
@Mapper
public interface CabinetPlanMapper {
    /**
     * 查询CabinetPlan
     *
     * @param cabinetPlan 查询条件
     * @return CabinetPlan
     * @author taoye
     */
    CabinetPlan getCabinetPlan(CabinetPlan cabinetPlan);

    /**
     * 查询List<CabinetPlan>
     *
     * @param cabinetPlan 查询条件
     * @return List<CabinetPlan>
     * @author taoye
     */
    List<CabinetPlan> listCabinetPlan(CabinetPlan cabinetPlan);
}