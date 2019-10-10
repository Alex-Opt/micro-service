package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.shop.entity.ShopInfo;
import com.ly.mt.center.data.shop.mapper.ShopInfoMapper;
import com.ly.mt.center.data.shop.service.ShopInfoService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class ShopInfoServiceImpl extends BaseServiceImpl implements ShopInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopInfoServiceImpl.class);
    @Resource
    ShopInfoMapper mapper;

    /**
     * @Description 插入ShopInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopInfo(JSONObject jsonObject) {
        try {
            ShopInfo shopInfo = JSONObject.toJavaObject(jsonObject, ShopInfo.class);
            LOGGER.info("shopinfo: {}",shopInfo.toString());
            mapper.insertShopInfo(shopInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopInfoServiceImpl.insertShopInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除ShopInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopInfoById(JSONObject jsonObject) {
        try {
            ShopInfo shopInfo = JSONObject.toJavaObject(jsonObject, ShopInfo.class);
            mapper.deleteShopInfoById(shopInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopInfoServiceImpl.deleteShopInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新ShopInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopInfoById(JSONObject jsonObject) {
        try {
            ShopInfo shopInfo = JSONObject.toJavaObject(jsonObject, ShopInfo.class);
            mapper.updateShopInfoById(shopInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopInfoServiceImpl.updateShopInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询ShopInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getShopInfo(JSONObject jsonObject) {
        try {
            ShopInfo shopInfo = JSONObject.toJavaObject(jsonObject, ShopInfo.class);
            mapper.getShopInfo(shopInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopInfoServiceImpl.getShopInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询ShopInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getShopInfoById(JSONObject jsonObject) {
        try {
            ShopInfo search = new ShopInfo();
            search.setId(String.valueOf(jsonObject.getLong("id")));
            ShopInfo shopInfo = mapper.getShopInfo(search);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,shopInfo);
        } catch (Exception e) {
            LOGGER.error("ShopInfoServiceImpl.getShopInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getShopInfoByMobile(JSONObject jsonObject) {
        try {
            ShopInfo search = new ShopInfo();
            search.setMobile(jsonObject.getString("mobile"));
            ShopInfo shopInfo = mapper.getShopInfo(search);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,shopInfo);
        } catch (Exception e) {
            LOGGER.error("ShopInfoServiceImpl.getShopInfoByMobile出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getShopInfoByUserId(JSONObject jsonObject) {
        try {
            ShopInfo search = new ShopInfo();
            search.setUser_id(jsonObject.getString("user_id"));
            ShopInfo shopInfo = mapper.getShopInfo(search);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,shopInfo);
        } catch (Exception e) {
            LOGGER.error("ShopInfoServiceImpl.getShopInfoByMobile出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}