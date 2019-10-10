package com.ly.mt.mall.h5.buycar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.buycar.service.BuyCarService;
import com.ly.mt.mall.h5.buycar.vo.BuyCar;
import com.ly.mt.mall.h5.buycar.vo.CarSku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ly.mt.core.redis.RedisKey.REDIS_ENTITY_CAR_ID;
import static com.ly.mt.core.base.dict.PicturePlaceholder.PICTURE_PLACEHOLDER_SKU;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.feign.DataCenterMethod.*;


/**
 * 购物车Service
 *
 * @author ypmu
 * @date 20190520
 */
@Service
public class BuyCarServiceImpl extends BaseServiceImpl implements BuyCarService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BuyCarServiceImpl.class);

    @Override
    public ResponseJson addCar(String skuId, String num) throws Exception {
        String buyerId = getLoginUserId();
        if (StringUtil.isEmpty(buyerId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        //从redis中获取购物车
        BuyCar buyCar = getCarFromRedis(buyerId);
        if (buyCar == null) {
            buyCar = new BuyCar();
            buyCar.setBuyerId(buyerId);
        }
        JSONObject json = new JSONObject();
        json.put("id", skuId);
        String result = callDataCenter(GOODS_SKU_QUERY, json);
        CarSku carSku = copyProperties(num, JSONObject.parseObject(result));
        buyCar.addItems(carSku);
        //将新的购物车写回redis
        writeCarToRedis(buyCar);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("buyCarSkuNum", buyCar.getItems().size());
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonObject);
    }

    @Override
    public ResponseJson subCarSkuNum(String skuId, String num) throws Exception {
        String buyerId = getLoginUserId();
        if (StringUtil.isEmpty(buyerId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        //从redis中获取购物车
        BuyCar buyCar = getCarFromRedis(buyerId);
        if (buyCar == null) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        }

        //购物车中删除sku的数量
        //如果删除后的数量为0，则将该sku移除出购物车
        //buyCar.subCarNum(skuId, Integer.parseInt(num));
        CarSku itemSku = buyCar.getItems().get(skuId);
        if(itemSku==null){
            return addCar(skuId,num);
        }
        buyCar.updateCarNum(skuId, Integer.parseInt(num));
        //判断购物车是否还有商品，
        //如果有，写入redis
        //如果没有，则将其从redis中清除
        Map<String, CarSku> items = buyCar.getItems();
        if (!items.isEmpty()) {
            //将新的购物车写回redis
            writeCarToRedis(buyCar);
        } else {
            //清除购物车
            clearCar(buyCar);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @Override
    public ResponseJson subCarSku(String skuId) throws Exception {
        String buyerId = getLoginUserId();
        if (StringUtil.isEmpty(buyerId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }

        //从redis中获取购物车//设置返回值
        BuyCar buyCar = getCarFromRedis(buyerId);
        if (buyCar == null) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        String[] array = skuId.split(",");
        for (String id : array) {
            buyCar.subCar(id);
        }
        //判断购物车是否还有商品
        Map<String, CarSku> items = buyCar.getItems();
        if (!items.isEmpty()) {
            //将新的购物车写回redis
            writeCarToRedis(buyCar);
        } else {
            //JsonUtil.getErrorJson(RESULT_CODE_BUYCAR_NULL_ERROR);
            //清除购物车
            clearCar(buyCar);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @Override
    public ResponseJson getBuyCar() throws Exception {
        String buyerId = getLoginUserId();
        if (StringUtil.isEmpty(buyerId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        //从redis中获取购物车
        BuyCar buyCar = getCarFromRedis(buyerId);
        if (buyCar == null || buyCar.getItems().isEmpty()) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        }
        Map<String, CarSku> items = buyCar.getItems();


        for (String key : items.keySet()) {
            CarSku carSku = items.get(key);
            //根据carSku里面的skuId在一小时达到商品表匹配


            JSONObject json = new JSONObject();
            json.put("sku_id", carSku.getSkuId());
            String result = callDataCenter(GOODS_SKU_PICTURE_QUERY, json);
            JSONArray array = JSONObject.parseArray(result);
            List list = new ArrayList();
            for (int i = 0; i < array.size(); i++) {
                JSONObject node = array.getJSONObject(i);
                if (StringUtil.isNotEmpty(node.getString("picture_url"))) {
                    list.add(node.getString("picture_url"));
                } else {
                    //无图用占位图表示
                    list.add(PICTURE_PLACEHOLDER_SKU.getId());
                }
            }
            carSku.setPictureList(list);
            if (StringUtil.isNotEmpty(carSku.getAttributes())) {
                json = new JSONObject();
                json.put("id", carSku.getAttributes());
                result = callDataCenter(GOODS_ATTR_VALUE_GET, json);
                if (StringUtil.isNotEmpty(result)) {
                    carSku.setAttrValue(JSONObject.parseObject(result).getString("attr_value"));
                }
            }
        }
        /**===========@date 2019-07-03==============购物车改造为一小时达和普通商品两种集合返回到前端。======start======*/
        Set<String> skuIds = items.keySet();
        JSONObject skuJson = new JSONObject();
        skuJson.put("skuIds", skuIds);
        String skuListStr = callDataCenter(GOODS_HOUR_QUERY_BY_SKUIDLIST, skuJson);
        List<Map> maps = JSONObject.parseArray(skuListStr, Map.class);
        Set<String> hourSkuIds = new HashSet<>(maps.size());
        for (Map map : maps) {
            String sku_id = map.get("sku_id").toString();
            hourSkuIds.add(sku_id);
        }
        Map<String, CarSku> averageItems = new HashMap<>(1);
        Map<String, CarSku> hourItems = new HashMap<>(1);
        for (String key : items.keySet()) {
            if (hourSkuIds.contains(key)) {
                hourItems.put(key, items.get(key));
            } else {
                averageItems.put(key, items.get(key));
            }
        }
        buyCar.setHourItems(hourItems);
        buyCar.setAverageItems(averageItems);
        /**===========@date 2019-07-03==============购物车改造为一小时达和普通商品两种集合返回到前端。======end======*/
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, buyCar);
    }


    @Override
    public ResponseJson syncBuyCar(String carSkuList) throws Exception {
        String buyerId = getLoginUserId();
        if (StringUtil.isEmpty(buyerId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        List<CarSku> list = JSONObject.parseArray(carSkuList, CarSku.class);
       /* //从redis中获取购物车
        BuyCar buyCar = getCarFromRedis(buyerId);
        if (buyCar != null) {
            buyCar = new BuyCar();
            buyCar.setBuyerId(buyerId);
        }*/

        Map<String, String> paramMap = null;
        for (CarSku carSku : list) {
            addCar(carSku.getSkuId(), carSku.getNum());
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    /**
     * 从redis里获得购物车信息
     *
     * @param buyerId
     * @return
     * @throws Exception
     */
    private BuyCar getCarFromRedis(String buyerId) throws Exception {
        BuyCar car = null;
        if (StringUtil.isEmpty(buyerId)) {
            return null;
        } else {
            String jsonObject = redisService.get(REDIS_ENTITY_CAR_ID, buyerId);
            car = JSONObject.parseObject(jsonObject, BuyCar.class);
        }
        return car;
    }


    /**
     * 将购物车写入redis
     *
     * @param buyCar
     * @throws Exception
     */
    private void writeCarToRedis(BuyCar buyCar) throws Exception {
        if (buyCar != null) {
            redisService.set(REDIS_ENTITY_CAR_ID, buyCar.getBuyerId(), JSON.toJSONString(buyCar));
        }
    }

    /**
     * 清除购物车
     *
     * @param buyCar
     * @throws Exception
     */
    public void clearCar(BuyCar buyCar) throws Exception {
        if (buyCar != null && StringUtil.isNotEmpty(buyCar.getBuyerId())) {
            redisService.del(REDIS_ENTITY_CAR_ID, buyCar.getBuyerId());
        }
    }

    private CarSku copyProperties(String num, JSONObject json) throws Exception {
        CarSku carSku = new CarSku();
        carSku.setSkuId(json.getString("id"));
        carSku.setNum(num);
        carSku.setSkuName(json.getString("name"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", json.getString("spu_id"));
        String result = callDataCenter(GOODS_SPU_QUERY, jsonObject);
        carSku.setSpuName(JSONObject.parseObject(result).getString("name"));
        carSku.setSinglePrice(json.getString("market_price"));
        carSku.setSpuId(json.getString("spu_id"));
        carSku.setAttributes(json.getString("attributes"));
        double singlePrice = StringUtil.isNumber(json.getString("market_price")) ? Double.parseDouble(json.getString("market_price")) : 0;
        double price = singlePrice * Integer.parseInt(num);
        carSku.setPrice(String.valueOf(price));
        return carSku;
    }

    private boolean checkParam(Map<String, String> map) {
//        if (StringUtil.isEmpty(map.get("buyerId"))) {
//            return false;
//        }
        if (StringUtil.isEmpty(map.get("skuId"))) {
            return false;
        }

        if (StringUtil.isEmpty(map.get("num")) || !StringUtil.isNumber(map.get("num"))) {
            return false;
        }
        return true;
    }


}
