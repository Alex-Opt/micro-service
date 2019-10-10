package com.ly.mt.core.data.goods.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.goods.dao.GoodsSkuPictureDao;
import com.ly.mt.core.data.goods.entity.GoodsSkuPicture;
import com.ly.mt.core.data.goods.mapper.GoodsSkuPictureMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static com.ly.mt.core.redis.RedisKey.REDIS_GOODS_SKU_PICTURE_ENTITY_SKU_ID;

/**
 * GoodsSkuPicture操作接口
 *
 * @author taoye
 */
@Service
public class GoodsSkuPictureDaoImpl extends BaseDaoServiceImpl implements GoodsSkuPictureDao {
    @Resource
    private GoodsSkuPictureMapper mapper;

    @Override
    public GoodsSkuPicture getGoodsSkuPictureBySkuIdFromRedis(String skuId) {
        Assert.notNull(skuId, "GoodsSkuPictureDaoImpl.getGoodsSkuPictureBySkuId skuId must not be null");
        String goodsSkuPictureJson = redisService.get(REDIS_GOODS_SKU_PICTURE_ENTITY_SKU_ID, skuId);
        if (StringUtil.isNotEmpty(goodsSkuPictureJson)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(goodsSkuPictureJson), GoodsSkuPicture.class);
        }
        GoodsSkuPicture goodsSkuPicture = new GoodsSkuPicture();
        goodsSkuPicture.setSkuId(skuId);
        goodsSkuPicture = mapper.getGoodsSkuPicture(goodsSkuPicture);
        if (null != goodsSkuPicture) {
            redisService.setEntity(REDIS_GOODS_SKU_PICTURE_ENTITY_SKU_ID, skuId, goodsSkuPicture);
            return goodsSkuPicture;
        } else {
            return new GoodsSkuPicture();
        }
    }
}