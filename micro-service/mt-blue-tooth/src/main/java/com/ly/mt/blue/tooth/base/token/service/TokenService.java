package com.ly.mt.blue.tooth.base.token.service;
/**
 * @program: mt-blue-tooth
 * @description: 操作tokenService接口
 * @author: wanghongliang
 * @create: 2019/7/24 17:34
 **/
public interface TokenService {

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
    String createToken(String userId,String password) throws Exception;

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