package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopPurchasesRefundItemMapper;
import com.ly.mt.center.data.shop.service.ShopPurchasesRefundItemService;
import com.ly.mt.center.data.shop.entity.ShopPurchasesRefundItem;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class ShopPurchasesRefundItemServiceImpl extends BaseServiceImpl implements ShopPurchasesRefundItemService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopPurchasesRefundItemServiceImpl.class);
    @Resource
    ShopPurchasesRefundItemMapper mapper;

    /**
     * @Description 保存ShopPurchasesRefundItem
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopPurchasesRefundItem(JSONObject jsonObject) {
        try {
            ShopPurchasesRefundItem shopPurchasesRefundItem = JSONObject.toJavaObject(jsonObject, ShopPurchasesRefundItem.class);
            if (StringUtil.isEmpty(shopPurchasesRefundItem.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopPurchasesRefundItem(shopPurchasesRefundItem);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesRefundItemServiceImpl.insertShopPurchasesRefundItem出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopPurchasesRefundItem
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopPurchasesRefundItem(JSONObject jsonObject) {
        try {
            ShopPurchasesRefundItem shopPurchasesRefundItem = JSONObject.toJavaObject(jsonObject, ShopPurchasesRefundItem.class);
            if (StringUtil.isEmpty(shopPurchasesRefundItem.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopPurchasesRefundItem(shopPurchasesRefundItem);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesRefundItemServiceImpl.deleteShopPurchasesRefundItem出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopPurchasesRefundItem
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopPurchasesRefundItem(JSONObject jsonObject) {
        try {
            ShopPurchasesRefundItem shopPurchasesRefundItem = JSONObject.toJavaObject(jsonObject, ShopPurchasesRefundItem.class);
            if (StringUtil.isEmpty(shopPurchasesRefundItem.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopPurchasesRefundItem(shopPurchasesRefundItem);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesRefundItemServiceImpl.updateShopPurchasesRefundItemById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopPurchasesRefundItem
     * @Author taoye
     */
    @Override
    public ResponseJson getShopPurchasesRefundItem(JSONObject jsonObject) {
        try {
            ShopPurchasesRefundItem shopPurchasesRefundItem = JSONObject.toJavaObject(jsonObject, ShopPurchasesRefundItem.class);
            if (StringUtil.isEmpty(shopPurchasesRefundItem.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopPurchasesRefundItem = mapper.getShopPurchasesRefundItem(shopPurchasesRefundItem);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopPurchasesRefundItem);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesRefundItemServiceImpl.getShopPurchasesRefundItem出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 查询ShopPurchasesRefundItem
     * @Author taoye
     */
    @Override
    public ResponseJson getShopRefundItemNum(JSONObject jsonObject) {
        try {
            ShopPurchasesRefundItem shopPurchasesRefundItem = JSONObject.toJavaObject(jsonObject, ShopPurchasesRefundItem.class);
            if (StringUtil.isEmpty(shopPurchasesRefundItem.getShop_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getRefundNum(shopPurchasesRefundItem));
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesRefundItemServiceImpl.getShopPurchasesRefundItem出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}