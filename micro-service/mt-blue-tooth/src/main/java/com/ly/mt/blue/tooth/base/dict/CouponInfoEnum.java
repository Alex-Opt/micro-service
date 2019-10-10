package com.ly.mt.blue.tooth.base.dict;

/**
 * @Description 优惠卷信息
 * 烟弹优惠卷暂且改成配置,等到家C上线后确定
 * @Author whl
 */
public enum CouponInfoEnum {
    COUPON_NAME("MOTI S雾化弹兑换券", "优惠卷名称"),
    DENOMINATION("39", "优惠卷面额"),
    VALIDITY_DAY("180", "优惠卷有效天数");
    private final String value;
    private final String desc;

    CouponInfoEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}