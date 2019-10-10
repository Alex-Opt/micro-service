package com.ly.mt.task.order.job;

import com.ly.mt.task.order.task.TradeOrderCompleteTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;


@Component
public class TradeOrderCompleteJob {


    @Bean(name = "jobordercompleteTrigger")
    public CronTriggerFactoryBean jobOrderCompleteTrigger(@Qualifier("jobordercompleteDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0/30 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "jobordercompleteDetail")
    public JobDetailFactoryBean jobOrderCompleteDetail(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(TradeOrderCompleteTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }
}
