package com.ly.mt.core.data.goods.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.goods.dao.GoodsSkuCodeDao;
import com.ly.mt.core.data.goods.entity.GoodsSkuCode;
import com.ly.mt.core.data.goods.mapper.GoodsSkuCodeMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_GOODS_SKU_CODE_ENTITY_SKU_ID;

/**
 * GoodsSkuCode操作接口
 *
 * @author taoye
 */
@Service
public class GoodsSkuCodeDaoImpl extends BaseDaoServiceImpl implements GoodsSkuCodeDao {
    @Resource
    private GoodsSkuCodeMapper mapper;


    @Override
    public void insertGoodsSkuCodes(List<GoodsSkuCode> goodsSkuCodes) {
        Assert.notNull(goodsSkuCodes, "GoodsSkuCodeDaoImpl.insertGoodsSkuCode goodsSkuCodes must not be null");
        goodsSkuCodes.forEach(
                goodsSkuCode -> redisService.setEntity(REDIS_GOODS_SKU_CODE_ENTITY_SKU_ID, goodsSkuCode.getSkuId(), goodsSkuCode)
        );
        mapper.insertGoodsSkuCodes(goodsSkuCodes);
    }


    @Override
    public GoodsSkuCode getGoodsSkuCodeBySkuIdFromRedis(String skuId) {
        Assert.notNull(skuId, "GoodsSkuCodeDaoImpl.getGoodsSkuCodeBySkuIdFromRedis skuId must not be null");
        String goodsSkuCodeJson = redisService.get(REDIS_GOODS_SKU_CODE_ENTITY_SKU_ID, skuId);
        if (StringUtil.isNotEmpty(goodsSkuCodeJson)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(goodsSkuCodeJson), GoodsSkuCode.class);
        }
        GoodsSkuCode goodsSkuCode = new GoodsSkuCode();
        goodsSkuCode.setSkuId(skuId);
        goodsSkuCode = mapper.getGoodsSkuCode(goodsSkuCode);
        if (null != goodsSkuCode) {
            redisService.setEntity(REDIS_GOODS_SKU_CODE_ENTITY_SKU_ID, skuId, goodsSkuCode);
            return goodsSkuCode;
        } else {
            return new GoodsSkuCode();
        }
    }
}