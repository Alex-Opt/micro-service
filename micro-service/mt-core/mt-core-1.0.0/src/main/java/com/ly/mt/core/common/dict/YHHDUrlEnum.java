package com.ly.mt.core.common.dict;

/**
 * 云和互动url枚举类
 * @author wanglong
 */
public enum YHHDUrlEnum {

    VIVO_DETECTION_TEST_URL("0","http://211.151.105.70/isz/service/","身份证扫描测试请求地址"),
    THREE_ELEMENT_DETECTION_TEST_URL("1","http://test.yunheit.com/threlem4/","三要素检测沙盒测试地址"),
    PORTRAINT_COMPARE_TEST_URL("2","http://test.yunheit.com/facecheck","人像对比沙盒测试地址");


    private  String id;
    private  String url;
    private String desc;

    YHHDUrlEnum(String id, String url, String desc) {
        this.id = id;
        this.url = url;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }}
