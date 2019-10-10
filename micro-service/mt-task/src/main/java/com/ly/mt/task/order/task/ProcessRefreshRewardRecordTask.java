package com.ly.mt.task.order.task;

import com.ly.mt.task.order.service.WithdrawalService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class ProcessRefreshRewardRecordTask extends QuartzJobBean {

    /** 日志*/
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessRefreshRewardRecordTask.class);


    @Resource
    WithdrawalService withdrawalService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LOGGER.info("=======格子柜B----结算奖励明细金额===========维护奖励明细表和奖励表数据 job  start==========================");
            withdrawalService.processRefreshRewardRecord();
            LOGGER.info("=======格子柜B----结算奖励明细金额===========维护奖励明细表和奖励表数据 job  end==========================");
        } catch (Exception e) {
            LOGGER.error("格子柜B----结算奖励明细金额===========维护提现流水表数据异常："+e.getMessage(),e);
        }
    }
}
