package com.ly.mt.home.tob.buycar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.buycar.service.BuyCarService;
import com.ly.mt.home.tob.buycar.vo.BuyCar;
import com.ly.mt.home.tob.buycar.vo.CarSku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.dict.PicturePlaceholder.PICTURE_PLACEHOLDER_SKU;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;


/**
 * 购物车实现类
 *
 * @author linan
 * @date 20190709
 */
@Service
public class BuyCarServiceImpl extends BaseServiceImpl implements BuyCarService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ResponseJson addCar(List<CarSku> list) {
        String shopId = getLoginShopId();

        BuyCar buyCar = getCarFromRedis(shopId);
        buyCar = (buyCar == null ? new BuyCar(shopId) : buyCar);

        JSONObject json = new JSONObject();
        for (CarSku carSku : list) {
            json.put("id", carSku.getSkuId());
            String goodsSkuInfo = callDataCenter(GOODS_SKU_QUERY, json);
            carSku = copyProperties(carSku.getNum(), JSONObject.parseObject(goodsSkuInfo));
            buyCar.addItems(carSku);
        }

        //将新的购物车写回redis
        writeCarToRedis(buyCar);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("buyCarSkuNum", buyCar.getItems().size());
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonObject);
    }

    @Override
    public ResponseJson subCarSkuNum(String skuId, String num) {
        String shopId = getLoginShopId();

        //从redis中获取购物车
        BuyCar buyCar = getCarFromRedis(shopId);
        if (buyCar == null) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        }

        //购物车中删除sku的数量
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
    public ResponseJson subCarSku(String skuId) {
        String shopId = getLoginShopId();

        //从redis中获取购物车//设置返回值
        BuyCar buyCar = getCarFromRedis(shopId);
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
            //清除购物车
            clearCar(buyCar);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @Override
    public ResponseJson getBuyCar() {
        String shopId = getLoginShopId();

        // 从redis中获取购物车
        BuyCar buyCar = getCarFromRedis(shopId);
        if (buyCar == null || buyCar.getItems().isEmpty()) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        }

        // buycar sku size
        buyCar.setBuyCarSkuCategoryNum(String.valueOf(buyCar.getItems().size()));

        // buycar items
        Map<String, CarSku> items = buyCar.getItems();
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
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, buyCar);
    }

    @Override
    public BuyCar getBuyCarInfo() {
        return getCarFromRedis(getLoginShopId());
    }

    /**
     * 从redis里获得购物车信息
     *
     * @param shopId
     * @return
     */
    private BuyCar getCarFromRedis(String shopId) {
        String jsonObject = redisService.get(RedisKey.REDIS_ENTITY_SHOP_CAR_ID, shopId);
        BuyCar car = JSONObject.parseObject(jsonObject, BuyCar.class);
        return car;
    }


    /**
     * 将购物车写入redis
     *
     * @param buyCar
     */
    private void writeCarToRedis(BuyCar buyCar) {
        if (buyCar != null) {
            redisService.set(RedisKey.REDIS_ENTITY_SHOP_CAR_ID, buyCar.getShopId(), JSON.toJSONString(buyCar));
        }
    }

    /**
     * 清除购物车
     *
     * @param buyCar
     * @throws Exception
     */
    public void clearCar(BuyCar buyCar) {
        if (buyCar != null && StringUtil.isNotEmpty(buyCar.getShopId())) {
            redisService.del(RedisKey.REDIS_ENTITY_SHOP_CAR_ID, buyCar.getShopId());
        }
    }

    private CarSku copyProperties(String num, JSONObject json) {
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

}
