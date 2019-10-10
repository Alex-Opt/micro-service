package com.ly.mt.core.data.cabinet.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.cabinet.entity.CabinetCategroy;

import java.util.List;

/**
 * CabinetCategroy操作接口
 *
 * @author taoye
 */
public interface CabinetCategroyDao {
    /**
     * 新增CabinetCategroy
     *
     * @param cabinetCategroy 新增数据
     * @author taoye
     */
    void insertCabinetCategroy(CabinetCategroy cabinetCategroy);

    /**
     * 更新CabinetCategroy
     *
     * @param cabinetCategroy 更新条件和数据
     * @return 更新条数
     * @author taoye
     */
    int updateCabinetCategroy(CabinetCategroy cabinetCategroy);

    /**
     * 从redis根据id查询CabinetCategroy
     *
     * @param id 查询条件
     * @return CabinetCategroy
     * @author taoye
     */
    CabinetCategroy getCabinetCategroyByIdFromMysql(String id);

    /**
     * 从mysql查询CabinetCategroy
     *
     * @param cabinetCategroy 查询条件
     * @return CabinetCategroy
     * @author taoye
     */
    CabinetCategroy getCabinetCategroyFromMysql(CabinetCategroy cabinetCategroy);

    /**
     * 从mysql查询List<CabinetCategroy>
     *
     * @param cabinetCategroy 查询条件
     * @return List<CabinetCategroy>
     * @author taoye
     */
    List<CabinetCategroy> listCabinetCategroyFromMysql(CabinetCategroy cabinetCategroy);

    /**
     * 从mysql查询easyui datagrid
     *
     * @param cabinetCategroy 查询条件
     * @param page            分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(CabinetCategroy cabinetCategroy, Page page);
}