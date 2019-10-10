package com.ly.mt.core.data.goods.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.goods.dao.GoodsCategroyInfoDao;
import com.ly.mt.core.data.goods.entity.GoodsCategroyInfo;
import com.ly.mt.core.data.goods.mapper.GoodsCategroyInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_GOODS_CATEGROY_INFO_ENTITY_ID;
import static com.ly.mt.core.redis.RedisKey.REDIS_GOODS_CATEGROY_INFO_LIST_PARENT_ID;

/**
 * GoodsCategroyInfo操作接口
 *
 * @author taoye
 */
@Service
public class GoodsCategroyInfoDaoImpl extends BaseDaoServiceImpl implements GoodsCategroyInfoDao {
    @Resource
    GoodsCategroyInfoMapper mapper;

    @Override
    public GoodsCategroyInfo getGoodsCategroyInfoDaoByIdFromRedis(String id) {
        Assert.notNull(id, "GoodsCategroyInfoDaoImpl.getGoodsCategroyInfoDaoByIdFromRedis id must not be null");
        String json = redisService.get(REDIS_GOODS_CATEGROY_INFO_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), GoodsCategroyInfo.class);
        }
        GoodsCategroyInfo goodsCategroyInfo = new GoodsCategroyInfo();
        goodsCategroyInfo.setId(id);
        goodsCategroyInfo = mapper.getGoodsCategroyInfo(goodsCategroyInfo);
        if (null != goodsCategroyInfo) {
            redisService.setEntity(REDIS_GOODS_CATEGROY_INFO_ENTITY_ID, id, goodsCategroyInfo);
            return goodsCategroyInfo;
        } else {
            return new GoodsCategroyInfo();
        }
    }


    @Override
    public List<GoodsCategroyInfo> listGoodsCategroyInfoByParentIdFromRedis(String parentId) {
        Assert.notNull(parentId, "GoodsCategroyInfoDaoImpl.listGoodsCategroyInfoByParentIdFromRedis parentId must not be null");
        String json = redisService.get(REDIS_GOODS_CATEGROY_INFO_LIST_PARENT_ID, parentId);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.parseObject(json, new TypeReference<List<GoodsCategroyInfo>>() {
            });
        }
        GoodsCategroyInfo goodsCategroyInfo = new GoodsCategroyInfo();
        goodsCategroyInfo.setParentId(parentId);
        List<GoodsCategroyInfo> list = mapper.listGoodsCategroyInfo(goodsCategroyInfo);
        if (null != list && list.size() > 0) {
            redisService.setEntity(REDIS_GOODS_CATEGROY_INFO_LIST_PARENT_ID, parentId, list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}