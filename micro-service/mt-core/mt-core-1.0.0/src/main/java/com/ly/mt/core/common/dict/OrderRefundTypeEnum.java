package com.ly.mt.core.common.dict;

/**
 * 退货单类型
 * @author 484876123@qq.com
 */
public enum OrderRefundTypeEnum {
    REFUND_TYPE_ALL_RETURN("1","整单退货"),
    REFUND_TYPE_PART_RETURN("2","部分退货"),
    ;

    private final String id;
    private final String name;

    OrderRefundTypeEnum(String id, String name) {
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
