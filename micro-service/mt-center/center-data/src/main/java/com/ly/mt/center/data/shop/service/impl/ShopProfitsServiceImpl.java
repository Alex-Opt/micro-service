package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopProfitsMapper;
import com.ly.mt.center.data.shop.service.ShopProfitsService;
import com.ly.mt.center.data.shop.entity.ShopProfits;
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
public class ShopProfitsServiceImpl extends BaseServiceImpl implements ShopProfitsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopProfitsServiceImpl.class);
    @Resource
    ShopProfitsMapper mapper;

    /**
     * @Description 保存ShopProfits
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopProfits(JSONObject jsonObject) {
        try {
            ShopProfits shopProfits = JSONObject.toJavaObject(jsonObject, ShopProfits.class);
            if (StringUtil.isEmpty(shopProfits.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopProfits(shopProfits);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopProfitsServiceImpl.insertShopProfits出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopProfits
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopProfits(JSONObject jsonObject) {
        try {
            ShopProfits shopProfits = JSONObject.toJavaObject(jsonObject, ShopProfits.class);
            if (StringUtil.isEmpty(shopProfits.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopProfits(shopProfits);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopProfitsServiceImpl.deleteShopProfits出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopProfits
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopProfits(JSONObject jsonObject) {
        try {
            ShopProfits shopProfits = JSONObject.toJavaObject(jsonObject, ShopProfits.class);
            if (StringUtil.isEmpty(shopProfits.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopProfits(shopProfits);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopProfitsServiceImpl.updateShopProfitsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopProfits
     * @Author taoye
     */
    @Override
    public ResponseJson getShopProfits(JSONObject jsonObject) {
        try {
            ShopProfits shopProfits = JSONObject.toJavaObject(jsonObject, ShopProfits.class);
            if (StringUtil.isEmpty(shopProfits.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopProfits = mapper.getShopProfits(shopProfits);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopProfits);
        } catch (Exception e) {
            LOGGER.error("ShopProfitsServiceImpl.getShopProfits出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}