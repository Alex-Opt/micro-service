package com.ly.mt.mall.h5.goods.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.goods.service.GoodsSpuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.PicturePlaceholder.PICTURE_PLACEHOLDER_SPU_DETAIL;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.REDIS_GOODS_SELLER_COUNT;

/**
 * 商品spu业务层
 */
@Service
public class GoodsSpuServiceImpl extends BaseServiceImpl implements GoodsSpuService {

    private final static Logger Logger = LoggerFactory.getLogger(GoodsSpuServiceImpl.class);

    @Override
    public ResponseJson queryGoodsSpuById(String id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", id);
        String result = callDataCenter(GOODS_SPU_QUERY, json);
        JSONObject resultJson = null;

        if (StringUtil.isNotEmpty(result)) {
            resultJson = JSONObject.parseObject(result);
            //查询商品spu图片数据
            json = new JSONObject();
            json.put("spu_id", id);
            String pictTemp = callDataCenter(GOODS_SPU_PICTURE_BY_SPUID, json);
            List<String> pictureList = null;
            if ("[]".equals(pictTemp)) {
                pictureList = new ArrayList<>(1);
                pictureList.add(PICTURE_PLACEHOLDER_SPU_DETAIL.getId());
                resultJson.put("pictureList", pictureList);
            } else {
                resultJson.put("pictureList", JSONObject.parseArray(pictTemp));
            }

            //根据spuId查询属性
            String resultAttr = callDataCenter(GOODS_SPU_ATTR_BY_SPUID, json);
            JSONArray arrayAttr = JSONObject.parseArray(resultAttr);
            //根据spuId查询属性值
            String resultAttrValue = callDataCenter(GOODS_SPU_ATTR_VALUE_BY_SPUID, json);
            JSONArray arrayAttrValue = JSONObject.parseArray(resultAttrValue);
            for (int j = 0; j < arrayAttr.size(); j++) {
                JSONObject nodeAttr = arrayAttr.getJSONObject(j);
                String attrId = nodeAttr.getString("attrId");
                List valueList = null;
                for (int k = 0; k < arrayAttrValue.size(); k++) {
                    if (valueList == null) {
                        valueList = new ArrayList();
                    }
                    JSONObject nodeAttrValue = arrayAttrValue.getJSONObject(k);
                    if (attrId.equals(nodeAttrValue.getString("attrId"))) {
                        valueList.add(nodeAttrValue);
                    }
                }
                nodeAttr.put("attrValueList", valueList);
            }
            resultJson.put("attr", arrayAttr);
            //查询商品spu的优惠券数据
            resultJson.put("couponList", callDataCenter(COUPON_INFO_GET_BY_SPU_ID, json));
            //查询商品优惠活动数据    1,limit_type=20为全部商品 2，limit_type=30为限定商品
            resultJson.put("activityList", callDataCenter(ACTIVITY_INFO_GET_BY_SPU_ID, json));
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultJson);
    }

    @Override
    public ResponseJson queryGoodsSpuByCategroy(String cid, int page, int rows) throws Exception {
        JSONObject json = new JSONObject();
        json.put("cid", cid);
        json.put("page", page);
        json.put("rows", rows);
        String result = callDataCenter(GOODS_SPU_QUERY_BY_CATEGROY, json);
        JSONObject resultJson = JSONObject.parseObject(result);
        JSONArray array = resultJson.getJSONArray("rows");
        Map resultMap = new HashMap();
        resultMap.put("total", resultJson.getString("total"));
        List list = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            Map map = new HashMap();
            map.put("id", node.getString("id"));
            map.put("name", node.getString("name"));
            map.put("cid", node.getString("cid"));
            map.put("marketPrice", node.getString("market_price"));
            map.put("wholesalePrice", node.getString("wholesale_price"));
            map.put("pictureUrl", node.getString("picture_url"));
            map.put("pictureUrlC", node.getString("picture_url_c"));
            map.put("describeUrl", node.getString("describe_url"));
            json = new JSONObject();
            json.put("spu_id", node.getString("id"));
            String resultPicture = callDataCenter(GOODS_SPU_PICTURE_BY_SPUID, json);
            List<String> pictureList = null;
            if ("[]".equals(resultPicture)) {
                pictureList = new ArrayList<>(1);
                pictureList.add(PICTURE_PLACEHOLDER_SPU_DETAIL.getId());
                map.put("pictureList", pictureList);
            } else {
                map.put("pictureList", JSONObject.parseArray(resultPicture));
            }
            list.add(map);
        }
        resultMap.put("rows", list);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultMap);
    }

    @Override
    public ResponseJson queryGoodsSpuByAerosol(int page, int rows) throws Exception {
        JSONObject json = new JSONObject();
        json.put("page", page);
        json.put("rows", rows);
        String result = callDataCenter(GOODS_SPU_QUERY_BY_AEROSOL, json);
        JSONObject resultJson = JSONObject.parseObject(result);
        JSONArray array = resultJson.getJSONArray("rows");

        Map resultMap = new HashMap();
        resultMap.put("total", resultJson.getString("total"));
        List list = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            Map map = new HashMap();
            map.put("id", node.getString("id"));
            map.put("name", node.getString("name"));
            map.put("cid", node.getString("cid"));
            map.put("marketPrice", node.getString("market_price"));
            map.put("wholesalePrice", node.getString("wholesale_price"));
            map.put("pictureUrl", node.getString("picture_url"));
            map.put("pictureUrlC", node.getString("picture_url_c"));
            map.put("describeUrl", node.getString("describe_url"));
            json = new JSONObject();
            json.put("spu_id", node.getString("id"));
            String resultPicture = callDataCenter(GOODS_SPU_PICTURE_BY_SPUID, json);
            List<String> pictureList = null;
            if ("[]".equals(resultPicture)) {
                pictureList = new ArrayList<>(1);
                pictureList.add(PICTURE_PLACEHOLDER_SPU_DETAIL.getId());
                map.put("pictureList", pictureList);
            } else {
                map.put("pictureList", JSONObject.parseArray(resultPicture));
            }
            //根据spuId查询属性
            String resultAttr = callDataCenter(GOODS_SPU_ATTR_BY_SPUID, json);
            JSONArray arrayAttr = JSONObject.parseArray(resultAttr);
            //根据spuId查询属性值
            String resultAttrValue = callDataCenter(GOODS_SPU_ATTR_VALUE_BY_SPUID, json);
            JSONArray arrayAttrValue = JSONObject.parseArray(resultAttrValue);
            for (int j = 0; j < arrayAttr.size(); j++) {
                JSONObject nodeAttr = arrayAttr.getJSONObject(j);
                String attrId = nodeAttr.getString("attrId");
                List valueList = null;
                for (int k = 0; k < arrayAttrValue.size(); k++) {
                    if (valueList == null) {
                        valueList = new ArrayList();
                    }
                    JSONObject nodeAttrValue = arrayAttrValue.getJSONObject(k);
                    if (attrId.equals(nodeAttrValue.getString("attrId"))) {
                        valueList.add(nodeAttrValue);
                    }
                }
                nodeAttr.put("attrValueList", valueList);
            }
            map.put("attr", arrayAttr);
            list.add(map);
        }
        resultMap.put("rows", list);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultMap);
    }

    @Override
    public ResponseJson queryCategroyList(String parentId) throws Exception {
        JSONObject json = new JSONObject();
        json.put("parent_id", parentId);
        String result = callDataCenter(GOODS_CATEGROY_INFO_GET_BY_PARENT_ID, json);
        JSONArray array = JSONObject.parseArray(result);
        List list = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            Map map = new HashMap();
            map.put("id", node.getString("id"));
            map.put("name", node.getString("name"));
            list.add(map);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }

    @Override
    public ResponseJson queryHourSpu(int page, int rows) throws Exception {
        JSONObject json = new JSONObject();
        json.put("page", page);
        json.put("rows", rows);
        String result = callDataCenter(GOODS_HOUR_SPU_QUERY_LIST, json);
       /*JSONObject resultJson = JSONObject.parseObject(result);
        JSONArray array = resultJson.getJSONArray("rows");
        Map resultMap = new HashMap();
        resultMap.put("total", resultJson.getString("total"));
        List list = new ArrayList();*/
        //  for (int i = 0; i < array.size(); i++) {
        //     JSONObject node = array.getJSONObject(i);
        //    Map map = new HashMap();
        //     map.put("id", node.getString("id"));
        //    map.put("nameGy", node.getString("name_gy"));
        //  map.put("name", node.getString("name"));
        //  map.put("code", node.getString("code"));
        //  map.put("cid", node.getString("cid"));
        //  map.put("brandId", node.getString("brand_id"));
        // map.put("marketPrice", node.getString("market_price"));
        // map.put("wholesalePrice", node.getString("market_price"));
        /// map.put("weight", node.getString("weight"));
        ///map.put("describeUrl", node.getString("describe_url"));
        ///map.put("spuAttr", node.getString("spu_attr"));
        ///map.put("spuStatus", node.getString("spu_status"));
        ///map.put("dataSource", node.getString("data_source"));
        // map.put("createTime", node.getString("create_time"));
        //map.put("remark", node.getString("remark"));
        //json.put("spu_id", node.getString("id"));
        // String resultPicture = callDataCenter(GOODS_SPU_PICTURE_BY_SPUID, json);*//*
        //map.put("pictureUrl", node.getString("picture_url"));
        //map.put("pictureUrlc", node.getString("picture_url_c"));
        //list.add(map);
        /// }
        //resultMap.put("rows", list);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, result);
    }


    @Override
    public ResponseJson queryGoodsHourSpuById(String id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", id);
        String result = callDataCenter(GOODS_SPU_QUERY, json);
        JSONObject resultJson = null;

        if (StringUtil.isNotEmpty(result)) {
            resultJson = JSONObject.parseObject(result);
            //查询商品spu图片数据
            json = new JSONObject();
            json.put("spu_id", id);
            String pictureTemp = callDataCenter(GOODS_SPU_PICTURE_BY_SPUID, json);
            List<String> pictureList = null;
            if ("[]".equals(pictureTemp)) {
                pictureList = new ArrayList<>(1);
                pictureList.add(PICTURE_PLACEHOLDER_SPU_DETAIL.getId());
                resultJson.put("pictureList", pictureList);
            } else {
                resultJson.put("pictureList", JSONObject.parseArray(pictureTemp));
            }
            //根据spuId查询属性
            String resultAttr = callDataCenter(GOODS_SPU_ATTR_BY_SPUID, json);
            JSONArray arrayAttr = JSONObject.parseArray(resultAttr);
            //根据spuId查询属性值
            String resultAttrValue = callDataCenter(GOODS_SPU_ATTR_VALUE_BY_SPUID, json);
            JSONArray arrayAttrValue = JSONObject.parseArray(resultAttrValue);
            String skuAttrIds = callDataCenter(GOODS_SPU_HOUR_ATTR_VALUE_BY_SPUID, json);
            List<String> attrList = JSONObject.parseArray(skuAttrIds, String.class);
            for (int j = 0; j < arrayAttr.size(); j++) {
                JSONObject nodeAttr = arrayAttr.getJSONObject(j);
                String attrId = nodeAttr.getString("attrId");
                List valueList = null;
                for (int k = 0; k < arrayAttrValue.size(); k++) {
                    if (valueList == null) {
                        valueList = new ArrayList();
                    }
                    JSONObject nodeAttrValue = arrayAttrValue.getJSONObject(k);
                    if (attrId.equals(nodeAttrValue.getString("attrId")) && attrList.contains(nodeAttrValue.getString("attrValueId"))) {
                        valueList.add(nodeAttrValue);
                    }
                }
                nodeAttr.put("attrValueList", valueList);
            }
            resultJson.put("attr", arrayAttr);
            //查询商品spu的优惠券数据
            resultJson.put("couponList", callDataCenter(COUPON_INFO_GET_BY_SPU_ID, json));
            //查询商品优惠活动数据    1,limit_type=20为全部商品 2，limit_type=30为限定商品
            resultJson.put("activityList", callDataCenter(ACTIVITY_INFO_GET_BY_SPU_ID, json));
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultJson);
    }

    @Override
    public ResponseJson countGoodsSellerEachMonth(String spuId) throws Exception {
        String yearMonthStr = DateUtil.thisYearMonthStr();
        String earlyMonthStr = DateUtil.earlyMonthStr();
        String nowTimeStr = DateUtil.getNowTimeStr();
        String sellerNumber = redisService.get(REDIS_GOODS_SELLER_COUNT, yearMonthStr + "_" + spuId);
        if (StringUtil.isEmpty(sellerNumber)) {
            //redis缓存不存在则走库查询了。
            JSONObject requestJson = new JSONObject();
            requestJson.put("earlyMonthStr", earlyMonthStr);
            requestJson.put("nowTimeStr", nowTimeStr);
            requestJson.put("spuId", spuId);
            String dataCenterJson = callDataCenter(GOODS_SPU_QUERY_SELLER_BY_ID, requestJson);
            JSONObject data = JSONObject.parseObject(dataCenterJson);
            sellerNumber = data.getString("sellerNumber") == null ? "0" : data.getString("sellerNumber");
            redisService.set(REDIS_GOODS_SELLER_COUNT, yearMonthStr + "_" + spuId, sellerNumber, 31L, TimeUnit.DAYS);
        }
        JSONObject resultJson = new JSONObject();
        resultJson.put("sellerNumber", sellerNumber);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultJson);
    }

    @Override
    public ResponseJson querySpuConfigGoodsByUserType(String systemUserType) throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("system_user_type", systemUserType);
        String dataCenterJson1 = callDataCenter(GOODS_SPU_CONFIGURATION_QUERY, requestJson);
        Logger.info("----------查询出来的商品spuIds的集合------------------dataCenterJson:{}",dataCenterJson1);
        JSONObject paramListJson = new JSONObject();
        paramListJson.put("spuIdList",dataCenterJson1);
        String dataCenterJson2 = callDataCenter(GOODS_SPU_INFO_BY_SPU_IDS, paramListJson);
        Logger.info("----------查询出来的商品信息集合-----------dataCenterJson2：{}",dataCenterJson2);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, dataCenterJson2);
    }
}
