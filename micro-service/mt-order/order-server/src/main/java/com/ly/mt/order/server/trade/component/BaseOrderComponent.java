package com.ly.mt.order.server.trade.component;

import com.ly.mt.core.base.entity.goods.GoodsSkuInfo;
import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.goods.GoodsSpuInfoForSkuVo;
import com.ly.mt.core.base.entity.trade.OrderModel;
import com.ly.mt.core.base.entity.trade.OrderVo;
import com.ly.mt.order.server.goods.GoodsSkuService;
import com.ly.mt.order.server.trade.decorator.DecoratorCommonServiceComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhanglifeng
 * @description 订单基础价格组件
 * @date 2019-05-23
 */
@Component
public class BaseOrderComponent extends PriceComponent {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseOrderComponent.class);

    /**
     * @param itemList 购买的商品集合
     * @return
     * @description 该方法实现订单原始价格的查询，封装。
     */
    @Override
    public OrderModel calcOrderPrice(List<GoodsSkuModel> itemList, DecoratorCommonServiceComponent decoratorCommonServiceComponent,String userId) {
        LOGGER.info("===============获取到订单原始价格========================");
        OrderModel orderModel = new OrderModel();
        BigDecimal tempPrice = BigDecimal.ZERO;
        for (GoodsSkuModel goodsSkuModel : itemList) {
            //未来得到商品信息可以直接从redis中拿。现在先走库
            String skuId = goodsSkuModel.getSkuId();
            String skuNum = goodsSkuModel.getSkuNum();
            GoodsSkuService goodsSkuService = decoratorCommonServiceComponent.getGoodsSkuService();
            GoodsSkuInfo goodsSkuInfo = goodsSkuService.getGoodsSkuInfoBySkuId(Long.parseLong(skuId));
            String skuPrice = goodsSkuInfo.getMarketPrice();
            goodsSkuModel.setSkuPrice(skuPrice);
            tempPrice = (new BigDecimal(skuPrice).multiply(new BigDecimal(skuNum))).add(tempPrice);
            goodsSkuModel.setSkuName(goodsSkuInfo.getName());
            GoodsSpuInfoForSkuVo goodsSpuInfo = goodsSkuService.getGoodsSpuInfoBySpuId(Long.valueOf(goodsSkuModel.getSpuId()));
            goodsSkuModel.setSpuName(goodsSpuInfo.getName());
        }
        String originalPrice = tempPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderOldMoney(originalPrice);
        //一开始不享受任何优惠时订单实付金额和订单原价一样。
        orderVo.setOrderMoney(originalPrice);
        orderModel.setItemList(itemList);
        orderVo.setBuyerId(userId);
        orderModel.setOrderVo(orderVo);
        return orderModel;
    }
}
