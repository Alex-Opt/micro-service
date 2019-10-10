package com.ly.mt.core.data.coupon.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.coupon.dao.CouponInfoDao;
import com.ly.mt.core.data.coupon.entity.CouponInfo;
import com.ly.mt.core.data.coupon.mapper.CouponInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static com.ly.mt.core.redis.RedisKey.REDIS_COUPON_INFO_ENTITY_ID;

/**
 * CouponInfo操作接口
 *
 * @author taoye
 */
@Service
public class CouponInfoDaoImpl extends BaseDaoServiceImpl implements CouponInfoDao {
    @Resource
    private CouponInfoMapper mapper;

    @Override
    public CouponInfo getCouponInfoByIdFromRedis(String id) {
        Assert.notNull(id, "CouponInfoDaoImpl.getCouponInfoByIdFromRedis id must not be null");
        String couponInfoJson = redisService.get(REDIS_COUPON_INFO_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(couponInfoJson)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(couponInfoJson), CouponInfo.class);
        }
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setId(id);
        couponInfo = mapper.getCouponInfo(couponInfo);
        if (null != couponInfo) {
            redisService.setEntity(REDIS_COUPON_INFO_ENTITY_ID, id, couponInfo);
            return couponInfo;
        } else {
            return new CouponInfo();
        }
    }
}