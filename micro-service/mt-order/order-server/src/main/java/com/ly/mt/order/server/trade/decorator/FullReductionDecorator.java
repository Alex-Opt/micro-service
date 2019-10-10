package com.ly.mt.order.server.trade.decorator;

import com.ly.mt.core.base.entity.activity.ActivityModel;
import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.trade.OrderModel;
import com.ly.mt.order.server.activity.strategy.ActivityContext;
import com.ly.mt.order.server.activity.strategy.ConcreteStrategy.FullReductionStrategy;
import com.ly.mt.order.server.trade.component.PriceComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wanglong
 * @description 优惠活动-----满减优惠活动的优惠价格计算
 * @date 2019-05-23
 */
public class FullReductionDecorator extends PriceDecorator {

    private final static Logger LOGGER = LoggerFactory.getLogger(FullReductionDecorator.class);

    private PriceComponent priceComponent;


    public FullReductionDecorator(PriceComponent priceComponent) {
        this.priceComponent = priceComponent;
    }

    @Override
    public OrderModel calcOrderPrice(List<GoodsSkuModel> itemList, DecoratorCommonServiceComponent decoratorCommonServiceComponent,String userId) {
        OrderModel model = priceComponent.calcOrderPrice(itemList, decoratorCommonServiceComponent,userId);
        LOGGER.info("================计算满减优惠活动后的订单价格开始========================");
        //使用满减策略计算价格
       ActivityContext activityContext = new ActivityContext(new FullReductionStrategy());
        Map<String,Object>  discountMap = activityContext.executeStrategy(itemList,model,decoratorCommonServiceComponent);
        model.setActivityList((discountMap.get("activityModels") == null)?new ArrayList<ActivityModel>():(List<ActivityModel> )discountMap.get("activityModels"));
        BigDecimal discountMoney = new BigDecimal(String.valueOf(discountMap.get("preferentialPrice")==null?"0":discountMap.get("preferentialPrice")));
        model.getOrderVo().setOrderDiscountMoney(String.valueOf(discountMoney));
        BigDecimal orderMoney = new BigDecimal(model.getOrderVo().getOrderOldMoney()).subtract(discountMoney);
        model.getOrderVo().setOrderMoney(orderMoney+"");
        LOGGER.info("================计算满减优惠活动后的订单价格结束========================");
        return model;
    }

}
