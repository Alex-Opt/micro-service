package com.ly.mt.core.wechat.service;

/**
 * 微信公众号认证登录
 *
 * @author taoye
 */
public interface AuthService {
    /**
     * 认证登录返回用户openid
     *
     * @param code code
     * @return openid
     * @throws Exception 异常信息
     * @author taoye
     */
    String getOpenId(String code) throws Exception;
}
