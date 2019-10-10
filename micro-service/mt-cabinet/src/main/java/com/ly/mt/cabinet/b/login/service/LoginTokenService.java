package com.ly.mt.cabinet.b.login.service;
/**
 * @program: mt-cabinet
 * @description: 操作登录tokenService接口
 * @author: wanghongliang
 * @create: 2019/7/24 17:34
 **/
public interface LoginTokenService {

    /**
     * 获取token
     * @throws Exception
     */
    String getToken(String userId) throws Exception;

    /**
     * token放入缓存
     * @throws Exception
     */
     String copyTokenToCache(String userId) throws Exception;

    /**
     * 创建新的token
     * @throws Exception
     */
    String createToken(String userId, String password) throws Exception;

    /**
     * 插入token
     * @throws Exception
     */
    Integer insert(String userId, String token, Long expire) throws Exception;

    /**
     * 更新token
     * @throws Exception
     */
    Integer updateToken(String userId, String token) throws Exception;

    /**
     * 删除token
     * @throws Exception
     */
    void delToken(String userId) throws Exception;
}