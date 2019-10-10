package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopPurchasesDiscountMapper;
import com.ly.mt.center.data.shop.service.ShopPurchasesDiscountService;
import com.ly.mt.center.data.shop.entity.ShopPurchasesDiscount;
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
public class ShopPurchasesDiscountServiceImpl extends BaseServiceImpl implements ShopPurchasesDiscountService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopPurchasesDiscountServiceImpl.class);
    @Resource
    ShopPurchasesDiscountMapper mapper;

    /**
     * @Description 保存ShopPurchasesDiscount
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopPurchasesDiscount(JSONObject jsonObject) {
        try {
            ShopPurchasesDiscount shopPurchasesDiscount = JSONObject.toJavaObject(jsonObject, ShopPurchasesDiscount.class);
            if (StringUtil.isEmpty(shopPurchasesDiscount.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopPurchasesDiscount(shopPurchasesDiscount);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesDiscountServiceImpl.insertShopPurchasesDiscount出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopPurchasesDiscount
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopPurchasesDiscount(JSONObject jsonObject) {
        try {
            ShopPurchasesDiscount shopPurchasesDiscount = JSONObject.toJavaObject(jsonObject, ShopPurchasesDiscount.class);
            if (StringUtil.isEmpty(shopPurchasesDiscount.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopPurchasesDiscount(shopPurchasesDiscount);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesDiscountServiceImpl.deleteShopPurchasesDiscount出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopPurchasesDiscount
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopPurchasesDiscount(JSONObject jsonObject) {
        try {
            ShopPurchasesDiscount shopPurchasesDiscount = JSONObject.toJavaObject(jsonObject, ShopPurchasesDiscount.class);
            if (StringUtil.isEmpty(shopPurchasesDiscount.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopPurchasesDiscount(shopPurchasesDiscount);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesDiscountServiceImpl.updateShopPurchasesDiscountById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopPurchasesDiscount
     * @Author taoye
     */
    @Override
    public ResponseJson getShopPurchasesDiscount(JSONObject jsonObject) {
        try {
            ShopPurchasesDiscount shopPurchasesDiscount = JSONObject.toJavaObject(jsonObject, ShopPurchasesDiscount.class);
            if (StringUtil.isEmpty(shopPurchasesDiscount.getId()) && StringUtil.isEmpty(shopPurchasesDiscount.getPurchases_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopPurchasesDiscount = mapper.getShopPurchasesDiscount(shopPurchasesDiscount);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopPurchasesDiscount);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesDiscountServiceImpl.getShopPurchasesDiscount出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 查询商家累计优惠
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson getShopTotalDiscount(JSONObject jsonObject) {
        try {
            ShopPurchasesDiscount shopPurchasesDiscount = JSONObject.toJavaObject(jsonObject, ShopPurchasesDiscount.class);
            if (StringUtil.isEmpty(shopPurchasesDiscount.getShop_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            String discount = mapper.getShopTotalDiscount(shopPurchasesDiscount);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, discount);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesDiscountServiceImpl.getShopPurchasesDiscount出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson getShopPurchasesDiscountList(JSONObject jsonObject) {
        try {
            ShopPurchasesDiscount shopPurchasesDiscount = JSONObject.toJavaObject(jsonObject, ShopPurchasesDiscount.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getShopPurchasesDiscountList(shopPurchasesDiscount));
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesDiscountServiceImpl.getShopPurchasesDiscount出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}