package com.ly.mt.core.dict;

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
     */
    SMS_TEMPLATE_CODE_BLUETOOTH_REGIST("SMS_158520074", "魔笛", "蓝牙APP用户注册验证码"),
    SMS_TEMPLATE_CODE_BLUETOOTH_PASSWORD("SMS_158520073", "魔笛", "蓝牙APP修改密码验证码"),
    SMS_TEMPLATE_CODE_BLUETOOTH_LOGIN("SMS_158520076", "魔笛", "蓝牙APP登录确认验证码"),
    SMS_TEMPLATE_CODE_BLUETOOTH_BIND_MOBILE("SMS_158520072", "魔笛", "蓝牙信息变更验证码"),


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