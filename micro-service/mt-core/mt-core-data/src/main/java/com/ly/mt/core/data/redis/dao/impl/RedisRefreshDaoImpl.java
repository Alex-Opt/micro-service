package com.ly.mt.core.data.redis.dao.impl;

import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.redis.dao.RedisRefreshDao;
import com.ly.mt.core.data.redis.entity.RedisRefresh;
import com.ly.mt.core.data.redis.mapper.RedisRefreshMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.dict.RedisRefreshStatus.REDIS_REFRESH_STATUS_WAIT;

/**
 * RedisRefresh操作接口
 *
 * @author taoye
 */
@Service
public class RedisRefreshDaoImpl extends BaseDaoServiceImpl implements RedisRefreshDao {
    @Resource
    private RedisRefreshMapper mapper;

    @Override
    public List<RedisRefresh> listRedisRefreshFromMysql() {
        RedisRefresh redisRefresh = new RedisRefresh();
        redisRefresh.setRefreshStatus(REDIS_REFRESH_STATUS_WAIT.getId());
        List<RedisRefresh> list = mapper.listRedisRefresh(redisRefresh);
        if (null != list) {
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void updateRedisRefreshList(List<RedisRefresh> list) {
        mapper.updateRedisRefreshList(list);
    }
}