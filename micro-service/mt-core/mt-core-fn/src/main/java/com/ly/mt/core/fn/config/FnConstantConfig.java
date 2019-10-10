package com.ly.mt.core.fn.config;

/**
 * FN静态常量配置
 *
 * @author taoye
 */
public class FnConstantConfig {
    /**
     * 开发环境配置
     */
    public static final String DEV_PROFILE = "dev";
    public static final String DEV_URL = "https://exam-anubis.ele.me/anubis-webapi";
    public static final String DEV_STORE_CODE = "1000";
    public static final String DEV_NOTIFY_URL = "http://testopen.motivape.cn/open/notify/fnOrder";
    /**
     * 测试环境配置
     */
    public static final String TEST_PROFILE = "test";
    public static final String TEST_URL = "https://exam-anubis.ele.me/anubis-webapi";
    public static final String TEST_STORE_CODE = "1000";
    public static final String TEST_NOTIFY_URL = "http://testopen.motivape.cn/open/notify/fnOrder";
    /**
     * 灰度环境配置
     */
    public static final String GRAY_PROFILE = "gary";
    public static final String GRAY_URL = "https://exam-anubis.ele.me/anubis-webapi";
    public static final String GRAY_STORE_CODE = "1000";
    public static final String GRAY_NOTIFY_URL = "http://opengray.motivape.cn/open/notify/fnOrder";
    /**
     * 生产环境配置
     */
    public static final String PROD_PROFILE = "prod";
    public static final String PROD_URL = "https://open-anubis.ele.me/anubis-webapi";
    public static final String PROD_STORE_CODE = "67306";
    public static final String PROD_NOTIFY_URL = "http://open.motivape.cn/open/notify/fnOrder";
    /**
     * 公共配置
     */
    public static final String APP_ID = "c86adc444-e9cb-4556-9a31-e818c7fbccc8";
    public static final String SECRET_KEY = "68618ab5-fa48-4666-a626-55a0e1a75bc9";
    public static final String SUCCESS_CODE = "200";
    /**
     * 获取token
     */
    public static final String FN_API_GET_TOKEN = "/get_access_token";
    /**
     * 创建订单
     */
    public static final String FN_API_ORDER_CREATE = "/v2/order";
    /**
     * 取消订单
     */
    public static final String FN_API_ORDER_CANCEL = "/v2/order/cancel";
    /**
     * 订单查询
     */
    public static final String FN_API_ORDER_QUERY = "/v2/order/query";
    /**
     * 查询骑手位置
     */
    public static final String FN_API_CARRIER_QUERY = "/v2/order/carrier";
}