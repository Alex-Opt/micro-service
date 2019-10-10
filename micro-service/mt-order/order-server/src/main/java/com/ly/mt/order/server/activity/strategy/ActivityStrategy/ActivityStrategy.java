package com.ly.mt.order.server.activity.strategy.ActivityStrategy;

import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.trade.OrderModel;
import com.ly.mt.order.server.trade.decorator.DecoratorCommonServiceComponent;

import java.util.List;
import java.util.Map;

/**
 * 活动策略接口
 */
public interface ActivityStrategy {

     Map<String,Object> calcPreferentialPrice(List<GoodsSkuModel> itemList, OrderModel model,
                                              DecoratorCommonServiceComponent decoratorCommonServiceComponent);
}
