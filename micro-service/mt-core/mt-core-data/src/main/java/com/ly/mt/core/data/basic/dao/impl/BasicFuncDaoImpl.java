package com.ly.mt.core.data.basic.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.basic.dao.BasicFuncDao;
import com.ly.mt.core.data.basic.entity.BasicFunc;
import com.ly.mt.core.data.basic.mapper.BasicFuncMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.*;

/**
 * BasicFunc操作接口
 *
 * @author taoye
 */
@Service
public class BasicFuncDaoImpl extends BaseDaoServiceImpl implements BasicFuncDao {
    @Resource
    private BasicFuncMapper mapper;

    @Override
    public void insertBasicFunc(List<BasicFunc> basicFuncs) {
        Assert.notNull(basicFuncs, "BasicFuncServiceImpl.insertBasicFunc basicFuncs must not be null");
        mapper.insertBasicFunc(basicFuncs);
        basicFuncs.forEach(
                basicFunc -> {
                    redisService.setEntity(REDIS_BASIC_FUNC_ENTITY_ID, basicFunc.getId(), basicFunc);
                    redisService.set(REDIS_BASIC_FUNC_STRING_MAX_SORT_PARENT_ID, basicFunc.getParentId(), basicFunc.getSort());
                    redisService.del(REDIS_BASIC_FUNC_LIST_PROJECT_TYPE_FUNC_LEVEL, basicFunc.getProjectType() + ":" + basicFunc.getFuncLevel());
                    redisService.del(REDIS_BASIC_FUNC_LIST_PROJECT_TYPE_PARENT_ID, basicFunc.getProjectType() + ":" + basicFunc.getParentId());
                }
        );
    }


    @Override
    public void updateBasicFunc(BasicFunc basicFunc) {
        BasicFunc bf = getBasicFunc(basicFunc);
        redisService.del(REDIS_BASIC_FUNC_ENTITY_ID, bf.getId());
        redisService.del(REDIS_BASIC_FUNC_LIST_PROJECT_TYPE_FUNC_LEVEL, bf.getProjectType() + ":" + bf.getFuncLevel());
        redisService.del(REDIS_BASIC_FUNC_LIST_PROJECT_TYPE_PARENT_ID, bf.getProjectType() + ":" + bf.getParentId());
        mapper.updateBasicFunc(basicFunc);
    }


    @Override
    public BasicFunc getBasicFunc(BasicFunc basicFunc) {
        String id = basicFunc.getId();
        if (StringUtil.isNotEmpty(id)) {
            return getBasicFuncByIdFromRedis(id);
        }
        basicFunc = mapper.getBasicFunc(basicFunc);
        if (null != basicFunc) {
            redisService.setEntity(REDIS_BASIC_FUNC_ENTITY_ID, id, basicFunc);
            return basicFunc;
        } else {
            return new BasicFunc();
        }
    }


    @Override
    public BasicFunc getBasicFuncByIdFromRedis(String id) {
        Assert.notNull(id, "BasicFuncServiceImpl.getBasicFuncByIdFromRedis id must not be null");
        String json = redisService.get(REDIS_BASIC_FUNC_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), BasicFunc.class);
        }
        BasicFunc basicFunc = new BasicFunc();
        basicFunc.setId(id);
        basicFunc = mapper.getBasicFunc(basicFunc);
        if (null != basicFunc) {
            redisService.setEntity(REDIS_BASIC_FUNC_ENTITY_ID, id, basicFunc);
            return basicFunc;
        } else {
            return new BasicFunc();
        }
    }


    @Override
    public List<BasicFunc> listBasicFuncByProjectTypeAndFuncLevelFromRedis(String projectType, String funcLevel) {
        Assert.notNull(projectType, "BasicFuncDaoImpl.listBasicFuncByProjectTypeAndFuncLevelFromRedis projectType must not be null");
        Assert.notNull(funcLevel, "BasicFuncDaoImpl.listBasicFuncByProjectTypeAndFuncLevelFromRedis funcLevel must not be null");
        String BasicFuncs = redisService.get(REDIS_BASIC_FUNC_LIST_PROJECT_TYPE_FUNC_LEVEL, projectType + ":" + funcLevel);
        if (StringUtil.isNotEmpty(BasicFuncs)) {
            return JSONObject.parseObject(BasicFuncs, new TypeReference<List<BasicFunc>>() {
            });
        }
        BasicFunc BasicFunc = new BasicFunc();
        BasicFunc.setFuncLevel(funcLevel);
        BasicFunc.setProjectType(projectType);
        List<BasicFunc> list = mapper.listBasicFunc(BasicFunc);
        if (null != list && list.size() > 0) {
            redisService.setEntity(REDIS_BASIC_FUNC_LIST_PROJECT_TYPE_FUNC_LEVEL, projectType + ":" + funcLevel, list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    public List<BasicFunc> listBasicFuncByProjectTypeAndParentIdFromRedis(String projectType, String parentId) {
        Assert.notNull(projectType, "BasicFuncDaoImpl.listBasicFuncByProjectTypeAndParentIdFromRedis projectType must not be null");
        Assert.notNull(parentId, "BasicFuncDaoImpl.listBasicFuncByProjectTypeAndParentIdFromRedis parentId must not be null");
        String BasicFuncs = redisService.get(REDIS_BASIC_FUNC_LIST_PROJECT_TYPE_PARENT_ID, projectType + ":" + parentId);
        if (StringUtil.isNotEmpty(BasicFuncs)) {
            return JSONObject.parseObject(BasicFuncs, new TypeReference<List<BasicFunc>>() {
            });
        }
        BasicFunc BasicFunc = new BasicFunc();
        BasicFunc.setParentId(parentId);
        List<BasicFunc> list = mapper.listBasicFunc(BasicFunc);
        if (null != list && list.size() > 0) {
            redisService.setEntity(REDIS_BASIC_FUNC_LIST_PROJECT_TYPE_PARENT_ID, projectType + ":" + parentId, list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    public String getMaxSortByParentIdFromRedis(String parentId) {
        Assert.notNull(parentId, "BasicFuncDaoImpl.getMaxSortByParentId parentId must not be null");
        String maxSort = redisService.get(REDIS_BASIC_FUNC_STRING_MAX_SORT_PARENT_ID, parentId);
        if (StringUtil.isEmpty(maxSort)) {
            maxSort = mapper.getMaxSortByParentId(parentId);
        }
        if (StringUtil.isEmpty(maxSort)) {
            maxSort = "0";
        }
        return maxSort;
    }
}