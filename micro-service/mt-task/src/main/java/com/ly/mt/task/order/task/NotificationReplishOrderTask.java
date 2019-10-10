package com.ly.mt.task.order.task;

import com.ly.mt.task.order.service.CabinetZgReplishmentOrderService;
import com.ly.mt.task.order.service.TradeOrderService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wanghongliang
 *
 */
@Component
public class NotificationReplishOrderTask extends QuartzJobBean {

    /** 日志*/
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationReplishOrderTask.class);


    @Resource
    CabinetZgReplishmentOrderService cabinetZgReplishmentOrderService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LOGGER.info("========格子柜B----开始通知===========查询展柜补货单并进行通知 job-start==========================");
            cabinetZgReplishmentOrderService.processCabinetZgReplishmentOrder();
            LOGGER.info("=========格子柜B---结束通知===========查询展柜补货单并进行通知 job-end==========================");
        } catch (Exception e) {
            LOGGER.error("格子柜B----通知异常：",e);
        }
    }
}
