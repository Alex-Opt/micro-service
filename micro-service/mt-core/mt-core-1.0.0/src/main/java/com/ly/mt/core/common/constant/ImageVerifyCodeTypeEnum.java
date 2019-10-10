package com.ly.mt.core.common.constant;

public enum ImageVerifyCodeTypeEnum {

    IMAGE_VERIFY_CODE_REGIST("1","注册图片验证码"),
    IMAGE_VERIFY_CODE_LOGIN("2","登录图片验证码"),
    IMAGE_VERIFY_CODE_RESET_PASSWORD("3","重置密码图片验证码"),
    IMAGE_VERIFY_CODE_BIND_MOBILE("4","绑定手机号图片验证码");
    private String code;

    private String desc;


    ImageVerifyCodeTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
