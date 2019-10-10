package com.ly.mt.base.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.taobao.txc.datasource.cobar.TxcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
    @Resource
    YmlConfig yml;

    @Bean
    @Primary
    public DataSource TxcDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(yml.getUrl());
        datasource.setUsername(yml.getUsername());
        datasource.setPassword(yml.getPassword());
        datasource.setDriverClassName(yml.getDriverClassName());
        datasource.setInitialSize(Integer.valueOf(yml.getInitialSize()));
        datasource.setMinIdle(Integer.valueOf(yml.getMinIdle()));
        datasource.setMaxActive(Integer.valueOf(yml.getMaxActive()));
        datasource.setMaxWait(Integer.valueOf(yml.getMaxWait()));
        datasource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(yml.getTimeBetweenEvictionRunsMillis()));
        datasource.setMinEvictableIdleTimeMillis(Integer.valueOf(yml.getMinEvictableIdleTimeMillis()));
        datasource.setValidationQuery(yml.getValidationQuery());
        datasource.setTestWhileIdle(yml.getTestWhileIdle());
        datasource.setTestOnBorrow(yml.getTestOnBorrow());
        datasource.setTestOnReturn(yml.getTestOnReturn());
        datasource.setPoolPreparedStatements(yml.getPoolPreparedStatements());
        datasource.setMaxOpenPreparedStatements(Integer.valueOf(yml.getMaxOpenPreparedStatements()));
        try {
            datasource.setFilters(yml.getFilters());
            LOGGER.info("dataSource加载:url={}", yml.getUrl());
        } catch (SQLException e) {
            LOGGER.error("dataSource加载异常:", e);
        }
        return new TxcDataSource(datasource);
    }
}