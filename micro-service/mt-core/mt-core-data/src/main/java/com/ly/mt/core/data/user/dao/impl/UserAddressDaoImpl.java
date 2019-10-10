package com.ly.mt.core.data.user.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.user.dao.UserAddressDao;
import com.ly.mt.core.data.user.entity.UserAddress;
import com.ly.mt.core.data.user.mapper.UserAddressMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static com.ly.mt.core.redis.RedisKey.REDIS_USER_ADDRESS_ENTITY_ID;

/**
 * UserAddress操作接口
 *
 * @author taoye
 */
@Service
public class UserAddressDaoImpl extends BaseDaoServiceImpl implements UserAddressDao {
    @Resource
    UserAddressMapper mapper;

    @Override
    public UserAddress getUserAddressByIdFromRedis(String id) {
        Assert.notNull(id, "UserAddressDaoImpl.getUserAddressByIdFromRedis id must not be null");
        String json = redisService.get(REDIS_USER_ADDRESS_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), UserAddress.class);
        }
        UserAddress address = new UserAddress();
        address.setId(id);
        address = mapper.getUserAddress(address);
        if (null != address) {
            redisService.setEntity(REDIS_USER_ADDRESS_ENTITY_ID, id, address);
            return address;
        } else {
            return new UserAddress();
        }
    }
}