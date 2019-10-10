package com.ly.mt.core.data.goods.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.goods.dao.GoodsSkuInfoDao;
import com.ly.mt.core.data.goods.entity.GoodsSkuInfo;
import com.ly.mt.core.data.goods.mapper.GoodsSkuInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static com.ly.mt.core.redis.RedisKey.REDIS_GOODS_SKU_INFO_ENTITY_ID;

/**
 * GoodsSkuInfo操作接口
 *
 * @author taoye
 */
@Service
public class GoodsSkuInfoDaoImpl extends BaseDaoServiceImpl implements GoodsSkuInfoDao {
    @Resource
    private GoodsSkuInfoMapper mapper;


    @Override
    public GoodsSkuInfo getGoodsSkuInfoByIdFromRedis(String id) {
        Assert.notNull(id, "GoodsSkuInfoDaoImpl.getGoodsSkuInfoByIdFromRedis id must not be null");
        String goodsSkuInfoJson = redisService.get(REDIS_GOODS_SKU_INFO_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(goodsSkuInfoJson)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(goodsSkuInfoJson), GoodsSkuInfo.class);
        }
        GoodsSkuInfo goodsSkuInfo = new GoodsSkuInfo();
        goodsSkuInfo.setId(id);
        goodsSkuInfo = mapper.getGoodsSkuInfo(goodsSkuInfo);
        if (null != goodsSkuInfo) {
            redisService.setEntity(REDIS_GOODS_SKU_INFO_ENTITY_ID, id, goodsSkuInfo);
            return goodsSkuInfo;
        } else {
            return new GoodsSkuInfo();
        }
    }
}