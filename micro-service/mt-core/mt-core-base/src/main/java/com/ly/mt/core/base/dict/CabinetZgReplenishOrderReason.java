package com.ly.mt.core.base.dict;

/**
 * 展柜补货理由枚举
 *
 * @author wanghongliang
 */
public enum CabinetZgReplenishOrderReason {
    TOP3("0", "TOP3商品售罄"),
    SUIT("1", "套装售罄"),
    STOCK_LIMIT("2", "该展柜库存剩余小于2");
    private final String value;
    private final String desc;

    CabinetZgReplenishOrderReason(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }}
