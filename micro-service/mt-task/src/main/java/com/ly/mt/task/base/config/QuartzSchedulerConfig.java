package com.ly.mt.task.base.config;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description Quartz调度配置类
 * @Author taoye
 */
@Configuration
public class QuartzSchedulerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzSchedulerConfig.class);
    private static final String QUARTZ_YML_NAME = "/quartz.yml";

    @Autowired
    private DataSource dataSource;

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, CronTrigger[] cronTrigger, JobDetail[] jobDetails) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        try {
            factoryBean.setQuartzProperties(initQuartzYml());
            factoryBean.setDataSource(dataSource);
            factoryBean.setJobFactory(jobFactory);
            factoryBean.setTriggers(cronTrigger);
            factoryBean.setJobDetails(jobDetails);
            factoryBean.setOverwriteExistingJobs(true);
        } catch (Exception e) {
            LOGGER.error("加载{}配置文件失败.", QUARTZ_YML_NAME, e);
            throw new RuntimeException("加载配置文件失败", e);
        }
        return factoryBean;
    }

    @Bean
    public Properties initQuartzYml() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_YML_NAME));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext context) {
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        @Override
        protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            beanFactory.autowireBean(job);
            return job;
        }
    }
}