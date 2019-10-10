package com.ly.mt.order.server.trade.component;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.border.OrdersBattleInfo;
import com.ly.mt.core.base.entity.delivery.ExpressCompanyInfo;
import com.ly.mt.core.base.entity.trade.OrderModel;
import com.ly.mt.core.base.entity.trade.TradeOrder;
import com.ly.mt.core.base.entity.trade.TradeOrderItem;
import com.ly.mt.core.base.entity.user.User;
import com.ly.mt.core.base.entity.user.UserAddress;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.order.server.activity.service.ActivityUserGradeDetailService;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.coupon.service.CouponCodeService;
import com.ly.mt.order.server.trade.mapper.TradeOrdersServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_ONE_HOUR;
import static com.ly.mt.core.base.dict.WxAppletTemplateSendType.*;
import static com.ly.mt.core.base.method.ThirdServerMethodEnum.WX_TEMPLATE_MESSAGE_SEND;

/**
 * @author zhanglifeng
 * @date 2019-05-28
 * 订单生成的异步处理中心
 */
@Component
public class TradeOrderServiceAsync extends BaseServiceImpl {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderServiceAsync.class);

    @Autowired
    public RedisService redisService;

    @Resource
    private CouponCodeService couponCodeService;

    @Resource
    private ActivityUserGradeDetailService activityUserGradeDetailService;

    @Resource
    private TradeOrdersServiceMapper tradeOrdersServiceMapper;

    /**
     * 订单中心执行的所有异步的方法。
     * 1.activity_user_grade_detail-用户使用活动明细表，写入使用促销活动购买的spu记录。
     * 2.coupon_code-用户使用优惠券表，更新使用的优惠券状态为已使用
     * 3.TODO 操作库存信息
     *
     * @param orderModel
     */
    @Async
    public void recordCouponAndActivityUsedHistory(OrderModel orderModel) throws Exception {
        LOGGER.info("=========================进入异步方法，执行用户================================");
        try {
            couponCodeService.updateCouponCode(orderModel.getCouponList(), orderModel.getOrderVo().getBuyerId());
            activityUserGradeDetailService.batchInsertActivityUserGradeDetail(orderModel);
        } catch (Exception e) {
            LOGGER.error("异步执行异常:", e);
        }
    }

    /**
     * 异步通知用户普通快递信息到微信小程序
     *
     * @param params
     */
    @Async
    public void asyncNotifyUserCommonDeliveryInfo(JSONObject params) {
        String expressNo = params.getString("expressNo");
        String code = params.getString("code");
        String orderNo = params.getString("orderNo");
        Long orderId = params.getLong("orderId");
        Long userId = params.getLong("userId");
        String time = params.getString("time");
        Long addressId = params.getLong("addressId");
        User userInfo = tradeOrdersServiceMapper.getUserInfoById(userId);
        String wxOpenId = userInfo.getWxOpenId();
        if (StringUtil.isNotEmpty(wxOpenId)) {
            List<TradeOrderItem> tradeOrderItems = tradeOrdersServiceMapper.getTradeOrderItemByOrderId(orderId);
            String spuName = tradeOrderItems.get(0).getSpuName();
            ExpressCompanyInfo expressCompanyInfo = tradeOrdersServiceMapper.getExpressCompanyInfoByCode(code);
            String name = expressCompanyInfo.getName();
            JSONObject dataJson = new JSONObject(6);
            JSONObject keyword1 = new JSONObject();
            keyword1.put("value", spuName);
            JSONObject keyword2 = new JSONObject();
            keyword2.put("value", orderNo);
            JSONObject keyword3 = new JSONObject();
            keyword3.put("value", expressNo);
            JSONObject keyword4 = new JSONObject();
            keyword4.put("value", name);
            JSONObject keyword5 = new JSONObject();
            UserAddress userAddress = tradeOrdersServiceMapper.selectByAddressId(addressId);
            if (userAddress != null) {
                keyword5.put("value", userAddress.getUserAddress());
            } else {
                keyword5.put("value", "暂无信息");
            }
            JSONObject keyword6 = new JSONObject();
            keyword6.put("value", time);
            dataJson.put("keyword1", keyword1);
            dataJson.put("keyword2", keyword2);
            dataJson.put("keyword3", keyword3);
            dataJson.put("keyword4", keyword4);
            dataJson.put("keyword5", keyword5);
            dataJson.put("keyword6", keyword6);
            sendWxMsg(wxOpenId, TEMPLATE_SEND_TYPE_COMMON_DELIVERY_RESULT.getId(), dataJson);
        }
    }

    /**
     * 异步通知一小时达订单的用户快递配送信息
     *
     * @param ordersBattleInfo
     * @param statusName
     */
    @Async
    public void asyncNotifyUserOneHourDeliveryInfo(OrdersBattleInfo ordersBattleInfo, String statusName) {
        String buyerId = ordersBattleInfo.getBuyerId();
        User userInfo = tradeOrdersServiceMapper.getUserInfoById(Long.parseLong(buyerId));
        String wxOpenId = userInfo.getWxOpenId();
        if (StringUtil.isNotEmpty(wxOpenId)) {
            String orderId = ordersBattleInfo.getOrderId();
            TradeOrder tradeOrder = tradeOrdersServiceMapper.selectByPrimaryKey(Long.parseLong(orderId));
            String orderMoney = tradeOrder.getOrderMoney();
            String orderNo = tradeOrder.getOrderNo();
            JSONObject dataJson = new JSONObject(6);
            JSONObject keyword1 = new JSONObject();
            keyword1.put("value", orderNo);
            JSONObject keyword2 = new JSONObject();
            keyword2.put("value", orderMoney);
            JSONObject keyword3 = new JSONObject();
            keyword3.put("value", "MOTI到家");
            JSONObject keyword4 = new JSONObject();
            String addressId = tradeOrder.getAddressId();
            UserAddress userAddress = tradeOrdersServiceMapper.selectByAddressId(Long.parseLong(addressId));
            if (userAddress != null) {
                keyword4.put("value", userAddress.getUserAddress());
            } else {
                keyword4.put("value", "暂无信息");
            }
            JSONObject keyword5 = new JSONObject();
            keyword5.put("value", statusName);
            JSONObject keyword6 = new JSONObject();
            keyword6.put("value", ordersBattleInfo.getDeliverymanPhone());
            dataJson.put("keyword1", keyword1);
            dataJson.put("keyword2", keyword2);
            dataJson.put("keyword3", keyword3);
            dataJson.put("keyword4", keyword4);
            dataJson.put("keyword5", keyword5);
            dataJson.put("keyword6", keyword6);
            sendWxMsg(wxOpenId, TEMPLATE_SEND_TYPE_FN_DELIVERY_RESULT.getId(), dataJson);
        }
    }


    /**
     * 确认收货发送微信小程序模版消息通知
     */
    @Async
    public void asyncNotifyUserConfirmReceipt(String spuName, TradeOrder tradeOrders) {
        String buyerId = tradeOrders.getBuyerId();
        User userInfo = tradeOrdersServiceMapper.getUserInfoById(Long.parseLong(buyerId));
        String wxOpenId = userInfo.getWxOpenId();
        if (StringUtil.isNotEmpty(wxOpenId)) {
            String orderNo = tradeOrders.getOrderNo();
            String distributionId = tradeOrders.getDistributionId();
            String orderMoney = tradeOrders.getOrderMoney();
            String expressNo = null;
            //说明是一小时达的，没有物流单号
            if (DISTRIBUTE_TYPE_ONE_HOUR.getId().equals(distributionId)) {
                expressNo = "蜂鸟转送";
            } else {
                expressNo = tradeOrders.getGyLogisticsCode();
            }
            JSONObject dataJson = new JSONObject(5);
            JSONObject keyword1 = new JSONObject();
            keyword1.put("value", "感谢您的支持。MOTI到家平台的所有商品均为官方正品");
            JSONObject keyword2 = new JSONObject();
            keyword2.put("value", spuName);
            JSONObject keyword3 = new JSONObject();
            keyword3.put("value", orderNo);
            JSONObject keyword4 = new JSONObject();
            keyword4.put("value", orderMoney);
            JSONObject keyword5 = new JSONObject();
            keyword5.put("value", expressNo);
            LOGGER.info("-------------------------获取到的物流单号：{}", expressNo);
            dataJson.put("keyword1", keyword1);
            dataJson.put("keyword2", keyword2);
            dataJson.put("keyword3", keyword3);
            dataJson.put("keyword4", keyword4);
            dataJson.put("keyword5", keyword5);
            sendWxMsg(wxOpenId, TEMPLATE_SEND_TYPE_CONFIRM_RECEIPT_RESULT.getId(), dataJson);
        }

    }


    /**
     * 调用三方发送信息到微信小程序的公共方法
     *
     * @param wxOpenId
     * @param templateId
     * @param dataJson
     */
    private void sendWxMsg(String wxOpenId, String templateId, JSONObject dataJson) {
        JSONObject templateMap = new JSONObject();
        templateMap.put("openId", wxOpenId);
        templateMap.put("templateId", templateId);
        templateMap.put("data", dataJson);
        try {
            LOGGER.info("--------------------------------------调用三方发送微信小程序信息提醒------start------------入参：{}", JSONObject.toJSONString(templateMap));
            callFNService(WX_TEMPLATE_MESSAGE_SEND, templateMap);
            LOGGER.info("--------------------------------------调用三方发送微信小程序信息提醒-----success-------发送完毕！");
        } catch (Exception e) {
            LOGGER.info("-------------------------------------发送信息到用户的微信小程序异常：{}", e);
        }
    }
}