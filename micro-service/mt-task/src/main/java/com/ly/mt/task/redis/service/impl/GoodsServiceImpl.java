package com.ly.mt.task.redis.service.impl;

import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.goods.dao.GoodsSkuCodeDao;
import com.ly.mt.core.data.goods.dao.GoodsSkuInfoDao;
import com.ly.mt.core.data.goods.dao.GoodsSkuPictureDao;
import com.ly.mt.core.data.goods.dao.GoodsSpuInfoDao;
import com.ly.mt.core.data.redis.entity.RedisRefresh;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.redis.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.TriggerType.TRIGGER_TYPE_DELETE;
import static com.ly.mt.core.redis.RedisKey.*;

/**
 * goods_* 缓存更新处理
 *
 * @author taoye
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {
    @Resource
    private GoodsSkuPictureDao goodsSkuPictureDao;
    @Resource
    private GoodsSkuCodeDao goodsSkuCodeDao;
    @Resource
    private GoodsSkuInfoDao goodsSkuInfoDao;
    @Resource
    private GoodsSpuInfoDao goodsSpuInfoDao;

    @Override
    public void refreshGoodsSkuPicture(RedisRefresh refresh) throws Exception {
        String skuId = refresh.getId1();
        if (StringUtil.isNotEmpty(skuId)) {
            redisService.del(REDIS_GOODS_SKU_PICTURE_ENTITY_SKU_ID, skuId);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                goodsSkuPictureDao.getGoodsSkuPictureBySkuIdFromRedis(skuId);
            }
        } else {
            throw new RuntimeException("GoodsServiceImpl.refreshGoodsSkuPicture refresh.id1 must not be null");
        }
    }


    @Override
    public void refreshGoodsSkuCode(RedisRefresh refresh) throws Exception {
        String skuId = refresh.getId1();
        if (StringUtil.isNotEmpty(skuId)) {
            redisService.del(REDIS_GOODS_SKU_CODE_ENTITY_SKU_ID, skuId);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                goodsSkuCodeDao.getGoodsSkuCodeBySkuIdFromRedis(skuId);
            }
        } else {
            throw new RuntimeException("GoodsServiceImpl.refreshGoodsSkuCode refresh.id1 must not be null");
        }
    }


    @Override
    public void refreshGoodsSkuInfo(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_GOODS_SKU_INFO_ENTITY_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                goodsSkuInfoDao.getGoodsSkuInfoByIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("GoodsServiceImpl.refreshGoodsSkuInfo refresh.id1 must not be null");
        }
    }


    @Override
    public void refreshGoodsSpuInfo(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_GOODS_SPU_INFO_ENTITY_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                goodsSpuInfoDao.getGoodsSpuInfoByIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("GoodsServiceImpl.refreshGoodsSpuInfo refresh.id1 must not be null");
        }
    }
}