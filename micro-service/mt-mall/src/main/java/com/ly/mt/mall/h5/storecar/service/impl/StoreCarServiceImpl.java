package com.ly.mt.mall.h5.storecar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.buycar.vo.BuyCar;
import com.ly.mt.mall.h5.buycar.vo.CarSku;
import com.ly.mt.mall.h5.storecar.service.StoreCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.redis.RedisKey.REDIS_ENTITY_STORE_ID;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * 收藏车Service
 */
@Service
public class StoreCarServiceImpl extends BaseServiceImpl implements StoreCarService {

    private final static Logger LOGGER = LoggerFactory.getLogger(StoreCarServiceImpl.class);

    @Override
    public ResponseJson addStoreCar(String skuId, String num) throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        //从redis中获取购物车
        BuyCar storeCar = getCarFromRedis(userId);
        if (storeCar == null) {
            storeCar = new BuyCar();
            storeCar.setBuyerId(userId);
        }
        JSONObject json = new JSONObject();
        json.put("id", skuId);
        String result = callDataCenter(GOODS_SKU_QUERY, json);
        CarSku carSku = copyProperties(num, JSONObject.parseObject(result));
        storeCar.addItems(carSku);

        //将新的购物车写回redis
        writeCarToRedis(storeCar);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @Override
    public ResponseJson subStoreCarSku(String skuId) throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }

        //从redis中获取收藏车//设置返回值
        BuyCar storeCar = getCarFromRedis(userId);
        if (storeCar == null) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        storeCar.subCar(skuId);

        //判断收藏车是否还有商品
        Map<String, CarSku> items = storeCar.getItems();
        if (!items.isEmpty()) {
            //将新的收藏车写回redis
            writeCarToRedis(storeCar);
        } else {
            clearCar(storeCar);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @Override
    public ResponseJson getStoreCar() throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }

        //从redis中获取收藏车
        BuyCar storeCar = getCarFromRedis(userId);
        if (storeCar == null || storeCar.getItems().isEmpty()) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        }
        Map<String, CarSku> items = storeCar.getItems();
        for (String key : items.keySet()) {
            CarSku carSku = items.get(key);
            JSONObject json = new JSONObject();
            json.put("sku_id", carSku.getSkuId());
            String result = callDataCenter(GOODS_SKU_PICTURE_QUERY, json);
            JSONArray array = JSONObject.parseArray(result);
            List list = new ArrayList();
            for (int i = 0; i < array.size(); i++) {
                JSONObject node = array.getJSONObject(i);
                if (StringUtil.isNotEmpty(node.getString("picture_url"))) {
                    list.add(node.getString("picture_url"));
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
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, storeCar);
    }


    public ResponseJson syncStoreCar(String carSkuList) throws Exception {
        String buyerId = getLoginUserId();
        if (StringUtil.isEmpty(buyerId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        List<CarSku> list = JSONObject.parseArray(carSkuList, CarSku.class);

        //从redis中获取购物车
        BuyCar buyCar = getCarFromRedis(buyerId);
        if (buyCar != null) {
            buyCar = new BuyCar();
            buyCar.setBuyerId(buyerId);
        }

        Map<String, String> paramMap = null;
        for (CarSku carSku : list) {
            addStoreCar(carSku.getSkuId(), carSku.getNum());
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    /**
     * 从redis里获得收藏车信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    private BuyCar getCarFromRedis(String userId) throws Exception {
        BuyCar car = null;
        if (StringUtil.isEmpty(userId)) {
            return null;
        } else {
            String jsonObject = redisService.get(REDIS_ENTITY_STORE_ID, userId);
            car = JSONObject.parseObject(jsonObject, BuyCar.class);
        }
        return car;
    }


    /**
     * 将收藏车写入redis
     *
     * @param storeCar
     * @throws Exception
     */
    private void writeCarToRedis(BuyCar storeCar) throws Exception {
        if (storeCar != null) {
            redisService.set(REDIS_ENTITY_STORE_ID, storeCar.getBuyerId(), JSON.toJSONString(storeCar));
        }
    }

    /**
     * 清除收藏车
     *
     * @param storeCar
     * @throws Exception
     */
    public void clearCar(BuyCar storeCar) throws Exception {
        if (storeCar != null && StringUtil.isNotEmpty(storeCar.getBuyerId())) {
            redisService.del(REDIS_ENTITY_STORE_ID, storeCar.getBuyerId());
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
        if (StringUtil.isEmpty(map.get("userId"))) {
            return false;
        }
        if (StringUtil.isEmpty(map.get("skuId"))) {
            return false;
        }
        if (StringUtil.isNotEmpty(map.get("num")) && !StringUtil.isNumber("num")) {
            return false;
        }
        return true;
    }


}
