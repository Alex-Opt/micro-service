package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopDefaultMapper;
import com.ly.mt.center.data.shop.service.ShopDefaultService;
import com.ly.mt.center.data.shop.entity.ShopDefault;
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
public class ShopDefaultServiceImpl extends BaseServiceImpl implements ShopDefaultService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopDefaultServiceImpl.class);
    @Resource
    ShopDefaultMapper mapper;

    /**
     * @Description 保存ShopDefault
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopDefault(JSONObject jsonObject) {
        try {
            ShopDefault shopDefault = JSONObject.toJavaObject(jsonObject, ShopDefault.class);
            if (StringUtil.isEmpty(shopDefault.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopDefault(shopDefault);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopDefaultServiceImpl.insertShopDefault出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopDefault
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopDefault(JSONObject jsonObject) {
        try {
            ShopDefault shopDefault = JSONObject.toJavaObject(jsonObject, ShopDefault.class);
            if (StringUtil.isEmpty(shopDefault.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopDefault(shopDefault);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopDefaultServiceImpl.deleteShopDefault出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopDefault
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopDefault(JSONObject jsonObject) {
        try {
            ShopDefault shopDefault = JSONObject.toJavaObject(jsonObject, ShopDefault.class);
            if (StringUtil.isEmpty(shopDefault.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopDefault(shopDefault);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopDefaultServiceImpl.updateShopDefaultById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopDefault
     * @Author taoye
     */
    @Override
    public ResponseJson getShopDefault(JSONObject jsonObject) {
        try {
            ShopDefault shopDefault = JSONObject.toJavaObject(jsonObject, ShopDefault.class);
            if (StringUtil.isEmpty(shopDefault.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopDefault = mapper.getShopDefault(shopDefault);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopDefault);
        } catch (Exception e) {
            LOGGER.error("ShopDefaultServiceImpl.getShopDefault出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}