package com.ly.mt.core.aliyun.sms.dict;

/**
 * sms模版
 *
 * @author taoye
 */
public enum SmsTemplate {
    SMS_TEMPLATE_CODE_MAll_H5_REGIST("SMS_158520074", "魔笛"),
    SMS_TEMPLATE_CODE_MAll_H5_LOGIN("SMS_158520076", "魔笛"),
    SMS_TEMPLATE_CODE_MAll_H5_PASSWORD("SMS_158520073", "魔笛"),
    SMS_TEMPLATE_CODE_MAll_H5_BIND_MOBILE("SMS_158520072", "魔笛"),

    //"格子柜B端-展柜BD补货通知"
    SMS_TEMPLATE_NOTIFICATION_CABAINET_B_ZG_BD("SMS_173477614", "MOTI"),


    /**
     * 蓝牙APP——注册
     * 【iMOTI】验证码842496，您正在注册成为新用户，若非本人操作，请勿泄露。感谢您的支持！
     */
    SMS_TEMPLATE_CODE_BLUETOOTH_REGIST("SMS_172356469", "iMOTI"),
    /**
     * 蓝牙APP——登录
     * 【iMOTI】验证码784288，您正在登录，若非本人操作，请勿泄露。
     */
    SMS_TEMPLATE_CODE_BLUETOOTH_LOGIN("SMS_172351515", "iMOTI"),
    /**
     * 蓝牙APP——修改密码
     * 【iMOTI】验证码784288，您正在重置密码，若非本人操作，请勿泄露。
     */
    SMS_TEMPLATE_CODE_BLUETOOTH_PASSWORD("SMS_172356476", "iMOTI"),
    /**
     * 蓝牙APP——更换绑定手机号申请
     * 【iMOTI】验证码784288，您正在申请更换手机号，若非本人操作，请勿泄露。
     */
    SMS_TEMPLATE_CODE_BLUETOOTH_CHANGE_BIND_MOBILE("SMS_172356480", "iMOTI"),
    /**
     * 蓝牙APP——更换绑定新手机号
     * 【iMOTI】验证码784288，您正在申请绑定新的手机号，若非本人操作，请勿泄露。
     */
    SMS_TEMPLATE_CODE_BLUETOOTH_BIND_MOBILE("SMS_172351535", "iMOTI"),


    /**
     * 活动——海南门店注册验证码
     */
    SMS_TEMPLATE_CODE_ACTIVITY_HN_REGIST("SMS_167964394", "魔笛"),
    /**
     * 活动——海南门店取货验证码
     */
    SMS_TEMPLATE_CODE_ACTIVITY_HN_GET_PRODUCT("SMS_168730471", "魔笛"),


    /**
     * 格子柜——入驻报名验证码
     */
    SMS_TEMPLATE_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET("SMS_169636144", "魔笛"),
    /**
     * 格子柜——B端登录发送短信
     */
    SMS_TEMPLATE_CODE_CABAINET_LOGIN("SMS_173472312", "MOTI商家");


    /**
     * 模版code
     */
    private final String code;
    /**
     * 短信签名
     */
    private final String sign;


    SmsTemplate(String code, String sign) {
        this.code = code;
        this.sign = sign;
    }


    public String getCode() {
        return code;
    }

    public String getSign() {
        return sign;
    }
}