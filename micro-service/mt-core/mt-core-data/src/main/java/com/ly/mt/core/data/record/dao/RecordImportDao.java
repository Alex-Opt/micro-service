package com.ly.mt.core.data.record.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.record.entity.RecordImport;

/**
 * RecordImport操作接口
 *
 * @author taoye
 */
public interface RecordImportDao {
    /**
     * 新增RecordImport
     *
     * @param recordImport 新增数据
     * @author taoye
     */
    void insertRecordImport(RecordImport recordImport);

    /**
     * 更新RecordImport
     *
     * @param recordImport 更新条件和数据
     * @author taoye
     */
    int updateRecordImport(RecordImport recordImport);

    /**
     * easyui datagrid 查询
     *
     * @param recordImport 查询条件
     * @param page         分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(RecordImport recordImport, Page page);
}