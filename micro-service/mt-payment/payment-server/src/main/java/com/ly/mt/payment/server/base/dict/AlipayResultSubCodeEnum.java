package com.ly.mt.payment.server.base.dict;

/**
 * @Description 阿里支付反参sub_code枚举类
 * @Author taoye
 */
public enum AlipayResultSubCodeEnum {
    ALIPAY_RESULT_SUB_CODE_TRADE_NOT_EXIST("ACQ.TRADE_NOT_EXIST", "交易不存在");

    private String code;
    private String msg;

    AlipayResultSubCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}