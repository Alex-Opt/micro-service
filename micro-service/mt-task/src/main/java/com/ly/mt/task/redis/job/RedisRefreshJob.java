package com.ly.mt.task.redis.job;

import com.ly.mt.task.redis.task.RedisRefreshTask;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * redis缓存更新处理任务
 *
 * @author taoye
 */
@Component
public class RedisRefreshJob {
    @Bean(name = "redisRefreshJobTrigger")
    public CronTriggerFactoryBean job3Trigger(@Qualifier("redisRefreshJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        // 10秒钟执行1次
        cronTriggerFactoryBean.setCronExpression("*/10 * * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean(name = "redisRefreshJobDetail")
    public JobDetailFactoryBean job3Detail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(RedisRefreshTask.class);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        Map<String, Object> map = new HashMap<>(1);
        map.put("concurrent", false);
        jobDetailFactoryBean.setJobDataAsMap(map);
        return jobDetailFactoryBean;
    }
}