package com.ly.mt.order.server.activity.service;

import com.ly.mt.core.base.entity.trade.OrderModel;

/**
 * 参与促销优惠活动的等级用户使用明细表持久层
 *
 * @author zhanglifeng
 * @date 2019-05-27
 */
public interface ActivityUserGradeDetailService {
    /**
     * 批量新增
     *
     * @param orderModel
     * @return
     */
    int batchInsertActivityUserGradeDetail(OrderModel orderModel) throws  Exception;
}
