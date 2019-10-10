package com.ly.mt.task.order.task;


import com.ly.mt.task.order.service.ShopPurchasesService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PurchasesCompleteTask extends QuartzJobBean {

    /** 日志*/
    private static  final Logger LOGGER = LoggerFactory.getLogger(PurchasesCompleteTask.class);

    @Resource
    ShopPurchasesService shopPurchasesService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            LOGGER.info("到家B-----------------------执行了根据订单表自动取消时间更新状态为完成任务开始--------");
            shopPurchasesService.updCompleteStatus();
            LOGGER.info("到家B-----------------------执行了根据订单表自动取消时间更新状态为完成任务结束--------");
        }catch( Exception e){
            LOGGER.error("到家B-----------------------根据订单表自动取消时间更新状态为未支付的订单为取消出错:"+e.getMessage(),e);
        }
    }
}
