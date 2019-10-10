package com.ly.mt.home.tob.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.goods.service.GoodsSkuService;
import com.ly.mt.home.tob.goods.vo.GoodsPictureVo;
import com.ly.mt.home.tob.goods.vo.GoodsSkuVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 商品sku业务层
 *
 * @author: linan
 * @date: 2019/9/19
 **/
@Service
public class GoodsSkuServiceImpl extends BaseServiceImpl implements GoodsSkuService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public GoodsSkuVo getGoodsSkuInfoBySpuIdAndAttr(String spuId, String attributes) {
        // GoodsSkuVo
        String result = callDataCenter(DataCenterMethod.GOODS_SKU_BY_SPUID_ATTR, (JSONObject) JSON.toJSON(
                new GoodsSkuVo.Builder().spuId(spuId).attributes(attributes).build()));
        GoodsSkuVo sku = JSONObject.toJavaObject(JSONObject.parseObject(
                result), GoodsSkuVo.class);

        // GoodsSkuPicture
        result = callDataCenter(DataCenterMethod.GOODS_SKU_PICTURE_QUERY, (JSONObject) JSON.toJSON(
                new GoodsPictureVo.Builder().skuId(sku.getId()).build()));
        GoodsPictureVo goodsPictureVo = JSONObject.parseArray(result, GoodsPictureVo.class).get(0);

        sku.setPictureList(new ArrayList() {
            {
                add(goodsPictureVo.getPictureUrl());
            }
        });

        return sku;
    }

    @Override
    public GoodsSkuVo getGoodsSkuInfoById(String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        String result = callDataCenter(DataCenterMethod.GOODS_SKU_INFO_GET_BY_ID, jsonObject);
        return JSONObject.parseObject(result, GoodsSkuVo.class);
    }


}
