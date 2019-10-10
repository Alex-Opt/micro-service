package com.ly.mt.task.order.task;

import com.ly.mt.task.order.service.TradeOrderService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhanglifeng
 * 处理一小时达订单过了超时时间没有被抢单的订单，进入兜底商家
 */
@Component
public class ProcessingTimeOutOrderTask extends QuartzJobBean {

    /** 日志*/
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingTimeOutOrderTask.class);


    @Resource
    TradeOrderService tradeOrderService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LOGGER.info("========到家B----抢单===========捞取超时订单，进入兜底流程 job  start==========================");
            tradeOrderService.processingTimeOutOrders();
            LOGGER.info("=========到家B----抢单==========捞取超时订单，进入兜底流程 job  end==========================");
        } catch (Exception e) {
            LOGGER.error("到家B----抢单，捞取超时订单，进入兜底流程异常："+e.getMessage(),e);
        }
    }
}
