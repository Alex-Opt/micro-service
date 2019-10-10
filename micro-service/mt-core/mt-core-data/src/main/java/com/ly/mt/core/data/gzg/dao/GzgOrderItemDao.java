package com.ly.mt.core.data.gzg.dao;

import com.ly.mt.core.data.gzg.entity.GzgOrderItem;

import java.util.List;

/**
 * GzgOrderItem操作接口
 *
 * @author taoye
 */
public interface GzgOrderItemDao {
    /**
     * 从reids根据orderId查询List<GzgOrderItem>
     * redis不存在则查询mysql
     *
     * @param orderId 订单ID
     * @return List<GzgOrderItem>
     * @author taoye
     */
    List<GzgOrderItem> listGzgOrderItemByOrderIdFromRedis(String orderId);
}