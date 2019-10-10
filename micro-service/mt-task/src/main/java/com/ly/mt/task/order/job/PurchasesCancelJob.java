package com.ly.mt.task.order.job;

import com.ly.mt.task.order.task.PurchasesCancelTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;


@Component
public class PurchasesCancelJob {

    @Bean(name = "purchasesCancelJobTrigger")
    public CronTriggerFactoryBean jobOrderCancelTrigger(@Qualifier("purchasesCancelJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0/10 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "purchasesCancelJobDetail")
    public JobDetailFactoryBean jobOrderCancelDetail(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(PurchasesCancelTask.class);

        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }


}
