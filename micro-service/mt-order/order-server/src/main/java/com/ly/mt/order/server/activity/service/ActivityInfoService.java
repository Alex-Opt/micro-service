package com.ly.mt.order.server.activity.service;

import com.ly.mt.core.base.entity.activity.ActivityModel;
import com.ly.mt.core.base.entity.goods.GoodsSkuModel;

import java.math.BigDecimal;
import java.util.List;

public interface ActivityInfoService {
    BigDecimal calcPreferentialPrice(List<GoodsSkuModel> itemList, String oldOriginPrice,String userId);
    List<ActivityModel> getActivityModelsForOrder();
    List<String> getActivityIds();
}
