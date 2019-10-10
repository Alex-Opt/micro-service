package com.ly.mt.payment.server.base.dict;

/**
 * @Description 阿里支付反参code枚举类
 * @Author taoye
 */
public enum AlipayResultCodeEnum {
    ALIPAY_RESULT_CODE_SUCCESS("10000", "接口调用成功"),
    ALIPAY_RESULT_CODE_SERVICE_UNAVAILABILITY("20000", "服务不可用"),
    ALIPAY_RESULT_CODE_INSUFFICIENT_AUTHORIZATION("20001", "授权权限不足"),
    ALIPAY_RESULT_CODE_LACK_PARAM("40001", "缺少必选参数"),
    ALIPAY_RESULT_CODE_ILLEGAL_PARAM("40002", "非法的参数"),
    ALIPAY_RESULT_CODE_BUSINESS_FALL("40004", "业务处理失败"),
    ALIPAY_RESULT_CODE_INSUFFICIENT_AUTHORITY("40006", "权限不足");

    private String code;
    private String name;

    AlipayResultCodeEnum(String code, String name) {
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