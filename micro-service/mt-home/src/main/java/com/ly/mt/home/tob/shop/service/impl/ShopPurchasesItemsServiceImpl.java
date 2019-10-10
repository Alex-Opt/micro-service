package com.ly.mt.home.tob.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.dict.PicturePlaceholder;
import com.ly.mt.core.base.dict.RefundReason;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.base.util.JsonUtil;
import com.ly.mt.home.tob.shop.service.ShopPurchasesItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ShopPurchasesItemsServiceImpl extends BaseServiceImpl implements ShopPurchasesItemsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopPurchasesItemsServiceImpl.class);

    @Resource
    private JsonUtil<JSONObject> jsonUtil;

    @Override
    public ResponseJson getItemList(JSONObject parameter) {
        Map<String, Object> dataResult = new HashMap<>();
        long shopPurachasesId = parameter.getLongValue("shopPurachasesId");
        LOGGER.info("refund method of RefundServiceImpl params is {}", shopPurachasesId);
        long start = System.currentTimeMillis();
        //获取进货订单列表
        JSONObject params = new JSONObject();
        params.put("shopPurchaseId", shopPurachasesId);
        try {
            String callBackData = callDataCenter(DataCenterMethod.SHOP_PURCHASES_ITEMS_LIST_GET, params);
            String humpCallBackData = jsonUtil.underling2Hump(callBackData);
            List<Map<String, Object>> skuList = new ArrayList<>();
            List<Map<String, Object>> mapResult = JSON.parseObject(humpCallBackData, new TypeReference<List<Map<String, Object>>>() {
            });
            mapResult.forEach(map -> {
                Map<String, Object> m = new ConcurrentHashMap<>();
                String spuName = (String) map.get("name");
                String skuName = (String) map.get("skuName");
                double marketPrice = (Double) map.get("marketPrice");
                int skuNum = (Integer) map.get("skuNum");
                double refundFee = (Double) map.get("paymentPrice");
                m.put("goodsName", spuName + " " + skuName);
                m.put("acturlPrice", (marketPrice * skuNum));
                m.put("skuNum", skuNum);
                m.put("skuPic", (PicturePlaceholder.PICTURE_PLACEHOLDER_SPU_DETAIL.getName()));
                m.put("refundFee", refundFee);
                skuList.add(m);
            });
            dataResult.put("skuList", skuList);
            dataResult.put("refundResons", refundReson());

            long end = System.currentTimeMillis();
            LOGGER.info("refund method of RefundServiceImpl cost {} mills", (end - start));
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, humpCallBackData);
        } catch (Exception e) {
            LOGGER.error("call method getItemList of ShopPurchasesItemsServiceImpl has error , the error message is {}", e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    private List<String> refundReson() {
        List<String> resons = new ArrayList<>();
        Arrays.asList(RefundReason.values()).forEach(reson -> {
            resons.add(reson.getName());
        });
        return resons;
    }
}
