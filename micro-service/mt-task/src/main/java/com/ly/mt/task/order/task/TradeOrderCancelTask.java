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
 * @author wanglong
 */
@Component
public class TradeOrderCancelTask extends QuartzJobBean {

    /** 日志*/
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeOrderCancelTask.class);


    @Resource
    TradeOrderService tradeOrderService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LOGGER.info("-------------到家C-------------------更新状态为未支付的订单为取消任务开始---------");
            tradeOrderService.updCancelStatus();
            LOGGER.info("-------------到家C-------------------更新状态为未支付的订单为取消任务结束---------");
        } catch (Exception e) {
            LOGGER.error("-------------到家C-------------------更新状态为未支付的订单为取消出错:"+e.getMessage(), e);
        }
    }
}
