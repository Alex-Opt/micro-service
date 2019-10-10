package com.ly.mt.core.data.order.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.order.entity.OrderHbpView;

import java.util.List;

/**
 * OrderHbpView操作接口
 *
 * @author taoye
 */
public interface OrderHbpViewDao {
    /**
     * 从redis根据id查询OrderHbpView
     * redis不存在则查询mysql
     *
     * @param id 订单ID
     * @return OrderHbpView
     * @author taoye
     */
    OrderHbpView getOrderHbpViewByIdFromRedis(String id);

    /**
     * 从mysql查询easyui datagrid
     *
     * @param orderHbpView 查询条件
     * @param page         分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(OrderHbpView orderHbpView, Page page);

    /**
     * 从mysql查询List<OrderHbpView>
     *
     * @param orderHbpView 查询条件
     * @return List<OrderHbpView>
     * @author taoye
     */
    List<OrderHbpView> listOrderHbpViewFromMysql(OrderHbpView orderHbpView);
}