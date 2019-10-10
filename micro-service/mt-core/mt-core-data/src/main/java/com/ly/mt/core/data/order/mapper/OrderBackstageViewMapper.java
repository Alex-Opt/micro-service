package com.ly.mt.core.data.order.mapper;

import com.ly.mt.core.data.order.entity.OrderBackstageView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderBackstageView操作接口
 *
 * @author taoye
 */
@Mapper
public interface OrderBackstageViewMapper {
    /**
     * 查询List<OrderBackstageView>
     *
     * @param orderBackstageView 查询条件
     * @return List<OrderBackstageView>
     * @author taoye
     */
    List<OrderBackstageView> listOrderBackstageView(OrderBackstageView orderBackstageView);
}