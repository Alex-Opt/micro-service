package com.ly.mt.mall.h5.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.user.service.UserAddressService;
import com.ly.mt.mall.h5.user.vo.UserAddressVo;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_USER_ADDRESS;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_NOT_LOGIN;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * @author zhanglifeng
 * @description 用户中心-收货地址管理服务层
 * @date 2019-05-20
 */
@Service
public class UserAddressServiceImpl extends BaseServiceImpl implements UserAddressService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserAddressServiceImpl.class);

    /**
     * 地址新增要根据前端传的省市区名字，查询字典表，匹配出对应的code,
     *
     * @param jsonObject 入参
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson saveAddress(String jsonObject) throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        //先去库里查下该用户有没有地址，没有则新增的为第一条。需要设置为默认地址
        String resultDefaultAddress = callDataCenter(DEFAULT_USERADDRESS_BY_USERID, json);
        if (StringUtil.isEmpty(resultDefaultAddress)) {
            json.put("is_default", "1");
        } else {
            json.put("is_default", "0");
        }
        UserAddressVo userAddressVo = JSON.parseObject(jsonObject, UserAddressVo.class);
        json.put("receive_name", userAddressVo.getReceiveName());
        json.put("receive_phone", userAddressVo.getReceivePhone());
        json.put("province_code", userAddressVo.getProvinceCode());
        json.put("province_name", userAddressVo.getProvinceName());
        json.put("city_code", userAddressVo.getCityCode());
        json.put("city_name", userAddressVo.getCityName());
        json.put("district_code", userAddressVo.getDistrictCode());
        json.put("district_name", userAddressVo.getDistrictName());
        //json.put("picker_value", userAddressVo.getPickerValue());
        json.put("user_address", userAddressVo.getUserAddress());
        json.put("lat", userAddressVo.getLat());
        json.put("lon", userAddressVo.getLon());
        json.put("user_name", getLoginUserName());
        json.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER_ADDRESS));
        json.put("valid_status", "1");
        json.put("create_time", DateUtil.getNowTimeStr());
        callDataCenter(USERADDRESS_INSERT, json);

        json.put("flag", true);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
    }

    @Override
    public ResponseJson listAddress(int page, int rows) throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        json.put("page", page);
        json.put("rows", rows);
        String resultAddressList = callDataCenter(USERADDRESS_BY_USERID, json);
        JSONObject resultJson = JSONObject.parseObject(resultAddressList);
        JSONArray array = resultJson.getJSONArray("rows");
        Map resultMap = new HashMap();
        resultMap.put("total", resultJson.getString("total"));
        List list = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            Map map = new HashMap();
            map.put("id", node.getString("id"));
            map.put("userId", node.getString("user_id"));
            map.put("receiveName", node.getString("receive_name"));
            map.put("receivePhone", node.getString("receive_phone"));
            map.put("provinceCode", node.getString("province_code"));
            map.put("provinceName", node.getString("province_name"));
            map.put("cityCode", node.getString("city_code"));
            map.put("cityName", node.getString("city_name"));
            map.put("districtCode", node.getString("district_code"));
            map.put("districtName", node.getString("district_name"));
            map.put("pickerValue", node.getString("picker_value"));
            map.put("userAddress", node.getString("user_address"));
            map.put("isDefault", node.getString("is_default"));
            map.put("lon", node.getString("lon"));
            map.put("lat", node.getString("lat"));
            list.add(map);
        }
        resultMap.put("rows", list);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultMap);
    }

    @Override
    @TxcTransaction(appName = "moti")
    public ResponseJson defaultAddress(String id) throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        String xid = TxcContext.getCurrentXid();
        JSONObject json = new JSONObject();
        json.put("xid", xid);
        json.put("user_id", userId);
        json.put("is_default", "0");
        json.put("modify_time", DateUtil.getNowTimeStr());
        callDataCenter(USERADDRESS_UPDATE, json);
        json.put("is_default", "1");
        json.put("id", id);
        //再更新具体某个id对应的地址为默认地址。
        callDataCenter(USERADDRESS_UPDATE, json);
        json.put("flag", true);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
    }

    /**
     * 修改地址的逻辑更新：原来的地址变为无效，修改的地址重新save为一条数据。这样做的目的是为了保存历史地址，保证历史订单中的
     * 收货地址信息不会跟着变化。
     *
     * @param jsonObject 入参
     * @return
     * @throws Exception
     */
    @Override
    @TxcTransaction(appName = "moti")
    public ResponseJson updateAddress(String jsonObject) throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        JSONObject json = new JSONObject();
        UserAddressVo vo = JSON.parseObject(jsonObject, UserAddressVo.class);
        //首先更新旧的地址为无效状态。
        json.put("id", vo.getId());
        json.put("valid_status", "2");
        callDataCenter(USERADDRESS_UPDATE, json);
        //保存新的地址信息
        json.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER_ADDRESS));
        json.put("user_id", userId);
        json.put("user_name", vo.getUserName());
        json.put("receive_name", vo.getReceiveName());
        json.put("receive_phone", vo.getReceivePhone());
        json.put("province_code", vo.getProvinceCode());
        json.put("province_name", vo.getProvinceName());
        json.put("city_code", vo.getCityCode());
        json.put("city_name", vo.getCityName());
        json.put("district_code", vo.getDistrictCode());
        json.put("district_name", vo.getDistrictName());
        json.put("lat", vo.getLat());
        json.put("lon", vo.getLon());
        json.put("is_default", vo.getIsDefault());
        json.put("valid_status", "1");
        json.put("user_address", vo.getUserAddress());
        json.put("create_time", DateUtil.getNowTimeStr());
        callDataCenter(USERADDRESS_INSERT, json);
        json.put("flag", true);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
    }

    @Override
    public ResponseJson deleteAddress(String id) throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("valid_status", "2");
        //既然已经删除，就算是默认的地址，也要更新为非默认地址。
        json.put("is_default", "0");
        json.put("modify_time", DateUtil.getNowTimeStr());
        callDataCenter(USERADDRESS_UPDATE, json);
        json.put("flag", true);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
    }

    @Override
    public ResponseJson getDefaultAddressByUserId() throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        String resultDefaultAddress = callDataCenter(DEFAULT_USERADDRESS_BY_USERID, json);
        if (StringUtil.isNotEmpty(resultDefaultAddress)) {
            JSONObject defaultAddressJson = JSONObject.parseObject(resultDefaultAddress);
            json.put("id", defaultAddressJson.getString("id"));
            json.put("userId", defaultAddressJson.getString("user_id"));
            json.put("receiveName", defaultAddressJson.getString("receive_name"));
            json.put("receivePhone", defaultAddressJson.getString("receive_phone"));
            json.put("provinceCode", defaultAddressJson.getString("province_code"));
            json.put("provinceName", defaultAddressJson.getString("province_name"));
            json.put("cityCode", defaultAddressJson.getString("city_code"));
            json.put("cityName", defaultAddressJson.getString("city_name"));
            json.put("districtCode", defaultAddressJson.getString("district_code"));
            json.put("districtName", defaultAddressJson.getString("district_name"));
            json.put("pickerValue", defaultAddressJson.getString("picker_value"));
            json.put("userAddress", defaultAddressJson.getString("user_address"));
            json.put("isDefault", defaultAddressJson.getString("is_default"));
            json.put("lon", defaultAddressJson.getString("lon"));
            json.put("lat", defaultAddressJson.getString("lat"));
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
    }

    @Override
    public ResponseJson getAddressById(String id) throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        JSONObject json = new JSONObject();
        json.put("address_id", Long.parseLong(id));
        String resultAddress = callDataCenter(USERADDRESS_BY_ID, json);
        if (StringUtil.isNotEmpty(resultAddress)) {
            JSONObject addressJson = JSONObject.parseObject(resultAddress);
            json.put("id", addressJson.getString("id"));
            json.put("userId", addressJson.getString("user_id"));
            json.put("receiveName", addressJson.getString("receive_name"));
            json.put("receivePhone", addressJson.getString("receive_phone"));
            json.put("provinceCode", addressJson.getString("province_code"));
            json.put("provinceName", addressJson.getString("province_name"));
            json.put("cityCode", addressJson.getString("city_code"));
            json.put("cityName", addressJson.getString("city_name"));
            json.put("districtCode", addressJson.getString("district_code"));
            json.put("districtName", addressJson.getString("district_name"));
            json.put("pickerValue", addressJson.getString("picker_value"));
            json.put("userAddress", addressJson.getString("user_address"));
            json.put("isDefault", addressJson.getString("is_default"));
            json.put("lon", addressJson.getString("lon"));
            json.put("lat", addressJson.getString("lat"));
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
    }
}
