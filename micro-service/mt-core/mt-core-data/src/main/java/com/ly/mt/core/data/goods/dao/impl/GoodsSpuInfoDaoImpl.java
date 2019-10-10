package com.ly.mt.core.data.goods.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.goods.dao.GoodsSpuInfoDao;
import com.ly.mt.core.data.goods.entity.GoodsSpuInfo;
import com.ly.mt.core.data.goods.mapper.GoodsSpuInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static com.ly.mt.core.redis.RedisKey.REDIS_GOODS_SPU_INFO_ENTITY_ID;

/**
 * GoodsSpuInfo操作接口
 *
 * @author taoye
 */
@Service
public class GoodsSpuInfoDaoImpl extends BaseDaoServiceImpl implements GoodsSpuInfoDao {
    @Resource
    private GoodsSpuInfoMapper mapper;


    @Override
    public GoodsSpuInfo getGoodsSpuInfoByIdFromRedis(String id) {
        Assert.notNull(id, "GoodsSpuInfoDaoImpl.getGoodsSpuInfoByIdFromRedis id must not be null");
        String goodsSpuInfoJson = redisService.get(REDIS_GOODS_SPU_INFO_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(goodsSpuInfoJson)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(goodsSpuInfoJson), GoodsSpuInfo.class);
        }
        GoodsSpuInfo goodsSpuInfo = new GoodsSpuInfo();
        goodsSpuInfo.setId(id);
        goodsSpuInfo = mapper.getGoodsSpuInfo(goodsSpuInfo);
        if (null != goodsSpuInfo) {
            redisService.setEntity(REDIS_GOODS_SPU_INFO_ENTITY_ID, id, goodsSpuInfo);
            return goodsSpuInfo;
        } else {
            return new GoodsSpuInfo();
        }
    }
}