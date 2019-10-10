package com.ly.mt.mall.h5.goods.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.goods.service.GoodsSkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * 商品sku业务层
 */
@Service
public class GoodsSkuServiceImpl extends BaseServiceImpl implements GoodsSkuService {

    private final static Logger Logger = LoggerFactory.getLogger(GoodsSkuServiceImpl.class);

    @Override
    public ResponseJson getGoodsSkuInfoBySpuIdAndAttr(String spuId, String attributes) throws Exception {
        JSONObject json = new JSONObject();
        json.put("spu_id", spuId);
        json.put("attributes", attributes);
        String result = callDataCenter(GOODS_SKU_BY_SPUID_ATTR, json);
        JSONObject resultJson = new JSONObject();
        if (StringUtil.isNotEmpty(result)) {
            JSONObject skuJson = JSONObject.parseObject(result);
            resultJson.put("id", skuJson.getString("id"));
            resultJson.put("name", skuJson.getString("name"));
            resultJson.put("spuId", skuJson.getString("spu_id"));
            resultJson.put("marketPrice", skuJson.getString("market_price"));
            resultJson.put("wholesalePrice", skuJson.getString("wholesale_price"));
            resultJson.put("attributes", skuJson.getString("attributes"));

            json = new JSONObject();
            json.put("sku_id", skuJson.getString("id"));
            result = callDataCenter(GOODS_SKU_PICTURE_QUERY, json);
            JSONArray array = JSONObject.parseArray(result);
            List list = new ArrayList();
            for (int i = 0; i < array.size(); i++) {
                JSONObject node = array.getJSONObject(i);
                if (StringUtil.isNotEmpty(node.getString("picture_url"))) {
                    list.add(node.getString("picture_url"));
                }
            }
            resultJson.put("pictureList", list);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultJson);
    }


    @Override
    public ResponseJson getSkuInfoBySpuIdForOk(String spuId) throws Exception {
        JSONObject json = new JSONObject();
        json.put("spu_id", spuId);
        String result = callDataCenter(GOODS_SKU_BY_SPUID, json);
        JSONArray array = JSONObject.parseArray(result);
        List resultList = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            JSONObject resultJson = new JSONObject();
            resultJson.put("id", node.getString("id"));
            resultJson.put("name", node.getString("name"));
            resultJson.put("spuId", node.getString("spu_id"));
            resultJson.put("marketPrice", node.getString("market_price"));
            resultJson.put("wholesalePrice", node.getString("wholesale_price"));
            resultJson.put("attributes", node.getString("attributes"));
            resultList.add(resultJson);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultList);
    }


}
