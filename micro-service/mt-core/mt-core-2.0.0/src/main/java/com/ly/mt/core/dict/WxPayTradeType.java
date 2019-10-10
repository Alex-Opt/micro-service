package com.ly.mt.core.dict;

import com.ly.mt.core.util.StringUtil;

/**
 * @Description 微信交易类型枚举类
 * @Author taoye
 */
public enum  WxPayTradeType {
    WX_PAY_TRADE_TYPE_MWEB("MWEB", "H5支付"),
    WX_PAY_TRADE_TYPE_JSAPI("JSAPI", "JSAPI或小程序支付"),
    WX_PAY_TRADE_TYPE_APP("APP", "APP支付");

    WxPayTradeType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private String type;
    private String desc;

    public String getType() {
        return type;
    }

    /**
     * @Description 判断交易类型是否存在
     * @Author taoye
     */
    public static boolean checkTradeType(String type) {
        if (StringUtil.isEmpty(type)) {
            return false;
        }
        for (WxPayTradeType tradeType : WxPayTradeType.values()) {
            if (tradeType.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}