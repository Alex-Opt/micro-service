package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopGrabBlackListMapper;
import com.ly.mt.center.data.shop.service.ShopGrabBlackListService;
import com.ly.mt.center.data.shop.entity.ShopGrabBlackList;
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
public class ShopGrabBlackListServiceImpl extends BaseServiceImpl implements ShopGrabBlackListService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopGrabBlackListServiceImpl.class);
    @Resource
    ShopGrabBlackListMapper mapper;

    /**
     * @Description 保存ShopGrabBlackList
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopGrabBlackList(JSONObject jsonObject) {
        try {
            ShopGrabBlackList shopGrabBlackList = JSONObject.toJavaObject(jsonObject, ShopGrabBlackList.class);
            if (StringUtil.isEmpty(shopGrabBlackList.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopGrabBlackList(shopGrabBlackList);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopGrabBlackListServiceImpl.insertShopGrabBlackList出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopGrabBlackList
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopGrabBlackList(JSONObject jsonObject) {
        try {
            ShopGrabBlackList shopGrabBlackList = JSONObject.toJavaObject(jsonObject, ShopGrabBlackList.class);
            if (StringUtil.isEmpty(shopGrabBlackList.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopGrabBlackList(shopGrabBlackList);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopGrabBlackListServiceImpl.deleteShopGrabBlackList出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopGrabBlackList
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopGrabBlackList(JSONObject jsonObject) {
        try {
            ShopGrabBlackList shopGrabBlackList = JSONObject.toJavaObject(jsonObject, ShopGrabBlackList.class);
            if (StringUtil.isEmpty(shopGrabBlackList.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopGrabBlackList(shopGrabBlackList);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopGrabBlackListServiceImpl.updateShopGrabBlackListById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopGrabBlackList
     * @Author taoye
     */
    @Override
    public ResponseJson getShopGrabBlackList(JSONObject jsonObject) {
        try {
            ShopGrabBlackList shopGrabBlackList = JSONObject.toJavaObject(jsonObject, ShopGrabBlackList.class);
            if (StringUtil.isEmpty(shopGrabBlackList.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopGrabBlackList = mapper.getShopGrabBlackList(shopGrabBlackList);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopGrabBlackList);
        } catch (Exception e) {
            LOGGER.error("ShopGrabBlackListServiceImpl.getShopGrabBlackList出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}