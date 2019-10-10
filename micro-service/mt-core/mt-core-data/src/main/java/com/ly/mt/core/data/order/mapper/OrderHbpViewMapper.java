package com.ly.mt.core.data.order.mapper;

import com.ly.mt.core.data.order.entity.OrderHbpView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderHbpView操作接口
 *
 * @author taoye
 */
@Mapper
public interface OrderHbpViewMapper {
    /**
     * 查询OrderHbpView
     *
     * @param orderHbpView 查询条件
     * @return OrderHbpView
     * @author taoye
     */
    OrderHbpView getOrderHbpView(OrderHbpView orderHbpView);

    /**
     * 查询List<OrderHbpView>
     *
     * @param orderHbpView 查询条件
     * @return List<OrderHbpView>
     * @author taoye
     */
    List<OrderHbpView> listOrderHbpView(OrderHbpView orderHbpView);
}