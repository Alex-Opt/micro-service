package com.ly.mt.core.data.basic.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.basic.dao.BasicRoleDao;
import com.ly.mt.core.data.basic.entity.BasicRole;
import com.ly.mt.core.data.basic.mapper.BasicRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_BASIC_ROLE_ENTITY_ID;
import static com.ly.mt.core.redis.RedisKey.REDIS_BASIC_ROLE_LIST_PARENT_ID;

/**
 * BasicRole操作接口
 *
 * @author taoye
 */
@Service
public class BasicRoleDaoImpl extends BaseDaoServiceImpl implements BasicRoleDao {
    @Resource
    private BasicRoleMapper mapper;

    @Override
    public String insertBasicRole(BasicRole basicRole) {
        Assert.notNull(basicRole, "BasicRoleDaoImpl.insertBasicRole basicRole must not be null");

        String parentId = basicRole.getParentId();
        Assert.notNull(parentId, "BasicRoleDaoImpl.insertBasicRole basicRole.parentId must not be null");
        redisService.del(REDIS_BASIC_ROLE_LIST_PARENT_ID, parentId);

        int id = mapper.insertBasicRole(basicRole);
        redisService.setEntity(REDIS_BASIC_ROLE_ENTITY_ID, id);
        return String.valueOf(id);
    }


    @Override
    public int updateBasicRole(BasicRole basicRole) {
        Assert.notNull(basicRole, "BasicRoleDaoImpl.updateBasicRole basicRole must not be null");

        String id = basicRole.getId();
        Assert.notNull(id, "BasicRoleDaoImpl.updateBasicRole basicRole.id must not be null");
        redisService.del(REDIS_BASIC_ROLE_ENTITY_ID, id);

        BasicRole role = getBasicRoleByIdFromRedis(id);
        redisService.del(REDIS_BASIC_ROLE_LIST_PARENT_ID, role.getParentId());

        return mapper.updateBasicRole(basicRole);
    }


    @Override
    public BasicRole getBasicRoleByIdFromRedis(String id) {
        Assert.notNull(id, "BasicRoleDaoImpl.getBasicRoleByIdFromRedis id must not be null");
        String json = redisService.get(REDIS_BASIC_ROLE_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), BasicRole.class);
        }
        BasicRole basicRole = new BasicRole();
        basicRole.setId(id);
        basicRole = mapper.getBasicRole(basicRole);
        if (null != basicRole) {
            redisService.setEntity(REDIS_BASIC_ROLE_ENTITY_ID, id, basicRole);
            return basicRole;
        } else {
            return new BasicRole();
        }
    }


    @Override
    public List<BasicRole> listBasicRoleByParentIdFromRedis(String parentId) {
        Assert.notNull(parentId, "BasicRoleDaoImpl.listBasicRoleByParentIdFromRedis parentId must not be null");
        String json = redisService.get(REDIS_BASIC_ROLE_LIST_PARENT_ID, parentId);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.parseObject(json, new TypeReference<List<BasicRole>>() {
            });
        }
        BasicRole basicRole = new BasicRole();
        basicRole.setParentId(parentId);
        List<BasicRole> list = listBasicRoleFromMysql(basicRole);
        if (list.size() > 0) {
            redisService.setEntity(REDIS_BASIC_ROLE_LIST_PARENT_ID, parentId, list);
        }
        return list;
    }


    @Override
    public List<BasicRole> listBasicRoleFromMysql(BasicRole basicRole) {
        List<BasicRole> basicRoles = mapper.listBasicRole(basicRole);
        if (null != basicRoles) {
            return basicRoles;
        } else {
            return new ArrayList<>();
        }
    }
}