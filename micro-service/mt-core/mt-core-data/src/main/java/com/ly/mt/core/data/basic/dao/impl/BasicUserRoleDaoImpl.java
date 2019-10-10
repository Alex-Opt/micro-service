package com.ly.mt.core.data.basic.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.basic.dao.BasicUserRoleDao;
import com.ly.mt.core.data.basic.entity.BasicUserRole;
import com.ly.mt.core.data.basic.mapper.BasicUserRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_BASIC_USER_ROLE_LIST_ROLE_ID;
import static com.ly.mt.core.redis.RedisKey.REDIS_BASIC_USER_ROLE_LIST_USER_ID;

/**
 * BasicUserRole操作接口
 *
 * @author taoye
 */
@Service
public class BasicUserRoleDaoImpl extends BaseDaoServiceImpl implements BasicUserRoleDao {
    @Resource
    private BasicUserRoleMapper mapper;


    @Override
    public void insertBasicUserRole(BasicUserRole basicUserRole) {
        Assert.notNull(basicUserRole, "BasicUserRoleDaoImpl.insertBasicUserRole basicUserRole must not be null");

        String userId = basicUserRole.getUserId();
        Assert.notNull(userId, "BasicUserRoleDaoImpl.insertBasicUserRole basicUserRole.userId must not be null");
        redisService.del(REDIS_BASIC_USER_ROLE_LIST_USER_ID, userId);

        String roleId = basicUserRole.getRoleId();
        Assert.notNull(basicUserRole.getRoleId(), "BasicUserRoleDaoImpl.insertBasicUserRole basicUserRole.roleId must not be null");
        redisService.del(REDIS_BASIC_USER_ROLE_LIST_ROLE_ID, roleId);

        mapper.insertBasicUserRole(basicUserRole);
    }


    @Override
    public void deleteBasicUserRole(BasicUserRole basicUserRole) {
        Assert.notNull(basicUserRole, "BasicUserRoleDaoImpl.insertBasicUserRole basicUserRole must not be null");
        String userId = basicUserRole.getUserId();
        String roleId = basicUserRole.getRoleId();
        Assert.notNull(userId + roleId, "BasicUserRoleDaoImpl.insertBasicUserRole basicUserRole.userId or basicUserRole.roleId must not be null");

        if (StringUtil.isNotEmpty(userId) && StringUtil.isNotEmpty(roleId)) {
            redisService.del(REDIS_BASIC_USER_ROLE_LIST_USER_ID, userId);
            redisService.del(REDIS_BASIC_USER_ROLE_LIST_USER_ID, roleId);
        } else if (StringUtil.isNotEmpty(userId)) {
            List<BasicUserRole> list = listBasicUserRoleByUserIdFromRedis(userId);
            list.forEach(
                    role -> redisService.del(REDIS_BASIC_USER_ROLE_LIST_ROLE_ID, role.getRoleId())
            );
            redisService.del(REDIS_BASIC_USER_ROLE_LIST_USER_ID, userId);
        } else if (StringUtil.isNotEmpty(roleId)) {
            List<BasicUserRole> list = listBasicUserRoleByRoleIdFromRedis(roleId);
            list.forEach(
                    role -> redisService.del(REDIS_BASIC_USER_ROLE_LIST_USER_ID, role.getUserId())
            );
            redisService.del(REDIS_BASIC_USER_ROLE_LIST_ROLE_ID, roleId);
        }

        mapper.deleteBasicUserRole(basicUserRole);
    }


    @Override
    public List<BasicUserRole> listBasicUserRoleByRoleIdFromRedis(String roleId) {
        Assert.notNull(roleId, "BasicUserRoleDaoImpl.listBasicUserRoleByRoleIdFromRedis roleId must not be null");
        String json = redisService.get(REDIS_BASIC_USER_ROLE_LIST_ROLE_ID, roleId);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.parseObject(json, new TypeReference<List<BasicUserRole>>() {
            });
        }
        BasicUserRole basicUserRole = new BasicUserRole();
        basicUserRole.setRoleId(roleId);
        List<BasicUserRole> list = mapper.listBasicUserRole(basicUserRole);
        if (null != list && list.size() > 0) {
            redisService.setEntity(REDIS_BASIC_USER_ROLE_LIST_ROLE_ID, roleId, list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    public List<BasicUserRole> listBasicUserRoleByUserIdFromRedis(String userId) {
        Assert.notNull(userId, "BasicUserRoleDaoImpl.listBasicUserRoleByUserIdFromRedis userId must not be null");
        String json = redisService.get(REDIS_BASIC_USER_ROLE_LIST_USER_ID, userId);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.parseObject(json, new TypeReference<List<BasicUserRole>>() {
            });
        }
        BasicUserRole basicUserRole = new BasicUserRole();
        basicUserRole.setUserId(userId);
        List<BasicUserRole> list = mapper.listBasicUserRole(basicUserRole);
        if (null != list && list.size() > 0) {
            redisService.setEntity(REDIS_BASIC_USER_ROLE_LIST_USER_ID, userId, list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}