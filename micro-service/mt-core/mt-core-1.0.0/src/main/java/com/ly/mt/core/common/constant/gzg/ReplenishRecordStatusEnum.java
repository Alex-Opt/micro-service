package com.ly.mt.core.common.constant.gzg;

public enum ReplenishRecordStatusEnum {
    ORDER_GRAB(0,"抢单"),
    GOODS_PATTERN(1,"商品校验"),
    GZG_PATTERN(2,"货柜校验"),
    COMMIT(3,"提交审核"),
    CHECK_PASS(4,"审核通过"),
    RECEIVE_REWARD(5,"领取奖励"),
    FAULIRE(6,"补货失效");

    private int key;
    private String value;

    ReplenishRecordStatusEnum(int key,String value){
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
