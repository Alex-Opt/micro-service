package com.ly.mt.core.base.dict;

/**
 * @Description 动态验证码枚举类
 * @Author taoye
 */
public enum SmsTemplateEnum {
    SMS_TEMPLATE_CODE_REGIST("0", "code:dynamic:regist:", "SMS_158520074", "魔笛", "用户注册验证码"),
    SMS_TEMPLATE_CODE_LOGIN("1", "code:dynamic:login:", "SMS_158520076", "魔笛", "登录确认验证码"),
    SMS_TEMPLATE_CODE_PASSWORD("2", "code:dynamic:password:", "SMS_158520073", "魔笛", "修改密码验证码"),
    SMS_TEMPLATE_CODE_INFO("3", "code:dynamic:info:", "SMS_158520072", "魔笛", "信息变更验证码"),

    SMS_TEMPLATE_NOTICE_ALARM("30", "", "SMS_166371982", "深圳雷炎科技有限公司", "报警信息"),

    SMS_TEMPLATE_CODE_ACTIVITY_TOB_REGIST("11","code;dynamic:att:b:regist","SMS_167964394","魔笛","活动验证码"),  //海南门店活动取货码
    SMS_TEMPLATE_CODE_ACTIVITY_GETPRODUCT_CODE("12","code;dynamic:att:c:productcode","SMS_168730471","魔笛","商品取货码"),  //海南门店活动取货码
    ;


    private final String dynamicCodeType;
    private final String redisKey;
    private final String templateCode;
    private final String signName;
    private final String describe;

    SmsTemplateEnum(String dynamicCodeType, String redisKey, String templateCode, String signName, String describe) {
        this.dynamicCodeType = dynamicCodeType;
        this.templateCode = templateCode;
        this.signName = signName;
        this.redisKey = redisKey;
        this.describe = describe;
    }

    public String getDynamicCodeType() {
        return dynamicCodeType;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public String getSignName() {
        return signName;
    }

    public static SmsTemplateEnum getDynamicCodeEnumByCodeType(String codeType) {
        for (SmsTemplateEnum smsTemplateEnum : SmsTemplateEnum.values()) {
            if (smsTemplateEnum.getDynamicCodeType().equals(codeType)) {
                return smsTemplateEnum;
            }
        }
        return null;
    }
}