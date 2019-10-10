package com.ly.mt.task.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanConfig implements ApplicationContextAware {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpringBeanConfig.class);

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringBeanConfig.applicationContext == null) {
            SpringBeanConfig.applicationContext = applicationContext;
        }
        LOGGER.info("ApplicationContext配置成功,在普通类可以通过调用SpringBeanUtil.getAppContext()获取applicationContext对象,applicationContext={}", SpringBeanConfig.applicationContext);
    }

    // 获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    // 通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    // 通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
