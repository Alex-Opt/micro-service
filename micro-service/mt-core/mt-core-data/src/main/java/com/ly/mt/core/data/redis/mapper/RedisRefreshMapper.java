package com.ly.mt.core.data.redis.mapper;

import com.ly.mt.core.data.redis.entity.RedisRefresh;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RedisRefresh操作接口
 *
 * @author taoye
 */
@Mapper
public interface RedisRefreshMapper {
    /**
     * 查询List<RedisRefresh>
     *
     * @param redisRefresh 查询条件
     * @return List<RedisRefresh>
     * @author taoye
     */
    List<RedisRefresh> listRedisRefresh(RedisRefresh redisRefresh);

    /**
     * 更新List<RedisRefresh>
     *
     * @param list 更新数据
     * @author taoye
     */
    void updateRedisRefreshList(List<RedisRefresh> list);
}