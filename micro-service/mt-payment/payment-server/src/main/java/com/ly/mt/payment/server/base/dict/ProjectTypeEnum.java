package com.ly.mt.payment.server.base.dict;

/**
 * 支付请求来源的项目枚举类
 * @author zhanglifeng
 * @date 2019-06-29
 */
public enum ProjectTypeEnum {
    PROJECT_TYPE_H5_PAY("1","H5商城，微信小程序支付请求"),
    PROJECT_TYPE_B_PAY("2","B端支付请求");

    private final String id;
    private final String type;

    ProjectTypeEnum(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

}
