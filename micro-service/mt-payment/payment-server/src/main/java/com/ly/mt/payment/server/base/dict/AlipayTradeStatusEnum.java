package com.ly.mt.payment.server.base.dict;

/**
 * @Description 阿里支付订单状态枚举类
 * @Author taoye
 */
public enum AlipayTradeStatusEnum {
    ALIPAY_TRADE_STATUS_WAIT_BUYER_PAY("WAIT_BUYER_PAY", "交易创建，等待买家付款"),
    ALIPAY_TRADE_STATUS_TRADE_CLOSED("TRADE_CLOSED", "未付款交易超时关闭，或支付完成后全额退款"),
    ALIPAY_TRADE_STATUS_TRADE_SUCCESS("TRADE_SUCCESS", "交易支付成功"),
    ALIPAY_TRADE_STATUS_TRADE_FINISHED("TRADE_FINISHED", "交易结束，不可退款");

    private String code;
    private String name;

    AlipayTradeStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}