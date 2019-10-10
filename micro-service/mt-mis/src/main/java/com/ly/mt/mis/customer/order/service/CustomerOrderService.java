package com.ly.mt.mis.customer.order.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 客服后台-查询工具-订单查询
 *
 * @author taoye
 */
public interface CustomerOrderService {
    /**
     * 订单信息分页表格
     *
     * @author taoye
     */
    ResponseJson loadOrderDatagrid(JSONObject jsonObject) throws Exception;
}
