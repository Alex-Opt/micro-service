package com.ly.mt.core.data.base.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

import static com.ly.mt.core.data.base.config.DruidConstant.*;

/**
 * DataSource配置
 *
 * @author taoye
 */
@Configuration
public class DataSourceConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    @Primary
    public DataSource druidDataSource() {
        LOGGER.info("##### dataSource init ##### profile = {}", profile);
        DruidDataSource datasource = new DruidDataSource();
        if (DEV_PROFILE.equals(profile)) {
            datasource.setUrl(DEV_URL);
            datasource.setUsername(DEV_USERNAME);
            datasource.setPassword(DEV_PASSWORD);
            LOGGER.info("##### dataSource init ##### url = {}", DEV_URL);
        } else if (TEST_PROFILE.equals(profile)) {
            datasource.setUrl(TEST_URL);
            datasource.setUsername(TEST_USERNAME);
            datasource.setPassword(TEST_PASSWORD);
            LOGGER.info("##### dataSource init ##### url = {}", TEST_URL);
        } else if (GRAY_PROFILE.equals(profile)) {
            datasource.setUrl(GRAY_URL);
            datasource.setUsername(GRAY_USERNAME);
            datasource.setPassword(GRAY_PASSWORD);
            LOGGER.info("##### dataSource init ##### url = {}", GRAY_URL);
        } else if (PROD_PROFILE.equals(profile)) {
            datasource.setUrl(PROD_URL);
            datasource.setUsername(PROD_USERNAME);
            datasource.setPassword(PROD_PASSWORD);
            LOGGER.info("##### dataSource init ##### url = {}", PROD_URL);
        }
        datasource.setDriverClassName(DRIVER_CLASS_NAME);
        datasource.setInitialSize(INITIAL_SIZE);
        datasource.setMinIdle(MIN_IDLE);
        datasource.setMaxActive(MAX_ACTIVE);
        datasource.setMaxWait(MAX_WAIT);
        datasource.setTimeBetweenEvictionRunsMillis(TIME_BETWEEN_EVICTION_RUNS_MILLIS);
        datasource.setMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS);
        datasource.setValidationQuery(VALIDATION_QUERY);
        datasource.setTestWhileIdle(TEST_WHILE_IDLE);
        datasource.setTestOnBorrow(TEST_ON_BORROW);
        datasource.setTestOnReturn(TEST_ON_RETURN);
        datasource.setPoolPreparedStatements(POOL_PREPARED_STATEMENTS);
        datasource.setMaxOpenPreparedStatements(MAX_OPEN_PREPARED_STATEMENTS);
        try {
            datasource.setFilters(FILTERS);
        } catch (SQLException e) {
            LOGGER.error("##### dataSource init ##### profile = {}, error = ", profile, e);
        }
        return datasource;
    }
}