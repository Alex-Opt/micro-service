package com.ly.mt.order.server.activity.strategy.ConcreteStrategy;

import com.ly.mt.core.base.entity.activity.ActivityModel;
import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.trade.OrderModel;
import com.ly.mt.order.server.activity.strategy.ActivityStrategy.ActivityStrategy;
import com.ly.mt.order.server.trade.decorator.DecoratorCommonServiceComponent;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 满减活动策略实现类
 */
public class FullReductionStrategy  implements ActivityStrategy {


    @Override
    public Map<String,Object> calcPreferentialPrice(List<GoodsSkuModel> itemList, OrderModel model,
                                                    DecoratorCommonServiceComponent decoratorCommonServiceComponent) {

        Map<String,Object> result = new HashMap<>();
        BigDecimal preferentialPrice = decoratorCommonServiceComponent.getActivityInfoService().calcPreferentialPrice(itemList,
                model.getOrderVo().getOrderOldMoney(),model.getOrderVo().getBuyerId());
        result.put("preferentialPrice",preferentialPrice);
        List<ActivityModel> activityModels = decoratorCommonServiceComponent.getActivityInfoService().getActivityModelsForOrder();
        result.put("activityModels",activityModels);
        decoratorCommonServiceComponent.getActivityInfoService().getActivityIds().clear();
        return result;
    }


}
