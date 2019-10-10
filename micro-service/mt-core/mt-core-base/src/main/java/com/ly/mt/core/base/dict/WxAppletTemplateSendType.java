package com.ly.mt.core.base.dict;

/**
 * [微信小程序模版类型枚举类]
 * @author zhanglifeng
 * @date 2019-09-18
 */
public enum WxAppletTemplateSendType {
    TEMPLATE_SEND_TYPE_PAY_RESULT("1XifBgWJMD__kY3BhMUGMfdChJhZO-PwCoN8oFneGGQ","订单支付结果、状态通知"),
    TEMPLATE_SEND_TYPE_COMMON_DELIVERY_RESULT("XSjvmUVzxg57FKKX6Pf3R4jURHSWkpnvadsViztzayI","普通快递物流信息通知"),
    TEMPLATE_SEND_TYPE_FN_DELIVERY_RESULT("6AWDv6XuXYIuvNtFezzUUxU86hYWD5TJb30hXwVNRTE","一小时达配送通知"),
    TEMPLATE_SEND_TYPE_WAITING_PAY_RESULT("pATtiHzJnNt7e6NdINr17U-AxudHZoHXfhAxs1lRNAU","待支付通知，提醒"),
    TEMPLATE_SEND_TYPE_CONFIRM_RECEIPT_RESULT("XGf5PU4txW5ZkW6Y2koROybUTUjtTGQXsLYPhCIkn8k","确认收货通知"),;
    private final String id;
    private final String name;

    WxAppletTemplateSendType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
