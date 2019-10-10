package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.center.data.shop.mapper.ShopPurchasesMapper;
import com.ly.mt.center.data.shop.service.ShopPurchasesService;
import com.ly.mt.center.data.shop.entity.ShopPurchases;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.user.entity.UserAddress;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class ShopPurchasesServiceImpl extends BaseServiceImpl implements ShopPurchasesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopPurchasesServiceImpl.class);
    @Resource
    ShopPurchasesMapper mapper;

    /**
     * @Description 保存ShopPurchases
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopPurchases(JSONObject jsonObject) {
        try {
            ShopPurchases shopPurchases = JSONObject.toJavaObject(jsonObject, ShopPurchases.class);
            if (StringUtil.isEmpty(shopPurchases.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopPurchases(shopPurchases);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesServiceImpl.insertShopPurchases出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopPurchases
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopPurchases(JSONObject jsonObject) {
        try {
            ShopPurchases shopPurchases = JSONObject.toJavaObject(jsonObject, ShopPurchases.class);
            if (StringUtil.isEmpty(shopPurchases.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopPurchases(shopPurchases);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesServiceImpl.deleteShopPurchases出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopPurchases
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopPurchases(JSONObject jsonObject) {
        try {
            ShopPurchases shopPurchases = JSONObject.toJavaObject(jsonObject, ShopPurchases.class);
            if (StringUtil.isEmpty(shopPurchases.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopPurchases(shopPurchases);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesServiceImpl.updateShopPurchasesById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopPurchases
     * @Author taoye
     */
    @Override
    public ResponseJson getShopPurchases(JSONObject jsonObject) {
        try {
            ShopPurchases shopPurchases = JSONObject.toJavaObject(jsonObject, ShopPurchases.class);
            if (StringUtil.isEmpty(shopPurchases.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getShopPurchases(shopPurchases));
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesServiceImpl.getShopPurchases出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 查询ShopPurchasesList
     * @Author taoye
     */
    @Override
    public ResponseJson getShopPurchasesList(JSONObject jsonObject) {
        try {
            Map result = new HashMap();
            PageHelper.startPage(jsonObject.getInteger("page"), jsonObject.getInteger("rows"));
            ShopPurchases shopPurchases = new ShopPurchases();
            shopPurchases.setShop_id(jsonObject.getString("shop_id"));
            List<ShopPurchases> shopPurchasesList = mapper.getShopPurchasesList(shopPurchases);
            PageInfo pageInfo = new PageInfo(shopPurchasesList);
            result.put("total", pageInfo.getTotal());
            result.put("rows", shopPurchasesList);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopPurchasesList);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesServiceImpl.getShopPurchases出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}