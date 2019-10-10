package com.ly.mt.core.data.redis.dao;

import com.ly.mt.core.data.redis.entity.RedisRefresh;

import java.util.List;

/**
 * RedisRefresh操作接口
 *
 * @author taoye
 */
public interface RedisRefreshDao {
    /**
     * 从mysql查询List<RedisRefresh>
     *
     * @return List<RedisRefresh>
     * @author taoye
     */
    List<RedisRefresh> listRedisRefreshFromMysql();

    /**
     * 更新List<RedisRefresh>
     *
     * @param list 更新数据
     * @author taoye
     */
    void updateRedisRefreshList(List<RedisRefresh> list);
}