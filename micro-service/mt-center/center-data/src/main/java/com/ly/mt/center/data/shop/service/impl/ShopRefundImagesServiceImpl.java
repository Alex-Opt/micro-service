package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopRefundImagesMapper;
import com.ly.mt.center.data.shop.service.ShopRefundImagesService;
import com.ly.mt.center.data.shop.entity.ShopRefundImages;
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
public class ShopRefundImagesServiceImpl extends BaseServiceImpl implements ShopRefundImagesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopRefundImagesServiceImpl.class);
    @Resource
    ShopRefundImagesMapper mapper;

    /**
     * @Description 保存ShopRefundImages
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopRefundImages(JSONObject jsonObject) {
        try {
            ShopRefundImages shopRefundImages = JSONObject.toJavaObject(jsonObject, ShopRefundImages.class);
            if (StringUtil.isEmpty(shopRefundImages.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopRefundImages(shopRefundImages);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopRefundImagesServiceImpl.insertShopRefundImages出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopRefundImages
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopRefundImages(JSONObject jsonObject) {
        try {
            ShopRefundImages shopRefundImages = JSONObject.toJavaObject(jsonObject, ShopRefundImages.class);
            if (StringUtil.isEmpty(shopRefundImages.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopRefundImages(shopRefundImages);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopRefundImagesServiceImpl.deleteShopRefundImages出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopRefundImages
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopRefundImages(JSONObject jsonObject) {
        try {
            ShopRefundImages shopRefundImages = JSONObject.toJavaObject(jsonObject, ShopRefundImages.class);
            if (StringUtil.isEmpty(shopRefundImages.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopRefundImages(shopRefundImages);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopRefundImagesServiceImpl.updateShopRefundImagesById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopRefundImages
     * @Author taoye
     */
    @Override
    public ResponseJson getShopRefundImages(JSONObject jsonObject) {
        try {
            ShopRefundImages shopRefundImages = JSONObject.toJavaObject(jsonObject, ShopRefundImages.class);
            if (StringUtil.isEmpty(shopRefundImages.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopRefundImages = mapper.getShopRefundImages(shopRefundImages);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopRefundImages);
        } catch (Exception e) {
            LOGGER.error("ShopRefundImagesServiceImpl.getShopRefundImages出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}