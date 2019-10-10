package com.ly.mt.core.base.dict;

/**
 * 第三方OCR图像识别返回结果
 */
public enum ThipartiteCardCode {
    THIPARTITE_CARD_CODE_200("200", "请求成功"),
    THIPARTITE_CARD_CODE_400("400", "请求的参数异常（必填参数为空或格式不正确）"),
    THIPARTITE_CARD_CODE_401("401", "请求未授权（请确认账号已经开通并且 token 有效）"),
    THIPARTITE_CARD_CODE_403("403", "权益已经到期（账号有效期已过期）"),
    THIPARTITE_CARD_CODE_404("404", "接口不存在（请检查接口地址是否正确）"),
    THIPARTITE_CARD_CODE_405("405", "请求方式不支持（请检查请求接口的方式，仅支持 POST）"),
    THIPARTITE_CARD_CODE_406("406", "请求内容解析失败（请求的参数密文无效或不是有效的 JSON）"),
    THIPARTITE_CARD_CODE_408("408", "请求结果超时（系统服务繁忙）"),
    THIPARTITE_CARD_CODE_410("410", "服务接口已升级,不再支持（请联系技术支持）"),
    THIPARTITE_CARD_CODE_415("415", "请求内容格式不支持（报文请求的 Content-type 解析失败）"),
    THIPARTITE_CARD_CODE_429("429", "权益次数不足（账号可调用该接口次数已用完）"),
    THIPARTITE_CARD_CODE_500("500", "数据查询错误（请联系技术支持）"),
    THIPARTITE_CARD_CODE_502("502", "数据查询失败（请联系技术支持）"),
    THIPARTITE_CARD_CODE_503("503", "数据查询失败,请重试（请联系技术支持）"),
    THIPARTITE_CARD_CODE_504("504", "数据查询错误,请重试（请联系技术支持）");

    private final String code;
    private final String desc;

    ThipartiteCardCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }}
