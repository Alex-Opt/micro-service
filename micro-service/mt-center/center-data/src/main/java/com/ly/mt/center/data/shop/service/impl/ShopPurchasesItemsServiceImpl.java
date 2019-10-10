package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.shop.entity.ShopPurchasesItems;
import com.ly.mt.center.data.shop.mapper.ShopPurchasesItemsMapper;
import com.ly.mt.center.data.shop.service.ShopPurchasesItemsService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class ShopPurchasesItemsServiceImpl extends BaseServiceImpl implements ShopPurchasesItemsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopPurchasesItemsServiceImpl.class);
    @Resource
    ShopPurchasesItemsMapper mapper;

    /**
     * @Description 保存ShopPurchasesItems
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopPurchasesItems(JSONObject jsonObject) {
        try {
            ShopPurchasesItems shopPurchasesItems = JSONObject.toJavaObject(jsonObject, ShopPurchasesItems.class);
            if (StringUtil.isEmpty(shopPurchasesItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopPurchasesItems(shopPurchasesItems);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesItemsServiceImpl.insertShopPurchasesItems出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopPurchasesItems
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopPurchasesItems(JSONObject jsonObject) {
        try {
            ShopPurchasesItems shopPurchasesItems = JSONObject.toJavaObject(jsonObject, ShopPurchasesItems.class);
            if (StringUtil.isEmpty(shopPurchasesItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopPurchasesItems(shopPurchasesItems);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesItemsServiceImpl.deleteShopPurchasesItems出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopPurchasesItems
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopPurchasesItems(JSONObject jsonObject) {
        try {
            ShopPurchasesItems shopPurchasesItems = JSONObject.toJavaObject(jsonObject, ShopPurchasesItems.class);
            if (StringUtil.isEmpty(shopPurchasesItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopPurchasesItems(shopPurchasesItems);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesItemsServiceImpl.updateShopPurchasesItemsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopPurchasesItems
     * @Author taoye
     */
    @Override
    public ResponseJson getShopPurchasesItems(JSONObject jsonObject) {
        try {
            ShopPurchasesItems shopPurchasesItems = JSONObject.toJavaObject(jsonObject, ShopPurchasesItems.class);
            if (StringUtil.isEmpty(shopPurchasesItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopPurchasesItems = mapper.getShopPurchasesItems(shopPurchasesItems);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopPurchasesItems);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesItemsServiceImpl.getShopPurchasesItems出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getItemList(JSONObject jsonParameter) {
        LOGGER.info("getItemList jsonParameter={}",jsonParameter);
        try {
            long start = System.currentTimeMillis();
            //业务代码
            long shopPurchaseId = jsonParameter.getLongValue("shopPurchaseId");
            List<Map<String,Object>> shopPurchasesItemsList = mapper.getItemList(shopPurchaseId);
            long end = System.currentTimeMillis();
            LOGGER.info("call getItemList method of ShopPurchasesItemsServiceImpl cost {} mills",(end - start));
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,shopPurchasesItemsList);
        }catch (Exception e){
            LOGGER.error("ShopPurchasesItemsServiceImpl.getItemList error,the error message is {}",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getShopItemNumByShopId(JSONObject jsonObject) {
        try {
            ShopPurchasesItems shopPurchasesItems = JSONObject.toJavaObject(jsonObject, ShopPurchasesItems.class);
            if (StringUtil.isEmpty(shopPurchasesItems.getShop_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getItemsNum(shopPurchasesItems));
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesItemsServiceImpl.getShopItemNumByShopId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getShopItemNumBySpu(JSONObject jsonObject) {
        try {
            ShopPurchasesItems shopPurchasesItems = JSONObject.toJavaObject(jsonObject, ShopPurchasesItems.class);
            if (StringUtil.isEmpty(shopPurchasesItems.getSpu_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getSpuNum(shopPurchasesItems));
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesItemsServiceImpl.getShopItemNumBySpu出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}