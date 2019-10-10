package com.ly.mt.core.common.dict;

/**
 * 退货状态
 * @author 484876123@qq.com
 */
public enum RefundStatusEnum {

    /**
     * 订单状态
     */
    ORDER_REFUND_NO("1","否"),
    ORDER_REFUND_EXAMINE("2","退货审核"),
    ORDER_REFUND_DONE("3","退货完成"),

    /**
     * 退货单状态
     */
    REFUND_STATUS_APPLY_RETURN("10","申请状态"),
    REFUND_STATUS_AGREE_RETURN("20","同意退货"),
    REFUND_STATUS_REFUSE_RETURN("30","拒绝退货"),
    REFUND_STATUS_DELIVER_RETURN("50","买家发货"),
    REFUND_STATUS_RECEIVING_RETURN("60","平台收货"),
    REFUND_STATUS_DONE_RETURN("99","退货完成"),
    ;
    private final String id;
    private final String name;

    RefundStatusEnum(String id, String name) {
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
