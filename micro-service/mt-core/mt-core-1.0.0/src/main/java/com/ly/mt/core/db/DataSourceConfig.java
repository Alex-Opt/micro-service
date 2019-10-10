package com.ly.mt.core.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@RefreshScope
public class DataSourceConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.initialSize}")
    private String initialSize;
    @Value("${spring.datasource.minIdle}")
    private String minIdle;
    @Value("${spring.datasource.maxActive}")
    private String maxActive;
    @Value("${spring.datasource.maxWait}")
    private String maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private String timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private String minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.maxOpenPreparedStatements}")
    private String maxOpenPreparedStatements;
    @Value("${spring.datasource.filters}")
    private String filters;

    @Bean
    @Primary
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(Integer.valueOf(initialSize));
        datasource.setMinIdle(Integer.valueOf(minIdle));
        datasource.setMaxActive(Integer.valueOf(maxActive));
        datasource.setMaxWait(Integer.valueOf(maxWait));
        datasource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(timeBetweenEvictionRunsMillis));
        datasource.setMinEvictableIdleTimeMillis(Integer.valueOf(minEvictableIdleTimeMillis));
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxOpenPreparedStatements(Integer.valueOf(maxOpenPreparedStatements));
        try {
            datasource.setFilters(filters);
            LOGGER.info("Druid configuration initialization filter success");
        } catch (SQLException e) {
            LOGGER.error("Druid configuration initialization filter error.", e);
        }
        return datasource;
    }
}