package com.ly.mt.core.data.order.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.order.entity.OrderHbsView;

import java.util.List;

/**
 * OrderHbsView操作接口
 *
 * @author taoye
 */
public interface OrderHbsViewDao {
    /**
     * 从redis根据id查询OrderHbsView
     * redis不存在则查询mysql
     *
     * @param id 订单ID
     * @return OrderHbsView
     * @author taoye
     */
    OrderHbsView getOrderHbsViewByIdFromRedis(String id);

    /**
     * 从mysql查询easyui datagrid
     *
     * @param orderHbsView 查询条件
     * @param page         分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(OrderHbsView orderHbsView, Page page);

    /**
     * 从mysql查询List<OrderHbsView>
     *
     * @param orderHbsView 查询条件
     * @return List<OrderHbsView>
     * @author taoye
     */
    List<OrderHbsView> listOrderHbsViewFromMysql(OrderHbsView orderHbsView);
}