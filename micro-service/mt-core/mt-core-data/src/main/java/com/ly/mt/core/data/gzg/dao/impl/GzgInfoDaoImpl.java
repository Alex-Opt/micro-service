package com.ly.mt.core.data.gzg.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.cabinet.entity.CabinetPlan;
import com.ly.mt.core.data.gzg.dao.GzgInfoDao;
import com.ly.mt.core.data.gzg.entity.GzgInfo;
import com.ly.mt.core.data.gzg.mapper.GzgInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.redis.RedisKey.REDIS_GZG_INFO_ENTITY_CODE;

/**
 * GzgInfo操作接口
 *
 * @author taoye
 */
@Service
public class GzgInfoDaoImpl extends BaseDaoServiceImpl implements GzgInfoDao {
    @Resource
    private GzgInfoMapper mapper;

    @Override
    public GzgInfo getGzgInfoByCodeFromRedis(String code) {
        Assert.notNull(code, "GzgInfoDaoImpl.getGzgInfoByCodeFromRedis code must not be null");
        String gzgInfoJson = redisService.get(REDIS_GZG_INFO_ENTITY_CODE, code);
        if (StringUtil.isNotEmpty(gzgInfoJson)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(gzgInfoJson), GzgInfo.class);
        }
        GzgInfo gzgInfo = new GzgInfo();
        gzgInfo.setCode(code);
        gzgInfo = mapper.getGzgInfo(gzgInfo);
        if (null != gzgInfo) {
            redisService.setEntity(REDIS_GZG_INFO_ENTITY_CODE, code, gzgInfo);
            return gzgInfo;
        } else {
            return new GzgInfo();
        }
    }


    @Override
    public Map<Long, Map<String, Long>> getCountByCabinetPlansFromMysql(List<CabinetPlan> cabinetPlans) {
        Assert.notNull(cabinetPlans, "GzgInfoDaoImpl.getCountByCabinetPlansFromMysql cabinetPlans must not be null");
        Map<Long, Map<String, Long>> map = mapper.getCountByCabinetPlans(cabinetPlans);
        if (null != map) {
            return map;
        } else {
            return new HashMap<>();
        }
    }
}