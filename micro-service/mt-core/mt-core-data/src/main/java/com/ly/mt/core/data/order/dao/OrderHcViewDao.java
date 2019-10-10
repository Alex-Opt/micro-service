package com.ly.mt.core.data.order.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.order.entity.OrderHcView;

import java.util.List;

/**
 * OrderHcView操作接口
 *
 * @author taoye
 */
public interface OrderHcViewDao {
    /**
     * 从redis根据id查询OrderHcView
     * redis不存在则查询mysql
     *
     * @param id 订单ID
     * @return OrderHcView
     * @author taoye
     */
    OrderHcView getOrderHcViewByIdFromRedis(String id);

    /**
     * 从mysql查询easyui datagrid
     *
     * @param orderHcView 查询条件
     * @param page        分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(OrderHcView orderHcView, Page page);

    /**
     * 从mysql查询List<OrderHcView>
     *
     * @param orderHcView 查询条件
     * @return List<OrderHcView>
     * @author taoye
     */
    List<OrderHcView> listOrderHcViewFromMysql(OrderHcView orderHcView);
}