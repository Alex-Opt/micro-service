package com.ly.mt.core.data.gzg.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.gzg.dao.GzgHotelDao;
import com.ly.mt.core.data.gzg.entity.GzgHotel;
import com.ly.mt.core.data.gzg.mapper.GzgHotelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static com.ly.mt.core.redis.RedisKey.REDIS_GZG_HOTEL_ENTITY_ID;

/**
 * GzgHotel操作接口
 *
 * @author taoye
 */
@Service
public class GzgHotelDaoImpl extends BaseDaoServiceImpl implements GzgHotelDao {
    @Resource
    private GzgHotelMapper mapper;


    @Override
    public GzgHotel getGzgHotelByIdFromRedis(String id) {
        Assert.notNull(id, "GzgHotelDaoImpl.getGzgHotelByIdFromRedis id must not be null");
        String gzgHotelJson = redisService.get(REDIS_GZG_HOTEL_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(gzgHotelJson)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(gzgHotelJson), GzgHotel.class);
        }
        GzgHotel gzgHotel = new GzgHotel();
        gzgHotel.setId(id);
        gzgHotel = mapper.getGzgHotel(gzgHotel);
        if (null != gzgHotel) {
            redisService.setEntity(REDIS_GZG_HOTEL_ENTITY_ID, id, gzgHotel);
            return gzgHotel;
        } else {
            return new GzgHotel();
        }
    }
}