package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopPurachasesLadderPriceMapper;
import com.ly.mt.center.data.shop.service.ShopPurachasesLadderPriceService;
import com.ly.mt.center.data.shop.entity.ShopPurachasesLadderPrice;
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
public class ShopPurachasesLadderPriceServiceImpl extends BaseServiceImpl implements ShopPurachasesLadderPriceService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopPurachasesLadderPriceServiceImpl.class);
    @Resource
    ShopPurachasesLadderPriceMapper mapper;

    /**
     * @Description 保存ShopPurachasesLadderPrice
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopPurachasesLadderPrice(JSONObject jsonObject) {
        try {
            ShopPurachasesLadderPrice shopPurachasesLadderPrice = JSONObject.toJavaObject(jsonObject, ShopPurachasesLadderPrice.class);
            if (StringUtil.isEmpty(shopPurachasesLadderPrice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopPurachasesLadderPrice(shopPurachasesLadderPrice);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurachasesLadderPriceServiceImpl.insertShopPurachasesLadderPrice出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopPurachasesLadderPrice
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopPurachasesLadderPrice(JSONObject jsonObject) {
        try {
            ShopPurachasesLadderPrice shopPurachasesLadderPrice = JSONObject.toJavaObject(jsonObject, ShopPurachasesLadderPrice.class);
            if (StringUtil.isEmpty(shopPurachasesLadderPrice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopPurachasesLadderPrice(shopPurachasesLadderPrice);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurachasesLadderPriceServiceImpl.deleteShopPurachasesLadderPrice出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopPurachasesLadderPrice
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopPurachasesLadderPrice(JSONObject jsonObject) {
        try {
            ShopPurachasesLadderPrice shopPurachasesLadderPrice = JSONObject.toJavaObject(jsonObject, ShopPurachasesLadderPrice.class);
            if (StringUtil.isEmpty(shopPurachasesLadderPrice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopPurachasesLadderPrice(shopPurachasesLadderPrice);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurachasesLadderPriceServiceImpl.updateShopPurachasesLadderPriceById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopPurachasesLadderPrice
     * @Author taoye
     */
    @Override
    public ResponseJson getShopPurachasesLadderPrice(JSONObject jsonObject) {
        try {
            ShopPurachasesLadderPrice shopPurachasesLadderPrice = JSONObject.toJavaObject(jsonObject, ShopPurachasesLadderPrice.class);
            if (StringUtil.isEmpty(shopPurachasesLadderPrice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopPurachasesLadderPrice = mapper.getShopPurachasesLadderPrice(shopPurachasesLadderPrice);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopPurachasesLadderPrice);
        } catch (Exception e) {
            LOGGER.error("ShopPurachasesLadderPriceServiceImpl.getShopPurachasesLadderPrice出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 查询ShopPurachasesLadderPrice集合
     * @Author linan
     */
    @Override
    public ResponseJson getShopPurachasesLadderPriceList(JSONObject jsonObject) {
        try {
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getLadderList());
        } catch (Exception e) {
            LOGGER.error("ShopPurachasesLadderPriceServiceImpl.getLadderList出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }
}