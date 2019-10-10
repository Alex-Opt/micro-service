package com.ly.mt.core.data.activity.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.activity.dao.ActivitySecurityCodeDao;
import com.ly.mt.core.data.activity.entity.ActivitySecurityCode;
import com.ly.mt.core.data.activity.mapper.ActivitySecurityCodeMapper;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static com.ly.mt.core.redis.RedisKey.REDIS_ACTIVITY_SECURITY_CODE_ENTITY_SECURITY_CODE;

/**
 * ActivitySecurityCode操作接口
 *
 * @author taoye
 */
@Service
public class ActivitySecurityCodeDaoImpl extends BaseDaoServiceImpl implements ActivitySecurityCodeDao {
    @Resource
    private ActivitySecurityCodeMapper mapper;


    @Override
    public void insertActivitySecurityCode(ActivitySecurityCode activitySecurityCode) {
        Assert.notNull(activitySecurityCode, "ActivitySecurityCodeDaoImpl.insertActivitySecurityCode activitySecurityCode must not be null");
        redisService.setEntity(REDIS_ACTIVITY_SECURITY_CODE_ENTITY_SECURITY_CODE, activitySecurityCode.getSecurityCode(), activitySecurityCode);
        mapper.insertActivitySecurityCode(activitySecurityCode);
    }


    @Override
    public ActivitySecurityCode getActivitySecurityCodeBySecurityCodeFromRedis(String securityCode) {
        Assert.notNull(securityCode, "ActivitySecurityCodeDaoImpl.getActivitySecurityCodeBySecurityCodeFromRedis securityCode must not be null");
        String json = redisService.get(REDIS_ACTIVITY_SECURITY_CODE_ENTITY_SECURITY_CODE, securityCode);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), ActivitySecurityCode.class);
        }
        ActivitySecurityCode activitySecurityCode = new ActivitySecurityCode();
        activitySecurityCode.setSecurityCode(securityCode);
        activitySecurityCode = mapper.getActivitySecurityCode(activitySecurityCode);
        if (null != activitySecurityCode) {
            redisService.setEntity(REDIS_ACTIVITY_SECURITY_CODE_ENTITY_SECURITY_CODE, securityCode, activitySecurityCode);
            return activitySecurityCode;
        } else {
            return new ActivitySecurityCode();
        }
    }
}