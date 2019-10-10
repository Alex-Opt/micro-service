package com.ly.mt.home.tob.discount.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.discount.service.DiscountService;
import com.ly.mt.home.tob.discount.vo.ShopPurchasesDiscountVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 商家进货优惠接口
 *
 * @author linan
 * @date 20190709
 */
@Service
public class DiscountServiceImpl extends BaseServiceImpl implements DiscountService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String totalDiscount() {
        String shopId = getLoginShopId();

        JSONObject json = new JSONObject();
        json.put("shop_id", shopId);

        return callDataCenter(DataCenterMethod.SHOP_PURCHASES_DISCOUNT_SHOP_DISCOUNT, json);

    }

    @Override
    public void addDiscount(ShopPurchasesDiscountVo shopPurchasesDiscountVo) {
        shopPurchasesDiscountVo.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_SHOP_PURCHASES_DISCOUNT));
        callDataCenter(DataCenterMethod.SHOP_PURCHASES_DISCOUNT_INSERT, (JSONObject) JSON.toJSON(shopPurchasesDiscountVo));
    }

    @Override
    public void updateDiscountStatus(String id, String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("status", status);
        //jsonObject.put("shop_id", getLoginShopId());
        callDataCenter(DataCenterMethod.SHOP_PURCHASES_DISCOUNT_UPDATE, jsonObject);
    }

    @Override
    public List<ShopPurchasesDiscountVo> getDiscountByPurchasesId(String purchasesId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("purchases_id", purchasesId);
        return JSONObject.parseArray(callDataCenter(DataCenterMethod.SHOP_PURCHASES_DISCOUNT_LIST, jsonObject), ShopPurchasesDiscountVo.class);
    }


}
