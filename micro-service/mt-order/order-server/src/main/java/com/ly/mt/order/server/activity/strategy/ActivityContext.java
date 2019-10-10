package com.ly.mt.order.server.activity.strategy;

import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.trade.OrderModel;
import com.ly.mt.order.server.activity.strategy.ActivityStrategy.ActivityStrategy;
import com.ly.mt.order.server.trade.decorator.DecoratorCommonServiceComponent;

import java.util.List;
import java.util.Map;


/**
 * 策略工厂
 */
public class ActivityContext {

    private ActivityStrategy activityStrategy;

    public ActivityContext(ActivityStrategy activityStrategy) {
        this.activityStrategy = activityStrategy;
    }

    public Map<String,Object> executeStrategy(List<GoodsSkuModel> itemList, OrderModel model,
                                              DecoratorCommonServiceComponent decoratorCommonServiceComponent){
        return activityStrategy.calcPreferentialPrice(itemList,model,decoratorCommonServiceComponent);
    }
}
