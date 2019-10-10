package com.ly.mt.core.data.basic.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.basic.dao.BasicAreaDao;
import com.ly.mt.core.data.basic.entity.BasicArea;
import com.ly.mt.core.data.basic.mapper.BasicAreaMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_BASIC_AREA_ENTITY_M_ID;
import static com.ly.mt.core.redis.RedisKey.REDIS_BASIC_AREA_LIST_M_PID;

/**
 * BasicArea操作接口
 *
 * @author taoye
 */
@Service
public class BasicAreaDaoImpl extends BaseDaoServiceImpl implements BasicAreaDao {
    @Resource
    private BasicAreaMapper mapper;

    @Override
    public BasicArea getBasicAredByMIdFromRedis(String mId) {
        Assert.notNull(mId, "BasicAreaDaoImpl.getBasicAredByMIdFromRedis mId must not be null");
        String json = redisService.get(REDIS_BASIC_AREA_ENTITY_M_ID, mId);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), BasicArea.class);
        }
        BasicArea basicArea = new BasicArea();
        basicArea.setMId(mId);
        basicArea = mapper.getBasicArea(basicArea);
        if (null != basicArea) {
            redisService.setEntity(REDIS_BASIC_AREA_ENTITY_M_ID, mId, basicArea);
            return basicArea;
        } else {
            return new BasicArea();
        }
    }


    @Override
    public List<BasicArea> listBasicAreaByMPidFromRedis(String mPid) {
        Assert.notNull(mPid, "BasicAreaDaoImpl.listBasicAreaByMPidFromRedis mPid must not be null");
        String json = redisService.get(REDIS_BASIC_AREA_LIST_M_PID, mPid);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.parseObject(json, new TypeReference<List<BasicArea>>() {
            });
        }
        BasicArea basicArea = new BasicArea();
        basicArea.setMPid(mPid);
        List<BasicArea> list = mapper.listBasicArea(basicArea);
        if (null != list && list.size() > 0) {
            redisService.setEntity(REDIS_BASIC_AREA_LIST_M_PID, mPid, list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}