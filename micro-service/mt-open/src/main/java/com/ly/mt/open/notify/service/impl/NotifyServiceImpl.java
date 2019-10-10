package com.ly.mt.open.notify.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.XmlUtil;
import com.ly.mt.open.base.service.impl.BaseServiceImpl;
import com.ly.mt.open.notify.service.NotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_AL;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_WX;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.feign.ThirdCenterMethod.AL_PAY_NOTIFY;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_PAY_NOTIFY;
import static com.ly.mt.core.mq.RabbitExchange.*;

;

/**
 * @author taoye
 * @date 2019-06-26
 */
@Service
public class NotifyServiceImpl extends BaseServiceImpl implements NotifyService {
    private final static Logger LOGGER = LoggerFactory.getLogger(NotifyServiceImpl.class);

    /**
     * @Description 阿里支付回调
     * @Author taoye
     */
    @Override
    public String alPayNotify(HttpServletRequest request) {
        try {
            Map<String, String> map = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = iter.next();
                String[] values = requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                map.put(name, valueStr);
            }
            JSONObject requestObject = JSONObject.parseObject(JSONObject.toJSONString(map));
            LOGGER.info("阿里支付回调参数:" + requestObject);
            String tradeStatus = callThirdCenter(AL_PAY_NOTIFY, requestObject);
            requestObject.put("paymentType", PAYMENT_TYPE_AL.getId());
            //这里暂时先用魔法值代替枚举类。未来都统一用attach值，直接通过工具类得到对应的mq的RabbitExchange，统一调用 mqService.sendMessage(attach, jsonObject);即可
            String attach = requestObject.getString("passback_params");
            if (TRADE_STATUS_PAY_SUCCESS.getId().equals(tradeStatus)) {
                // mt-home订单状态处理
                mqService.sendMessage(RABBIT_MQ_PAY_NOTIFY_MT_HOME_B, requestObject);
                if ("mt-mall".equals(attach)) {
                    // mt-mall订单状态处理
                    mqService.sendMessage(RABBIT_MQ_PAY_NOTIFY_MALL, requestObject);
                }
                //mt-activity订单状态处理
                mqService.sendMessage(RABBIT_MQ_PAY_NOTIFY_AD_ACTIVITY, requestObject);
            }
            return "success";
        } catch (Exception e) {
            LOGGER.error("阿里支付回调处理出错:", e);
            return "error";
        }
    }


    /**
     * @Description 微信支付回调
     * @Author taoye
     */
    @Override
    public String wxPayNotify(HttpServletRequest request) {
        try {
            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = request.getReader().readLine()) != null) {
                sb.append(inputLine);
            }
            request.getReader().close();
            String jsonStr = JSON.toJSONString(sb.toString()).replace("<![CDATA[", "").replace("]]>", "").replace("\"", "");
            LOGGER.info("微信支付回调参数:" + jsonStr);
            Map<String, Object> map = XmlUtil.transXml2Map(jsonStr);
            jsonStr = JSONObject.toJSONString(map);
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            String s = callThirdCenter(WX_PAY_NOTIFY, jsonObject);
            LOGGER.info("=================open模块支付回调接口，调三方接口返回值：{}",s);
            jsonObject.put("paymentType", PAYMENT_TYPE_WX.getId());
            //获取区分发送mq的支付参数传递
            String attach = jsonObject.getString("attach");
            LOGGER.info("========attach的值====================：" + attach);
            // mt-home订单状态处理
            mqService.sendMessage(RABBIT_MQ_PAY_NOTIFY_MT_HOME_B, jsonObject);
            //这里暂时先用魔法值代替枚举类。未来都统一用attach值，直接通过工具类得到对应的mq的RabbitExchange，统一调用 mqService.sendMessage(attach, jsonObject);即可
            if ("mt-mall".equals(attach)) {
                // mt-mall订单状态处理
                LOGGER.info("-------------发送消息到mt-mall---------------jsonObject:{}",jsonObject);
                mqService.sendMessage(RABBIT_MQ_PAY_NOTIFY_MALL, jsonObject);
            }
            //mt-activity订单状态处理
            mqService.sendMessage(RABBIT_MQ_PAY_NOTIFY_AD_ACTIVITY, jsonObject);
            return getWxSuccessXml();
        } catch (Exception e) {
            LOGGER.error("微信支付回调处理出错:", e);
            return getWxFallXml();
        }
    }


    /**
     * @Description 蜂鸟订单状态回调，直接将消息进mq了
     * @Author taoye
     */
    @Override
    public void fnOrder(JSONObject jsonObject) throws Exception {
        LOGGER.info("==========蜂鸟物流信息更新============" + jsonObject);
        try {
            mqService.sendMessage(RABBIT_MQ_FN_CALLBACK, jsonObject);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }


    /**
     * @Description 获取错误返回
     * @Author taoye
     */
    private String getWxFallXml() {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("return_code", "<![CDATA[FALL]]>");
        resultMap.put("return_msg", "<![CDATA[NO]]>");
        return XmlUtil.transMap2Xml(resultMap);
    }


    /**
     * @Description 获取成功返回
     * @Author taoye
     */
    private String getWxSuccessXml() {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("return_code", "<![CDATA[SUCCESS]]>");
        resultMap.put("return_msg", "<![CDATA[OK]]>");
        return XmlUtil.transMap2Xml(resultMap);
    }
}