package com.ly.mt.home.tob.shopuser.service;

import io.jsonwebtoken.Claims;

/**
 * @author wanglong
 */
public interface TokenService {
    /**
     * 根据clams生成token
     *
     * @return
     */
    String generateToken(String shopId, String userId, String mobile, String userName, Long expiration);

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    Claims getClaimsFromToken(String token);


    /**
     * 检查token是否过期
     *
     * @param token
     * @return
     */
    Boolean isTokenExpire(String token);

}
