package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.shop.entity.ShopAddress;
import com.ly.mt.center.data.shop.mapper.ShopAddressMapper;
import com.ly.mt.center.data.shop.service.ShopAddressService;
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
public class ShopAddressServiceImpl extends BaseServiceImpl implements ShopAddressService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopAddressServiceImpl.class);
    @Resource
    ShopAddressMapper mapper;

    /**
     * @Description 插入ShopAddress
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopAddress(JSONObject jsonObject) {
        try {
            ShopAddress shopAddress = JSONObject.toJavaObject(jsonObject, ShopAddress.class);
            mapper.insertShopAddress(shopAddress);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopAddressServiceImpl.insertShopAddress出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除ShopAddress
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopAddressById(JSONObject jsonObject) {
        try {
            ShopAddress shopAddress = JSONObject.toJavaObject(jsonObject, ShopAddress.class);
            mapper.deleteShopAddressById(shopAddress);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopAddressServiceImpl.deleteShopAddressById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新ShopAddress
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopAddressById(JSONObject jsonObject) {
        try {
            ShopAddress shopAddress = JSONObject.toJavaObject(jsonObject, ShopAddress.class);
            mapper.updateShopAddressById(shopAddress);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopAddressServiceImpl.updateShopAddressById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询ShopAddress
     * @Author taoye
     */
    @Override
    public ResponseJson getShopAddress(JSONObject jsonObject) {
        try {
            ShopAddress shopAddress = JSONObject.toJavaObject(jsonObject, ShopAddress.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getShopAddress(shopAddress));
        } catch (Exception e) {
            LOGGER.error("ShopAddressServiceImpl.getShopAddress出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据shopId查询店铺的地址信息
     * @Author zhanglifeng
     */
    @Override
    public ResponseJson getShopDefaultAddressByShopId(JSONObject jsonObject){
        try {
            Long shopId = jsonObject.getLong("id");
            ShopAddress address = mapper.getShopDefaultAddressByShopId(shopId);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,address);
        } catch (Exception e) {
            LOGGER.error("ShopAddressServiceImpl.getShopDefaultAddressByShopId:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询ShopAddressList
     * @Author linan
     */
    @Override
    public ResponseJson getShopAddressList(JSONObject jsonObject) {
        try {
            ShopAddress shopAddress = JSONObject.toJavaObject(jsonObject, ShopAddress.class);
            if (StringUtil.isEmpty(shopAddress.getShop_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getShopAddressList(shopAddress));
        } catch (Exception e) {
            LOGGER.error("ShopAddressServiceImpl.getShopDefaultAddressByShopId:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

}