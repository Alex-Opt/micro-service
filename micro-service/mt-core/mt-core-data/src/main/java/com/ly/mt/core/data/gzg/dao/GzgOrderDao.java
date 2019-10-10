package com.ly.mt.core.data.gzg.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.gzg.entity.GzgOrder;

import java.util.List;

/**
 * GzgOrder操作接口
 *
 * @author taoye
 */
public interface GzgOrderDao {
    /**
     * 从reids根据id查询GzgOrder
     * redis不存在则查询mysql
     *
     * @param id id
     * @return GzgOrder
     * @author taoye
     */
    GzgOrder getGzgOrderByIdFromRedis(String id);

    /**
     * 从mysql查询List<GzgOrder>
     *
     * @param gzgOrder 查询条件
     * @return List<GzgOrder>
     * @author taoye
     */
    List<GzgOrder> listGzgOrderFromMysql(GzgOrder gzgOrder);

    /**
     * 从mysql查询easyui datagrid
     *
     * @param gzgOrder 查询条件
     * @param page     分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(GzgOrder gzgOrder, Page page);
}