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
 * 校验商品过期任务
 */
@Component
public class ProductVerificationExpiredTask extends QuartzJobBean {

    /** 日志*/
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductVerificationExpiredTask.class);


    @Resource
    TradeOrderService tradeOrderService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LOGGER.info("========到家B----抢单=========校验商品过期任务开始================");
            tradeOrderService.productVerificationExpired();
            LOGGER.info("========到家B----抢单=========校验商品过期任务结束================");
        } catch (Exception e) {
            LOGGER.error("到家B----抢单，校验商品过期任务异常:"+e.getMessage(), e);
        }
    }
}
