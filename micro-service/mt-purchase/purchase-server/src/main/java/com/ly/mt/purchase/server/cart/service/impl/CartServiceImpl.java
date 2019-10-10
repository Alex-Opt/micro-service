package com.ly.mt.purchase.server.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.common.entity.buycar.BuyCar;
import com.ly.mt.core.common.entity.buycar.BuyCarGoodsSkuInfo;
import com.ly.mt.core.common.entity.buycar.CarSku;
import com.ly.mt.core.common.entity.purchase.Cart;
import com.ly.mt.core.common.entity.purchase.CartGoodsSkuInfo;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.purchase.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.purchase.server.cart.mapper.CartServiceMapper;
import com.ly.mt.purchase.server.cart.service.CartService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.common.constant.RedisEnum.ENTITY_CAR_BUYER_ID_REDIS;
import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;

public class CartServiceImpl extends BaseServiceImpl implements CartService {

    @Resource
    private CartServiceMapper cartServiceMapper;
    @Override
    public JSONObject addCart(String json) throws Exception {
        //从json中获取参数值
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        if (!checkParam(map)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        //String buyerId = getLoginUserId();
        String buyerId = map.get("userId");
        String skuId = map.get("skuId");
        String num = map.get("num");

        //从redis中获取购物车
        Cart cart = getCarFromRedis(buyerId);
        if (null == cart) {
            cart = new Cart();
            cart.setBuyerId(buyerId);
        }
        Long sId = Long.parseLong(skuId);
        CartGoodsSkuInfo skuInfo = cartServiceMapper.selectByPrimaryKey(sId);
        CarSku carSku = copyProperties(num, skuInfo);
        cart.addItems(carSku);

        //将新的购物车写回redis
        writeCarToRedis(cart);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("buyCarSkuNum",cart.getItems().size());
        return JsonUtil.getSuccessJson(jsonObject);
    }

    @Override
    public JSONObject subCartSkuNum(String json) throws Exception {
        //从json中获取参数值
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        if (!checkParam(map)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        String buyerId = getLoginUserId();
        String skuId = map.get("skuId");
        String num = map.get("num");

        //从redis中获取购物车
        Cart cart = getCarFromRedis(buyerId);
        if (null == cart) {
            return JsonUtil.getSuccessJson();
        }

        //购物车中删除sku的数量
        //如果删除后的数量为0，则将该sku移除出购物车
        //buyCar.subCarNum(skuId, Integer.parseInt(num));
        cart.updateCarNum(skuId, Integer.parseInt(num));


        //判断购物车是否还有商品，
        //如果有，写入redis
        //如果没有，则将其从redis中清除
        Map<String, CarSku> items = cart.getItems();
        if (!items.isEmpty()) {
            //将新的购物车写回redis
            writeCarToRedis(cart);
        } else {
            //清除购物车
            clearCar(cart);
        }
        return JsonUtil.getSuccessJson();
    }

    @Override
    public JSONObject delCartSku(String json) throws Exception {
        //从json中获取参数值
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
      /*  if (!checkParam(map)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }*/
        //String buyerId = getLoginUserId();
        String buyerId = map.get("userId");
        String skuId = map.get("skuId");

        //从redis中获取购物车//设置返回值
        Cart cart = getCarFromRedis(buyerId);
        if (null == cart) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        String[] array = skuId.split(",");
        for (String id : array) {
            cart.subCar(id);
        }
        //判断购物车是否还有商品
        Map<String, CarSku> items = cart.getItems();
        if (!items.isEmpty()) {
            //将新的购物车写回redis
            writeCarToRedis(cart);
        } else {
            //JsonUtil.getErrorJson(RESULT_CODE_BUYCAR_NULL_ERROR);
            //清除购物车
            clearCar(cart);
        }
        return JsonUtil.getSuccessJson();
    }

    @Override
    public JSONObject getPurchaseCart(String json) throws Exception {
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        String buyerId = map.get("buyerId");

        //从redis中获取购物车
        Cart cart = getCarFromRedis(buyerId);
        if (null == cart || cart.getItems().isEmpty()) {
            return JsonUtil.getSuccessJson(null);
        }
        Map<String, CarSku> items = cart.getItems();
        for (String key : items.keySet()) {
            CarSku carSku = items.get(key);
            carSku.setPictureList(cartServiceMapper.queryGoodsSkuPictureBySkuId(Long.valueOf(carSku.getSkuId())));
            if (StringUtil.isNotEmpty(carSku.getAttributes())) {
                carSku.setAttrValueList(cartServiceMapper.queryAttrValueById(carSku.getAttributes().split(":")));
            }
        }
        return JsonUtil.getSuccessJson(cart);
    }


    @Override
    public JSONObject syncCart(String json) throws Exception {
        Map<String, Object> map = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
        });
        String userId = (String) map.get("userId");
        List<CarSku> list = JSONObject.parseArray((String) map.get("skuIds"), CarSku.class);

        Map<String, String> paramMap = null;
        for (CarSku carSku : list) {
            paramMap = new HashMap<>();
            paramMap.put("userId", userId);
            paramMap.put("skuId", carSku.getSkuId());
            paramMap.put("num", carSku.getNum());
            addCart(JSONObject.toJSONString(paramMap));
        }
        return JsonUtil.getSuccessJson();
    }

    /**
     * 从redis里获得购物车信息
     *
     * @param buyerId
     * @return
     * @throws Exception
     */
    private Cart getCarFromRedis(String buyerId) throws Exception {
        Cart cart = null;
        if (StringUtil.isEmpty(buyerId)) {
            return null;
        } else {
            String jsonObject = redisServer.get(ENTITY_CAR_BUYER_ID_REDIS, buyerId);
            cart = JSONObject.parseObject(jsonObject, Cart.class);
        }
        return cart;
    }


    /**
     * 将购物车写入redis
     *
     * @param cart
     * @throws Exception
     */
    private void writeCarToRedis(Cart cart) throws Exception {
        if (null != cart) {
            redisServer.set(ENTITY_CAR_BUYER_ID_REDIS, cart.getBuyerId(), JSON.toJSONString(cart));
        }
    }

    /**
     * 清除购物车
     *
     * @param cart
     * @throws Exception
     */
    public void clearCar(Cart cart) throws Exception {
        if (null != cart && StringUtil.isNotEmpty(cart.getBuyerId())) {
            redisServer.del(ENTITY_CAR_BUYER_ID_REDIS, cart.getBuyerId());
        }
    }

    private CarSku copyProperties(String num, CartGoodsSkuInfo skuInfo) throws Exception {
        CarSku carSku = new CarSku();
        carSku.setSkuId(skuInfo.getId());
        carSku.setNum(num);
        carSku.setSkuName(skuInfo.getName());
        carSku.setSpuName(cartServiceMapper.selectSpuInfoById(Long.valueOf(skuInfo.getSpuId())).getName());
        carSku.setSinglePrice(skuInfo.getMarketPrice());
        carSku.setSpuId(skuInfo.getSpuId());
        carSku.setAttributes(skuInfo.getAttributes());
        double singlePrice = StringUtil.isNumber(skuInfo.getMarketPrice()) ? Double.parseDouble(skuInfo.getMarketPrice()) : 0;
        double price = singlePrice * Integer.parseInt(num);
        carSku.setPrice(String.valueOf(price));
        return carSku;
    }

    private boolean checkParam(Map<String, String> map) {
        if (StringUtil.isEmpty(map.get("skuId"))) {
            return false;
        }

        if (StringUtil.isEmpty(map.get("num")) || !StringUtil.isNumber(map.get("num"))) {
            return false;
        }
        return true;
    }
}
