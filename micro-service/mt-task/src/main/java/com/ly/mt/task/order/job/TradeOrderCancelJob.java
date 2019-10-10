package com.ly.mt.task.order.job;

import com.ly.mt.task.order.task.TradeOrderCancelTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 到家C未支付订单定时任务更新为取消状态
 */
@Component
public class TradeOrderCancelJob {

    /**
     * 执行频次为1分钟一次
     * @param jobDetail
     * @return
     */
    @Bean(name = "tradeOrderCancelJobTrigger")
    public CronTriggerFactoryBean jobOrderCancelTrigger(@Qualifier("tradeOrderCancelJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0/1 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "tradeOrderCancelJobDetail")
    public JobDetailFactoryBean jobOrderCancelDetail(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(TradeOrderCancelTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }


}
