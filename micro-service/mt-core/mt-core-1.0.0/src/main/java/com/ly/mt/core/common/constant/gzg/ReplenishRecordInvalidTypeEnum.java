package com.ly.mt.core.common.constant.gzg;

public enum ReplenishRecordInvalidTypeEnum {
    GOODS_CHECK_FAILURE(0,"商品校验失败"),
    GZG_CHECK_FAILURE(1,"货柜校验失败"),
    CHECK_FAILURE(2,"审核失败"),
    REPLENISHI_OVER_DUE(3,"补货超时"),
    REPLENISH_GIVE_UP(4,"放弃补货");
    private int key;
    private String value;

    ReplenishRecordInvalidTypeEnum(int key,String value){
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        ReplenishRecordInvalidTypeEnum[] businessModeEnums = values();
        for (ReplenishRecordInvalidTypeEnum businessModeEnum : businessModeEnums) {
            if (businessModeEnum.getKey() == key) {
                return businessModeEnum.getValue();
            }
        }
        return null;
    }
    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
