package com.ly.mt.core.data.order.mapper;

import com.ly.mt.core.data.order.entity.OrderHbsView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderHbsView操作接口
 *
 * @author taoye
 */
@Mapper
public interface OrderHbsViewMapper {
    /**
     * 查询OrderHbsView
     *
     * @param orderHbsView 查询条件
     * @return OrderHbsView
     * @author taoye
     */
    OrderHbsView getOrderHbsView(OrderHbsView orderHbsView);

    /**
     * 查询List<OrderHbsView>
     *
     * @param orderHbsView 查询条件
     * @return List<OrderHbsView>
     * @author taoye
     */
    List<OrderHbsView> listOrderHbsView(OrderHbsView orderHbsView);
}