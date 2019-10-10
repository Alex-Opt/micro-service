package com.ly.mt.core.data.order.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.order.entity.OrderBackstageView;

/**
 * OrderBackstageView操作接口
 *
 * @author taoye
 */
public interface OrderBackstageViewDao {
    /**
     * easyui datagrid 查询
     *
     * @param orderBackstageView 查询条件
     * @param page               分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(OrderBackstageView orderBackstageView, Page page);
}