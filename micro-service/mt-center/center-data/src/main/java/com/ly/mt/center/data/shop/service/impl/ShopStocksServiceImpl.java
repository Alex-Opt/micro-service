package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopStocksMapper;
import com.ly.mt.center.data.shop.service.ShopStocksService;
import com.ly.mt.center.data.shop.entity.ShopStocks;
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
public class ShopStocksServiceImpl extends BaseServiceImpl implements ShopStocksService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopStocksServiceImpl.class);
    @Resource
    ShopStocksMapper mapper;

    /**
     * @Description 保存ShopStocks
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopStocks(JSONObject jsonObject) {
        try {
            ShopStocks shopStocks = JSONObject.toJavaObject(jsonObject, ShopStocks.class);
            if (StringUtil.isEmpty(shopStocks.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopStocks(shopStocks);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopStocksServiceImpl.insertShopStocks出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopStocks
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopStocks(JSONObject jsonObject) {
        try {
            ShopStocks shopStocks = JSONObject.toJavaObject(jsonObject, ShopStocks.class);
            if (StringUtil.isEmpty(shopStocks.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopStocks(shopStocks);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopStocksServiceImpl.deleteShopStocks出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopStocks
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopStocks(JSONObject jsonObject) {
        try {
            ShopStocks shopStocks = JSONObject.toJavaObject(jsonObject, ShopStocks.class);
            if (StringUtil.isEmpty(shopStocks.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopStocks(shopStocks);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopStocksServiceImpl.updateShopStocksById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopStocks
     * @Author taoye
     */
    @Override
    public ResponseJson getShopStocks(JSONObject jsonObject) {
        try {
            ShopStocks shopStocks = JSONObject.toJavaObject(jsonObject, ShopStocks.class);
            if (StringUtil.isEmpty(shopStocks.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopStocks = mapper.getShopStocks(shopStocks);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopStocks);
        } catch (Exception e) {
            LOGGER.error("ShopStocksServiceImpl.getShopStocks出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}