package com.ly.mt.task.order.job;

import com.ly.mt.task.order.task.ProcessRefreshRewardRecordTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;


@Component
public class ProcessRefreshRewardRecordJob {

    @Bean(name = "processRefreshRewardRecordTrigger")
    public CronTriggerFactoryBean jobQueryWithdrawalTrigger(@Qualifier("processRefreshRewardRecordJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0 1 * * ?"); //每天凌晨1点执行一次
        //cronTriggerFactoryBean.setCronExpression("0 0/5 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "processRefreshRewardRecordJobDetail")
    public JobDetailFactoryBean processRefreshRewardRecordJobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(ProcessRefreshRewardRecordTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }


}
