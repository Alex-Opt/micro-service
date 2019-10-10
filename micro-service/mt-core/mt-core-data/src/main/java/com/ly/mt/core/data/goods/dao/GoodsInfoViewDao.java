package com.ly.mt.core.data.goods.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.goods.entity.GoodsInfoView;

/**
 * GoodsInfoView操作接口
 *
 * @author taoye
 */
public interface GoodsInfoViewDao {
    /**
     * 从mysql查询easyui datagrid
     *
     * @param goodsInfoView 查询条件
     * @param page          分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(GoodsInfoView goodsInfoView, Page page);
}