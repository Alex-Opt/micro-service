package com.ly.mt.task.redis.service;

import com.ly.mt.core.data.redis.entity.RedisRefresh;

/**
 * User缓存更新处理
 *
 * @author taoye
 */
public interface UserService {
    /**
     * User缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshUser(RedisRefresh refresh) throws Exception;

    /**
     * UserAddress缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshUserAddress(RedisRefresh refresh);
}