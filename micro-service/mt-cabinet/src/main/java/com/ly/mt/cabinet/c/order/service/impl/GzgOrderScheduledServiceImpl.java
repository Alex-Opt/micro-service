package com.ly.mt.cabinet.c.order.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.cabinet.c.order.entity.OrderOverTimeEnum;
import com.ly.mt.cabinet.c.order.entity.OrderStateEnum;
import com.ly.mt.cabinet.c.order.service.GzgOrderScheduledService;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.feign.DataCenterMethod.GZG_ORDER_GET;
import static com.ly.mt.core.feign.DataCenterMethod.GZG_ORDER_UPDATE;


@Service
public class GzgOrderScheduledServiceImpl extends BaseServiceImpl implements GzgOrderScheduledService {
    private final static Logger log = LoggerFactory.getLogger(FeignServiceImpl.class);

    static final int nThreads = Runtime.getRuntime().availableProcessors();//availableProcessors()获取当前电脑CPU数量

    ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(nThreads * 2);

    @Override
    public void gzgOrderScheduled(String orderId) throws Exception {
        schedulePool.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("延时线程池开始工作，线程名称{}",Thread.currentThread().getName());
                log.info("延时线程池开始工作，线程大小{}",((ThreadPoolExecutor)schedulePool).getActiveCount());
                GzgOrder gzgOrder = getGzgOrder(orderId);
                if ( StringUtil.isEmpty(gzgOrder.getPayment_no()) && OrderStateEnum.TO_BE_PAY.getKey().equals(gzgOrder.getOrder_status())) {
                    log.info("订单编号：{} 超时，延时任务将订单状态改为超时状态",orderId);
                    String xid = TxcContext.getCurrentXid();
                    JSONObject jsonGzgOrder = new JSONObject();
                    jsonGzgOrder.put("id", orderId);
                    jsonGzgOrder.put("order_status", OrderStateEnum.OVERTIME.getKey());
                    jsonGzgOrder.put("order_finish_time", DateUtil.getNowTimeStr());
                    callDataCenter(GZG_ORDER_UPDATE, jsonGzgOrder);
                }
            }
        }, OrderOverTimeEnum.OVERTIME.getTime(), TimeUnit.SECONDS);

    }

    public GzgOrder getGzgOrder(String orderNo) {
        JSONObject jsonGzgOrders = new JSONObject();
        jsonGzgOrders.put("id", orderNo);
        String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrders);
        GzgOrder gzgOrder = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);
        return gzgOrder;
    }


}
