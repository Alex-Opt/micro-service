package com.ly.mt.home.tob.stocks.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.stocks.service.ShopStocksService;
import com.ly.mt.home.tob.stocks.vo.ShopStocksVo;
import org.springframework.stereotype.Service;

/**
 * @author linan
 * @description : 商家库存
 * @date 2019/7/17
 */
@Service
public class ShopStocksServiceImpl extends BaseServiceImpl implements ShopStocksService {

    @Override
    public ShopStocksVo getStock(String shopId, String spuId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shop_id", shopId);
        jsonObject.put("spu_id", spuId);
        return JSONObject.parseObject(callDataCenter(DataCenterMethod.SHOP_STOCKS_GET, jsonObject), ShopStocksVo.class);
    }

    @Override
    public void addStock(ShopStocksVo shopStocksVo) {
        callDataCenter(DataCenterMethod.SHOP_ADDRESS_INSERT, (JSONObject) JSON.toJSON(shopStocksVo));
    }

    @Override
    public void updateStock(ShopStocksVo shopStocksVo) {
        callDataCenter(DataCenterMethod.SHOP_ADDRESS_UPDATE, (JSONObject) JSON.toJSON(shopStocksVo));
    }

}