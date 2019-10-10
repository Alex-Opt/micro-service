package com.ly.mt.core.data.base.config;

/**
 * Druid静态常量配置
 *
 * @author taoye
 */
public class DruidConstant {
    /**
     * 开发环境配置
     */
    public static final String DEV_PROFILE = "dev";
    public static final String DEV_URL = "jdbc:mysql://leiyankeji.mysql.rds.aliyuncs.com:3306/mt-shop-dev?characterEncoding=utf8&useAffectedRows=true";
    public static final String DEV_USERNAME = "mt_dev";
    public static final String DEV_PASSWORD = "mt_dev_2019";
    /**
     * 测试环境配置
     */
    public static final String TEST_PROFILE = "test";
    public static final String TEST_URL = "jdbc:mysql://rm-2zeowv55ji24468qe.mysql.rds.aliyuncs.com:3306/mt-shop-test?characterEncoding=utf8&useAffectedRows=true";
    public static final String TEST_USERNAME = "test_mt";
    public static final String TEST_PASSWORD = "mt_test_2019";
    /**
     * 灰度环境配置
     */
    public static final String GRAY_PROFILE = "gray";
    public static final String GRAY_URL = "jdbc:mysql://rm-2zeowv55ji24468qe.mysql.rds.aliyuncs.com:3306/gray_mt?characterEncoding=utf8&useAffectedRows=true";
    public static final String GRAY_USERNAME = "test_mt";
    public static final String GRAY_PASSWORD = "mt_test_2019";
    /**
     * 生产环境配置
     */
    public static final String PROD_PROFILE = "prod";
    public static final String PROD_URL = "jdbc:mysql://rm-2zeowv55ji24468qe.mysql.rds.aliyuncs.com:3306/mt-shop?characterEncoding=utf8&useAffectedRows=true";
    public static final String PROD_USERNAME = "prod_mt";
    public static final String PROD_PASSWORD = "mt_prod_2019";
    /**
     * 公共配置
     */
    public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    public static final int INITIAL_SIZE = 1;
    public static final int MIN_IDLE = 1;
    public static final int MAX_ACTIVE = 50;
    public static final int MAX_WAIT = 10000;
    public static final int TIME_BETWEEN_EVICTION_RUNS_MILLIS = 10000;
    public static final int MIN_EVICTABLE_IDLE_TIME_MILLIS = 300000;
    public static final String VALIDATION_QUERY = "select 'x'";
    public static final boolean TEST_WHILE_IDLE = true;
    public static final boolean TEST_ON_BORROW = false;
    public static final boolean TEST_ON_RETURN = false;
    public static final boolean POOL_PREPARED_STATEMENTS = false;
    public static final int MAX_OPEN_PREPARED_STATEMENTS = 20;
    public static final String FILTERS = "stat";
}