package com.ly.mt.activity.wechart.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 微信公众号认证登录
 *
 * @author taoye
 */
public interface WeChartAuthService {
    /**
     * 认证登录返回用户openid
     *
     * @param code code
     * @return ResponseJson
     * @throws Exception 异常信息
     * @author taoye
     */
    ResponseJson getOpenId(String code) throws Exception;
}
