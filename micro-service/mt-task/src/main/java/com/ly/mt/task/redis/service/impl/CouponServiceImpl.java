package com.ly.mt.task.redis.service.impl;

import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.coupon.dao.CouponInfoDao;
import com.ly.mt.core.data.redis.entity.RedisRefresh;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.redis.service.CouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.TriggerType.TRIGGER_TYPE_DELETE;
import static com.ly.mt.core.redis.RedisKey.REDIS_COUPON_INFO_ENTITY_ID;

/**
 * coupon_* 缓存更新处理
 *
 * @author taoye
 */
@Service
public class CouponServiceImpl extends BaseServiceImpl implements CouponService {
    @Resource
    private CouponInfoDao dao;

    @Override
    public void refreshCouponInfo(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_COUPON_INFO_ENTITY_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                dao.getCouponInfoByIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("CouponServiceImpl.refreshCouponInfo refresh.id1 must not be null");
        }
    }
}