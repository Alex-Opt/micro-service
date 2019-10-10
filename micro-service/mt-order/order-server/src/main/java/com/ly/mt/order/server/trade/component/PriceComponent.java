package com.ly.mt.order.server.trade.component;

import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.trade.OrderModel;
import com.ly.mt.order.server.trade.decorator.DecoratorCommonServiceComponent;

import java.util.List;

/**
 * @author zhanglifeng
 * @description 价格组件
 * @date 2019-05-23
 */

public abstract class PriceComponent {

    /**
     * @param itemList 购买的商品集合
     * @return
     * @description 计算购买的商品列表的价格
     */
    public abstract OrderModel calcOrderPrice(List<GoodsSkuModel> itemList, DecoratorCommonServiceComponent decoratorCommonServiceComponent,String userId);

}
