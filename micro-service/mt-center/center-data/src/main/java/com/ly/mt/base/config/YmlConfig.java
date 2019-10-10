package com.ly.mt.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class YmlConfig {
    /**
     * @Description filter
     */
    @Value("${filter}")
    private String filter;

    /**
     * @Description druid
     */
    @Value("${druid.url}")
    private String url;
    @Value("${druid.username}")
    private String username;
    @Value("${druid.password}")
    private String password;
    @Value("${druid.driverClassName}")
    private String driverClassName;
    @Value("${druid.initialSize}")
    private String initialSize;
    @Value("${druid.minIdle}")
    private String minIdle;
    @Value("${druid.maxActive}")
    private String maxActive;
    @Value("${druid.maxWait}")
    private String maxWait;
    @Value("${druid.timeBetweenEvictionRunsMillis}")
    private String timeBetweenEvictionRunsMillis;
    @Value("${druid.minEvictableIdleTimeMillis}")
    private String minEvictableIdleTimeMillis;
    @Value("${druid.validationQuery}")
    private String validationQuery;
    @Value("${druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${druid.maxOpenPreparedStatements}")
    private String maxOpenPreparedStatements;
    @Value("${druid.filters}")
    private String filters;

    public String getFilter() {
        return filter;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getInitialSize() {
        return initialSize;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public String getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public String getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public boolean getTestOnReturn() {
        return testOnReturn;
    }

    public boolean getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public String getMaxOpenPreparedStatements() {
        return maxOpenPreparedStatements;
    }

    public String getFilters() {
        return filters;
    }
}