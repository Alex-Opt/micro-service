package com.ly.mt.core.base.dict;

public enum ThipartiteThreeCheckCode {
    THIPARTITE_THREE_CHECK_CODE_0("0", " 认证失败，请重新认证"),
    THIPARTITE_THREE_CHECK_CODE_1("1", "身份信息一致"),
    THIPARTITE_THREE_CHECK_CODE_2("-1", "证件照、姓名和手机号不相符"),
    THIPARTITE_THREE_CHECK_CODE_3("-2", "姓名和手机号不相符"),
    THIPARTITE_THREE_CHECK_CODE_4("-3", " 证件号和手机号不相符"),
    THIPARTITE_THREE_CHECK_CODE_5("-4", "认证失败，请重新认证");



    private final String code;
    private final String desc;

    ThipartiteThreeCheckCode(String code, String desc) {
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
