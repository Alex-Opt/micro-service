package com.ly.mt.gzg.b.server.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsynExecutorConfiguration {

    @Value("${asyn.executor.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${asyn.executor.queueCapacity}")
    private Integer queueCapacity;
    @Value("${asyn.executor.corePoolSize}")
    private Integer corePoolSize;

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
