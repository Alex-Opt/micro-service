package com.ly.mt.core.data.cabinet.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.cabinet.entity.CabinetPlan;

/**
 * CabinetPlan操作接口
 *
 * @author taoye
 */
public interface CabinetPlanDao {
    /**
     * 从redis根据id查询CabinetPlan
     *
     * @param id id
     * @return CabinetPlan
     * @author taoye
     */
    CabinetPlan getCabinetPlanByIdFromRedis(String id);

    /**
     * 从mysql查询CabinetPlan
     *
     * @param cabinetPlan 查询条件
     * @return CabinetPlan
     * @author taoye
     */
    CabinetPlan getCabinetPlanFromMysql(CabinetPlan cabinetPlan);

    /**
     * 从mysql查询easyui datagrid
     *
     * @param cabinetPlan 查询条件
     * @param page        分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(CabinetPlan cabinetPlan, Page page);
}