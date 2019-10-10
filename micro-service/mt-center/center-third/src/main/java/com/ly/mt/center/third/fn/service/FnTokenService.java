package com.ly.mt.center.third.fn.service;

import com.ly.mt.center.third.fn.dict.FnApi;

/**
 * @Description 蜂鸟凭证接口
 * @Author taoye
 */
public interface FnTokenService {
    /**
     * @Description 获取请求地址
     * @Author taoye
     */
    String getRequestUtl(FnApi fnApi);

    /**
     * @Description 请求接口的签名
     * @Author taoye
     */
    String getBusinessSign(String json, int salt);
}