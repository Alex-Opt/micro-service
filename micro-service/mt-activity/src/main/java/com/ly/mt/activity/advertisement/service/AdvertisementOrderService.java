package com.ly.mt.activity.advertisement.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.advertisement.vo.PageOrderVo;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author wanglong
 */
public interface AdvertisementOrderService {

    /**
     * 广告活动下单1
     * @param paramObject
     * @return
     * @throws Exception
     */
    ResponseJson  bookingActivityOrder(JSONObject paramObject)throws Exception;


    /**
     * 2c广告活动下单
     * @param paramObject
     * @return
     * @throws Exception
     */
    ResponseJson  bookingActivityOrder2c(JSONObject paramObject)throws Exception;


    /**
     * 根据订单Id查询订单信息
     * @param paramObject
     * @return
     */
    ResponseJson getOrderInfo2c(JSONObject paramObject);

    /**
     * mojo小烟活动下单 价格19
     * @param paramObjec
     * @return
     */
    ResponseJson bookingGghdOrderLittleSmoke(JSONObject paramObjec);


    /**
     * 下单完成发送短信
     * @param orderId
     * @param mobile
     */
    void sendSms(String orderId,String mobile)throws Exception;


    /**
     * 马蜂窝下单（附赠品）
     * @return
     */
    ResponseJson generateOrderForMafengwo(PageOrderVo pageOrder);
}
