package com.ly.mt.task.gy.timing.job;

import com.ly.mt.task.gy.timing.task.HandleNextDayReachOrderTimingTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author zhanglifeng
 * @description 次日达，普通快递的订单推送频次为5分钟捞取一次。
 */
@Component
public class HandleNextDayReachOrderTimingJob {
    @Bean(name = "handleNextDayReachOrderTimingJobTrigger")
    public CronTriggerFactoryBean jobHandleNextDayReachOrderTrigger(@Qualifier("handleNextDayReachOrderTimingJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0/5 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "handleNextDayReachOrderTimingJobDetail")
    public JobDetailFactoryBean jobHandleNextDayReachOrderDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(HandleNextDayReachOrderTimingTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }
}
