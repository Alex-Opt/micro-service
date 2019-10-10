package com.ly.mt.home.base.constant;

/**
 * @description: 商家进货常量
 * @author: linan
 * @date: 2019/7/29
 */
public class ShopConstant {
    // 进货基数
    public static Integer BASE_NUM = 2;
    // 运费
    public static String FREIGHT = "0";
    // 新人活动优惠券id
    public static String SHOP_ACTIVITY_COUPON_ID = "604336790610325464";
    // 新人领取的优惠券有效天数
    public static Integer SHOP_ACTIVITY_COUPON_VALIDITY_DAY = 7;

    /**
     * 商家优惠券枚举
     */
    public enum ShopCouponStatus {
        NO_USE("1"), USE("2");

        ShopCouponStatus(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }
    }

    /**
     * 是否默认地址枚举
     */
    public enum ShopAddressIsDefault {
        NO("0"), YES("1");

        ShopAddressIsDefault(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }
    }

    /**
     * 优惠类型枚举
     */
    public enum DiscountType {
        LADDER("1"), MEMBER("2"), COUPON("3");

        DiscountType(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }
    }

    /**
     * 优惠记录状态枚举
     */
    public enum DiscountStatus {
        NORMAL("1"), ABNORMAL("2");

        DiscountStatus(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }
    }

    /**
     * 是或否
     */
    public enum YesOrNo {
        NO("0"), YES("1");

        YesOrNo(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }
    }

}
