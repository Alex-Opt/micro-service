package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.center.data.user.entity.UserAddress;
import com.ly.mt.center.data.user.mapper.UserAddressMapper;
import com.ly.mt.center.data.user.service.UserAddressService;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
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
public class UserAddressServiceImpl extends BaseServiceImpl implements UserAddressService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserAddressServiceImpl.class);
    @Resource
    UserAddressMapper mapper;

    /**
     * @Description 插入UserAddress
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserAddress(JSONObject jsonObject) {
        try {
            UserAddress userAddress = JSONObject.toJavaObject(jsonObject, UserAddress.class);
            mapper.insertUserAddress(userAddress);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserAddressServiceImpl.insertUserAddress出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除UserAddress
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserAddressById(JSONObject jsonObject) {
        try {
            UserAddress userAddress = JSONObject.toJavaObject(jsonObject, UserAddress.class);
            mapper.deleteUserAddressById(userAddress);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserAddressServiceImpl.deleteUserAddressById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id更新UserAddress
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserAddressById(JSONObject jsonObject) {
        try {
            UserAddress userAddress = JSONObject.toJavaObject(jsonObject, UserAddress.class);
            mapper.updateUserAddressById(userAddress);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserAddressServiceImpl.updateUserAddressById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询UserAddress
     * @Author taoye
     */
    @Override
    public ResponseJson getUserAddress(JSONObject jsonObject) {
        try {
            UserAddress userAddress = JSONObject.toJavaObject(jsonObject, UserAddress.class);
            mapper.getUserAddress(userAddress);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserAddressServiceImpl.getUserAddress出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询UserAddress
     * @Author taoye
     */
    @Override
    public ResponseJson getUserAddressById(JSONObject jsonObject) {
        try {
            Long address_id = jsonObject.getLong("address_id");
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getUserAddressById(address_id));
        } catch (Exception e) {
            LOGGER.error("UserAddressServiceImpl.getUserAddressById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getDefaultUserAddressByUserId(JSONObject jsonObject) {
        try {
            UserAddress userAddress = JSONObject.toJavaObject(jsonObject, UserAddress.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getDefaultUserAddressByUserId(userAddress));
        } catch (Exception e) {
            LOGGER.error("UserAddressServiceImpl.getDefaultUserAddressByUserId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getUserAddressByUserId(JSONObject jsonObject) {
        try {
            Map result = new HashMap();
            PageHelper.startPage(jsonObject.getInteger("page"), jsonObject.getInteger("rows"));
            UserAddress info = new UserAddress();
            info.setUser_id(jsonObject.getString("user_id"));
            List<UserAddress> list = mapper.getUserAddressByUserId(info);
            PageInfo pageInfo = new PageInfo(list);
            result.put("total", pageInfo.getTotal());
            result.put("rows", list);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, result);
        } catch (Exception e) {
            LOGGER.error("UserAddressServiceImpl.getUserAddressByUserId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateUserAddress(JSONObject jsonObject) {
        try {
            UserAddress userAddress = JSONObject.toJavaObject(jsonObject, UserAddress.class);
            mapper.updateUserAddress(userAddress);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserAddressServiceImpl.updateUserAddress出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}