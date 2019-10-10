package com.ly.mt.core.data.order.mapper;

import com.ly.mt.core.data.order.entity.OrderHcView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderHcView操作接口
 *
 * @author taoye
 */
@Mapper
public interface OrderHcViewMapper {
    /**
     * 查询OrderHcView
     *
     * @param orderHcView 查询条件
     * @return OrderHcView
     * @author taoye
     */
    OrderHcView getOrderHcView(OrderHcView orderHcView);

    /**
     * 查询List<OrderHcView>
     *
     * @param orderHcView 查询条件
     * @return List<OrderHcView>
     * @author taoye
     */
    List<OrderHcView> listOrderHcView(OrderHcView orderHcView);
}