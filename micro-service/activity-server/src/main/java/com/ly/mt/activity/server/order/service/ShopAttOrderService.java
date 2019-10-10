package com.ly.mt.activity.server.order.service;

import com.alibaba.fastjson.JSONObject;


/**
 * @description 门店活动非在线支付形式 order 服务
 *
 * @author panjingtian
 * @date 2019/6/12 5:57 PM
 */
public interface ShopAttOrderService {


    /**
     * 门店查询所有
     * @param jsonObject {@link com.ly.mt.core.common.entity.hd.vo.HdShopAttDetailVo} 商家信息对象
     * @return 分页查询结果
     */
    JSONObject shopOrders(JSONObject jsonObject);


    /**
     * 商家修改订单状态
     * 单号，订单状态，取货码
     * @param jsonObject
     *
     * string orderId
     * string orderStatus
     * string getProductCode
     *
     * @return
     */
    JSONObject shopChangeOrderStatus(JSONObject jsonObject);

    /**
     * 买家查询订单
     * @param jsonObject
     *
     * @return
     */
    JSONObject buyerGetOrder(JSONObject jsonObject);

}
