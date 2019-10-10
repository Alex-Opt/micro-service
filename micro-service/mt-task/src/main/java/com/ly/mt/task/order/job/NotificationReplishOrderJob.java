package com.ly.mt.task.order.job;

import com.ly.mt.task.order.task.NotificationReplishOrderTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 展柜补货订单通知
 */
@Component
public class NotificationReplishOrderJob {

    @Bean(name = "noticationRelishOrderJobTrigger")
    public CronTriggerFactoryBean jobOrderCancelTrigger(@Qualifier("noticationRelishOrderJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0 8 ? * 1-6");//周一至周六早上8点触发发送短信
        //cronTriggerFactoryBean.setCronExpression("0 */1 * * * ?");//方便测试暂时1分钟跑一次
        return cronTriggerFactoryBean;
    }

    @Bean(name = "noticationRelishOrderJobDetail")
    public JobDetailFactoryBean noticationRelishOrderDetail(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(NotificationReplishOrderTask.class);

        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }


}
