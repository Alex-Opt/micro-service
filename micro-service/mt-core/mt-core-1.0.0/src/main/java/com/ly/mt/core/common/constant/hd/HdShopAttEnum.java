package com.ly.mt.core.common.constant.hd;


/**
 * @description
 * 活动模块枚举
 * @author panjingtian
 * @date 2019/6/14 7:48 PM
 */
public enum  HdShopAttEnum {
    /******************* 活动用户状态 **************************/
    ACTIVITY_USER_STATUS_VALID("1","有效的"),
    ACTIVITY_USER_STATUS_INVALID("0","无效的"),
    ACTIVITY_USER_STATUS_REPETITION("3","重复参加"),
    /************************  性别  *******************************/
    ACTIVITY_USER_SEX_MEN("1","男人"),
    ACTIVITY_USER_SEX_WOMAN("0","女人"),
    /****************************** 订单状态 ***********************************/
    ACTIVITY_ORDER_STATUS_FINISH("FINISH","完结"),
    ACTIVITY_ORDER_STATUS_CANCEL("CANCEL","取消"),
    ACTIVITY_ORDER_STATUS_UNPAY("UNPAY","待支付"),
    ACTIVITY_ORDER_STATUS_OKPAY("OKPAY","已支付"),
    ACTIVITY_ORDER_STATUS_SEND("SEND","待收取")

    ;


    HdShopAttEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private String code;

    private String msg;

}
