package com.ly.mt.task.order.job;

import com.ly.mt.task.order.task.TradeOrderNotPaySendWxMsgTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 到家C未支付订单倒计时15分钟时发送未支付提醒给微信小程序用户
 */
@Component
public class TradeOrderNotPaySendWxMsgJob {

    /**
     * 执行频次为1分钟一次
     *
     * @param jobDetail
     * @return
     */
    @Bean(name = "tradeOrderNotPaySendWxMsgJobTrigger")
    public CronTriggerFactoryBean jobOrderCancelTrigger(@Qualifier("tradeOrderNotPaySendWxMsgJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setCronExpression("0 0/1 * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "tradeOrderNotPaySendWxMsgJobDetail")
    public JobDetailFactoryBean jobOrderNotPaySendWxMsgDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(TradeOrderNotPaySendWxMsgTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }


}
