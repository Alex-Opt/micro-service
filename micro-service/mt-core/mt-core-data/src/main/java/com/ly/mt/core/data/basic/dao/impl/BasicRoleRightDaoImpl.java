package com.ly.mt.core.data.basic.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.basic.dao.BasicRoleRightDao;
import com.ly.mt.core.data.basic.entity.BasicRoleRight;
import com.ly.mt.core.data.basic.mapper.BasicRoleRightMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_BASIC_ROLE_RIGHT_LIST_ROLE_ID_RIGHT_TYPE;

/**
 * BasicRoleRight操作接口
 *
 * @author taoye
 */
@Service
public class BasicRoleRightDaoImpl extends BaseDaoServiceImpl implements BasicRoleRightDao {
    @Resource
    private BasicRoleRightMapper mapper;

    @Override
    public void insertBasicRoleRights(List<BasicRoleRight> basicRoleRights) {
        Assert.notNull(basicRoleRights, "BasicRoleRightDaoImpl.insertBasicRoleRights basicRoleRights must not be null");
        BasicRoleRight basicRoleRight = basicRoleRights.get(0);
        redisService.del(REDIS_BASIC_ROLE_RIGHT_LIST_ROLE_ID_RIGHT_TYPE, basicRoleRight.getRoleId() + ":" + basicRoleRight.getRightType());
        mapper.insertBasicRoleRights(basicRoleRights);
    }


    @Override
    public void deleteBasicRoleRight(BasicRoleRight basicRoleRight) {
        Assert.notNull(basicRoleRight, "BasicRoleRightDaoImpl.deleteBasicRoleRight basicRoleRight must not be null");
        Assert.notNull(basicRoleRight.getRoleId() + basicRoleRight.getRightType(), "BasicRoleRightDaoImpl.deleteBasicRoleRight roleId + rightType must not be null");
        redisService.del(REDIS_BASIC_ROLE_RIGHT_LIST_ROLE_ID_RIGHT_TYPE, basicRoleRight.getRoleId() + ":" + basicRoleRight.getRightType());
        mapper.deleteBasicRoleRight(basicRoleRight);
    }


    @Override
    public List<BasicRoleRight> listBasicRoleRightFromMysql(BasicRoleRight basicRoleRight) {
        List<BasicRoleRight> basicRoleRights = mapper.listBasicRoleRight(basicRoleRight);
        if (null != basicRoleRights) {
            return basicRoleRights;
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    public List<BasicRoleRight> listBasicRoleRightByRoleIdAndRightTypeFromRedis(String roleId, String rightType) {
        Assert.notNull(roleId + rightType, "BasicRoleRightDaoImpl.listBasicRoleRightByRoleIdFromRedis roleId + rightType must not be null");
        String json = redisService.get(REDIS_BASIC_ROLE_RIGHT_LIST_ROLE_ID_RIGHT_TYPE, roleId + ":" + rightType);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.parseObject(json, new TypeReference<List<BasicRoleRight>>() {
            });
        }
        BasicRoleRight basicRoleRight = new BasicRoleRight();
        basicRoleRight.setRoleId(roleId);
        List<BasicRoleRight> basicRoleRights = mapper.listBasicRoleRight(basicRoleRight);
        if (null != basicRoleRights && basicRoleRights.size() > 0) {
            redisService.setEntity(REDIS_BASIC_ROLE_RIGHT_LIST_ROLE_ID_RIGHT_TYPE, roleId + ":" + rightType, basicRoleRights);
            return basicRoleRights;
        } else {
            return new ArrayList<>();
        }
    }
}