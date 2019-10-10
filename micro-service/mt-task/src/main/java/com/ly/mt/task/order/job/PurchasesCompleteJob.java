package com.ly.mt.task.order.job;

import com.ly.mt.task.order.task.PurchasesCompleteTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;


@Component
public class PurchasesCompleteJob {


    @Bean(name = "purchasesCompleteJobTrigger")
    public CronTriggerFactoryBean jobOrderCompleteTrigger(@Qualifier("purchasesCompleteJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0/3 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "purchasesCompleteJobDetail")
    public JobDetailFactoryBean jobOrderCompleteDetail(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(PurchasesCompleteTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }
}
