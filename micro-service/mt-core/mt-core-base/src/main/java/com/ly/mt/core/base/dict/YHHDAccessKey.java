package com.ly.mt.core.base.dict;

/**
 * 云河互动商户信息枚举类
 */
public enum YHHDAccessKey {
    IDCARD_SCAN_ACCESS_ID("0","leiyankeji","二代身份证扫描商户access_id"),
    IDCARD_SCAN_ACCESS_PWD("1","B49C9FFABC4A63A6","二代身份证扫描商户密码"),
    /*THREE_ELE_ACCESS_KEY("2","iZAorYgRzUMhsWI_vgZHfq2XuzPIZtCU","三要素检测商户key"),
    THREE_ELE_KEY_SECRET("3","Zk1dlPcQa8KvvKt3Th9aKF_LOfiHR00b","三要素检测商户密钥")*/

    THREE_ELE_ACCESS_KEY("2","lR5gJh7ymIhAvra9GsWnOENIpLLVbI__","三要素检测商户key"),
    THREE_ELE_KEY_SECRET("3","SmIHUcV70WIiqyLVmcHTx8eFoH2RF1dw","三要素检测商户密钥"),
    IDCARD_SCAN_PRODUCT_CODE("4","1R010Y01","身份证解析产品编号");


    private String  code;
    private  String name;
    private  String desc;

    YHHDAccessKey(String code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }}
