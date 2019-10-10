package com.ly.mt.home.tob.purchases.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.purchases.service.PurchasesItemsService;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesItemsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进货商品实现类
 *
 * @author: linan
 * @date: 2019/9/10
 **/
@Service
public class PurchasesItemsServiceImpl extends BaseServiceImpl implements PurchasesItemsService {

    @Override
    public void addPurchasesItems(ShopPurchasesItemsVo vo) {
        callDataCenter(DataCenterMethod.SHOP_PURCHASES_ITEMS_INSERT, (JSONObject) JSON.toJSON((vo)));
    }

    @Override
    public Integer getShopItemNum(String shopId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shop_id", shopId);
        String num = callDataCenter(DataCenterMethod.SHOP_PURCHASES_ITEMS_NUM_GET, jsonObject);
        return StringUtils.isEmpty(num) ? 0 : Integer.parseInt(num);
    }

    @Override
    public Integer getSaleNum(String spuId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("spu_id", spuId);
        String num = callDataCenter(DataCenterMethod.SHOP_PURCHASES_ITEMS_SPU_NUM_GET, jsonObject);
        return StringUtils.isEmpty(num) ? 0 : Integer.parseInt(num);
    }
}
