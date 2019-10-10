package com.ly.mt.order.server.trade.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单主表接口层
 *
 * @author zhanglifeng
 * @date 2019-05-21
 */
public interface TradeOrdersService {

    /**
     * 按订单主键id查询出一条订单
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject selectByPrimaryKey(String json) throws Exception;

    /**
     * 按订单主键id更新一条订单
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject updateByPrimaryKey(String json) throws Exception;

    /**
     * 查询用户的订单列表
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject queryOrderListByUserId(String json) throws Exception;

    /**
     * 查询用户购买历史，首单判断用
     *
     * @return true-是首单，false-不是首单
     * @throws Exception
     */
    Integer queryOrderByUserId(String userId) throws Exception;

    /**
     * 根据订单id查询订单的详情
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject queryOrderDetailByOrderId(String json) throws Exception;

    /**
     * 获取下单页面-预订单的价格等数据展示
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject queryPreOrderInfo(String json) throws Exception;

    /**
     * 下订单，订单生成
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject orderGenerate(String json) throws Exception;

    /**
     * 订单管理-校验订单中使用的优惠活动是否有效接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject orderCheck(String json) throws Exception;


    /**
     * 订单管理-取消订单
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject cancelOrder(String json) throws Exception;


    /**
     * 订单管理-查询订单的物流信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getOrderDeliveryInfo(String json) throws Exception;


    /**
     * 活动页面下单
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject generatePagePromotionOrder(String json) throws Exception;

    /**
     * 根据用户收藏车信息，实现预订单功能
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject preOrderByStoryCar(String json) throws Exception;


    /**
     * 根据快递的算法得到快递的费用
     *
     * @param distributeId
     * @param money
     * @return
     */
    String getFeignByDistributeId(String distributeId, String money, String userId) throws Exception;

    /**
     * 检查当前用户的收货地址是否支持一小时达
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject orderCheckAddressHour(String json) throws Exception;

    /**
     * 检查当前用户的收货地址是否支持次日达
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject orderCheckAddressDay(String json) throws Exception;


    /**
     * 检查当前用户是否拥有蓝牙产品购买资格
     *
     * @param json
     * @throws Exception
     */
    JSONObject orderCheckBlueTooth(String json) throws Exception;


    /**
     * 活动短信模板查询物流信息
     *
     * @param json
     * @return
     */
    JSONObject getOrderDeliveryInfoSms(String json) throws Exception;

}
