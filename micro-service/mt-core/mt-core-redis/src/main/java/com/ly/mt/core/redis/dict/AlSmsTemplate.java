package com.ly.mt.core.redis.dict;

/**
 * @Description 动态验证码枚举类
 * @Author taoye
 */
public enum AlSmsTemplate {
    SMS_TEMPLATE_CODE_MAll_H5_REGIST("SMS_158520074", "魔笛", "用户注册验证码"),
    SMS_TEMPLATE_CODE_MAll_H5_LOGIN("SMS_158520076", "魔笛", "登录确认验证码"),
    SMS_TEMPLATE_CODE_MAll_H5_PASSWORD("SMS_158520073", "魔笛", "修改密码验证码"),
    SMS_TEMPLATE_CODE_MAll_H5_BIND_MOBILE("SMS_158520072", "魔笛", "信息变更验证码"),

    /**
     * 蓝牙APP注册
     * 蓝牙APP修改密码
     * 蓝牙APP登录
     * 蓝牙更换绑定手机号
     * 【iMOTI】验证码842496，您正在注册成为新用户，若非本人操作，请勿泄露。感谢您的支持！
     * 【iMOTI】验证码784288，您正在登录，若非本人操作，请勿泄露。
     * 【iMOTI】验证码784288，您正在重置密码，若非本人操作，请勿泄露。
     * 【iMOTI】验证码784288，您正在申请更换手机号，若非本人操作，请勿泄露。
     * 【iMOTI】验证码784288，您正在申请绑定新的手机号，若非本人操作，请勿泄露。
     * iMOTI注册
     *      * SMS_172356469
     * iMOTI登录
     *     * SMS_172351515
     * iMOTI重置
     *      * SMS_172356476
     * iMOTI更换手机
     *      * SMS_172356480
     * iMOTI绑定新手机号
     *    SMS_172351535
     */
    SMS_TEMPLATE_CODE_BLUETOOTH_REGIST("SMS_172356469", "iMOTI", "您正在注册成为新用户，若非本人操作，请勿泄露。感谢您的支持！"),
    SMS_TEMPLATE_CODE_BLUETOOTH_LOGIN("SMS_172351515", "iMOTI", "您正在登录，若非本人操作，请勿泄露。"),
    SMS_TEMPLATE_CODE_BLUETOOTH_PASSWORD("SMS_172356476", "iMOTI", "您正在重置密码，若非本人操作，请勿泄露。"),
    SMS_TEMPLATE_CODE_BLUETOOTH_CHANGE_BIND_MOBILE("SMS_172356480", "iMOTI", "您正在申请更换手机号，若非本人操作，请勿泄露。"),
    SMS_TEMPLATE_CODE_BLUETOOTH_BIND_MOBILE("SMS_172351535", "iMOTI", "您正在申请更换手机号，若非本人操作，请勿泄露。"),

    /**
     * 格子柜B端发送短信相关业务
     */
    SMS_TEMPLATE_CODE_CABAINET_LOGIN("SMS_173472312", "MOTI商家", "您正在登录，若非本人操作，请勿泄露。"),
    SMS_TEMPLATE_NOTIFICATION_CABAINET_B_GZG_BD("SMS_173472580", "MOTI", "格子柜B端-格子柜BD补货通知"),
    SMS_TEMPLATE_NOTIFICATION_CABAINET_B_ZG_BD("SMS_173477614", "MOTI", "格子柜B端-展柜BD补货通知"),



    SMS_TEMPLATE_CODE_ACTIVITY_HN_REGIST("SMS_167964394", "魔笛", "海南门店活动注册验证码"),
    SMS_TEMPLATE_CODE_ACTIVITY_HN_GET_PRODUCT("SMS_168730471", "魔笛", "海南门店活动取货验证码"),
    SMS_TEMPLATE_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET("SMS_169636144", "魔笛", "格子柜入驻报名验证码");


    private final String templateCode;
    private final String signName;
    private final String desc;

    AlSmsTemplate(String templateCode, String signName, String desc) {
        this.templateCode = templateCode;
        this.signName = signName;
        this.desc = desc;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public String getSignName() {
        return signName;
    }
}