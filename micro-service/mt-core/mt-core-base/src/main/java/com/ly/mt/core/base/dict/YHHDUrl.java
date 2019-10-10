package com.ly.mt.core.base.dict;

/**
 * 云和互动url枚举类
 * @author wanglong
 */
public enum YHHDUrl {

    VIVO_DETECTION_TEST_URL("0","http://211.151.105.70/isz/service/","身份证扫描测试请求地址"),
    THREE_ELEMENT_DETECTION_TEST_URL("1","http://test.yunheit.com/threlem4/","三要素检测沙盒测试地址"),
    PORTRAINT_COMPARE_TEST_URL("2","http://test.yunheit.com/facecheck","人像对比沙盒测试地址"),

    VIVO_DETECTION_URL("3","https://api.yunheit.com/identity/ocr","身份证扫描正式请求地址"),
    THREE_ELEMENT_DETECTION_URL("4","https://api.yunheit.com/threlem3","三要素检测正式地址");




    private  String id;
    private  String url;
    private String desc;

    YHHDUrl(String id, String url, String desc) {
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
