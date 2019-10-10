package com.ly.mt.task.order.job;

import com.ly.mt.task.order.task.ProcessQueryWithdrawalTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;


@Component
public class ProcessQueryWithdrawalJob {

    @Bean(name = "processQueryWithdrawalTrigger")
    public CronTriggerFactoryBean jobQueryWithdrawalTrigger(@Qualifier("processQueryWithdrawalJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0/20 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "processQueryWithdrawalJobDetail")
    public JobDetailFactoryBean processQueryWithdrawalJobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(ProcessQueryWithdrawalTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }


}
