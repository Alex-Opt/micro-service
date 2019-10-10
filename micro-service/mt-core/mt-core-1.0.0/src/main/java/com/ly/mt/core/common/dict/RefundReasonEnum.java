package com.ly.mt.core.common.dict;

/**
 * @author zhanglifeng
 * 退款原因枚举类
 */
public enum RefundReasonEnum {
    REFUND_WITHOUT_REASON("1","七天无理由退款"),
    REFUND_QUALITY_PROBLEM("2","商品质量问题"),
    REFUND_OTHER_THING("3","其他");
    private final String id;
    private final String name;

    RefundReasonEnum(String id, String name) {
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
