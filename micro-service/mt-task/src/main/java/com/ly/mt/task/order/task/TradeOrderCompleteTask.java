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
public class TradeOrderCompleteTask  extends QuartzJobBean {

    /** 日志*/
    private static  final Logger LOGGER = LoggerFactory.getLogger(TradeOrderCompleteTask.class);

    @Resource
    TradeOrderService tradeOrderService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            LOGGER.info("----------到家C----------更新待收货单子状态为完成--------任务开始--------");
            tradeOrderService.updCompleteStatus();
            LOGGER.info("----------到家C----------更新待收货单子状态为完成--------任务结束--------");
        }catch( Exception e){
            LOGGER.error("----------到家C----------更新待收货单子状态为完成出错:"+e.getMessage(),e);
        }
    }
}
