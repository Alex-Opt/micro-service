package com.ly.mt.home.tob.address.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.home.base.constant.ShopConstant;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.address.service.ShopAddressService;
import com.ly.mt.home.tob.address.vo.ShopAddressVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 商家收货地址实现类
 *
 * @author linan
 * @date 2019/7/17
 */
@Service
public class ShopAddressServiceServiceImpl extends BaseServiceImpl implements ShopAddressService {

    @Override
    public ShopAddressVo getShopAddressById(String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("shop_id", getLoginShopId());
        return JSONObject.parseObject(callDataCenter(DataCenterMethod.SHOP_ADDRESS_GET, jsonObject), ShopAddressVo.class);
    }

    @Override
    public List<ShopAddressVo> getShopAddressList() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shop_id", getLoginShopId());
        String addressStr = callDataCenter(DataCenterMethod.SHOP_ADDRESS_LIST_GET, jsonObject);
        List<ShopAddressVo> list = JSONObject.parseArray(addressStr, ShopAddressVo.class);
        return list;
    }

    @Override
    public ShopAddressVo getDefaultShopAddress() {
        String shopId = getLoginShopId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shop_id", shopId);
        jsonObject.put("is_default", ShopConstant.ShopAddressIsDefault.YES.getValue());
        return JSONObject.parseObject(callDataCenter(DataCenterMethod.SHOP_ADDRESS_GET, jsonObject), ShopAddressVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addShopAddress(ShopAddressVo shopAddressVo) {
        shopAddressVo.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.SHOP_ADDRESS));
        shopAddressVo.setUserId(getLoginUserId());
        shopAddressVo.setShopId(getLoginShopId());
        shopAddressVo.setUserName(getLoginUserName());
        shopAddressVo.setCreateTime(DateUtil.getNowTimeStr());

        // 如果当前为默认收货地址，则修改其他地址为非默认地址
        this.updateShopAddressToNotDefault(shopAddressVo);
        callDataCenter(DataCenterMethod.SHOP_ADDRESS_INSERT, (JSONObject) JSON.toJSON(shopAddressVo));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateShopAddress(ShopAddressVo shopAddressVo) {
        Assert.notNull(getShopAddressById(shopAddressVo.getId()), "修改的收货地址不存在");
        shopAddressVo.setModifyTime(DateUtil.getNowTimeStr());

        // 如果当前为默认收货地址，则修改其他地址为非默认地址
        this.updateShopAddressToNotDefault(shopAddressVo);
        callDataCenter(DataCenterMethod.SHOP_ADDRESS_UPDATE, (JSONObject) JSON.toJSON(shopAddressVo));
    }

    @Override
    public void deleteShopAddress(String id) {
        ShopAddressVo shopAddressVo = this.getShopAddressById(id);

        Assert.notNull(shopAddressVo, "收货地址不存在");
        callDataCenter(DataCenterMethod.SHOP_ADDRESS_DELETE, (JSONObject) JSON.toJSON(shopAddressVo));
    }

    private void updateShopAddressToNotDefault(ShopAddressVo shopAddressVo) {
        if (shopAddressVo.getIsDefault().equals(ShopConstant.ShopAddressIsDefault.YES.getValue())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("shop_id", getLoginShopId());
            jsonObject.put("is_default", ShopConstant.ShopAddressIsDefault.NO.getValue());
            callDataCenter(DataCenterMethod.SHOP_ADDRESS_UPDATE, jsonObject);
        }
    }
}