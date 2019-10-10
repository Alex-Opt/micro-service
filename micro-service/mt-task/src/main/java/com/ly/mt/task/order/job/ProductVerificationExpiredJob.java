package com.ly.mt.task.order.job;

import com.ly.mt.task.order.task.ProductVerificationExpiredTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author zhanglifeng
 * 商品校验超过五分钟后过期。自动结束抢单job
 * 启动频次 3分钟一次
 */
@Component
public class ProductVerificationExpiredJob {

    @Bean(name = "productVerificationExpiredJobTrigger")
    public CronTriggerFactoryBean jobOrderCancelTrigger(@Qualifier("productVerificationExpiredJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0/3 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "productVerificationExpiredJobDetail")
    public JobDetailFactoryBean jobOrderCancelDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(ProductVerificationExpiredTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }


}
