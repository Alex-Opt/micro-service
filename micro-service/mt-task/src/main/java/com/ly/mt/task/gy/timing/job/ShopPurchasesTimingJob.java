package com.ly.mt.task.gy.timing.job;

import com.ly.mt.task.gy.timing.task.ShopPurchasesTimingTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @description: 商家订单
 * @author: linan
 * @date: 2019/7/22
 */
@Component
public class ShopPurchasesTimingJob {
    @Bean(name = "shopPurchasesTimingJobTrigger")
    public CronTriggerFactoryBean jobShopPurchasesTrigger(@Qualifier("shopPurchasesTimingJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0/15 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "shopPurchasesTimingJobDetail")
    public JobDetailFactoryBean jobShopPurchasesDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(ShopPurchasesTimingTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }
}
