package com.ly.mt.task.order.job;

import com.ly.mt.task.order.task.ProcessingTimeOutOrderTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author zhanglifeng
 * 处理一小时达订单过了超时时间没有被抢单的订单，进入兜底商家
 * 定时频次：暂定10分钟一次
 */
@Component
public class ProcessingTimeOutOrderJob {

    @Bean(name = "processingTimeOutOrderTrigger")
    public CronTriggerFactoryBean jobOrderCancelTrigger(@Qualifier("processingTimeOutOrderJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0/10 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "processingTimeOutOrderJobDetail")
    public JobDetailFactoryBean processingTimeOutOrderJobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(ProcessingTimeOutOrderTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }


}
