package com.ly.mt.home.tob.purchases.factory.decorator;

import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.home.base.constant.ShopConstant;
import com.ly.mt.home.base.constant.ShopPurchasesEnum;
import com.ly.mt.home.base.util.ApplicationContextHelper;
import com.ly.mt.home.tob.address.service.ShopAddressService;
import com.ly.mt.home.tob.address.vo.ShopAddressVo;
import com.ly.mt.home.tob.goods.service.GoodsSkuService;
import com.ly.mt.home.tob.goods.vo.GoodsSkuVo;
import com.ly.mt.home.tob.purchases.service.PurchasesItemsService;
import com.ly.mt.home.tob.purchases.service.ShopPurchasesRefundItemsService;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesItemsVo;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_ORDINARY_EXPRESS;

/**
 * 订单基本计算
 *
 * @author: linan
 * @date: 2019/09/11
 **/
public class BasicDecorator extends AbstractPriceComponent {
    private GoodsSkuService goodsSkuService = ApplicationContextHelper.getBean(GoodsSkuService.class);

    private ShopPurchasesRefundItemsService shopPurchasesRefundItemsService = ApplicationContextHelper.getBean(ShopPurchasesRefundItemsService.class);

    private PurchasesItemsService purchasesItemsService = ApplicationContextHelper.getBean(PurchasesItemsService.class);

    private ShopAddressService shopAddressService = ApplicationContextHelper.getBean(ShopAddressService.class);

    @Override
    public ShopPurchasesVo calc(ShopPurchasesVo shopPurchasesVo) {
        List<ShopPurchasesItemsVo> itemList = shopPurchasesVo.getItemList();
        // 总价
        BigDecimal amount = new BigDecimal(ShopConstant.FREIGHT);
        // 进货数
        Integer skuNum = 0;

        // 基础价格
        for (ShopPurchasesItemsVo itemVo : itemList) {
            GoodsSkuVo skuVo = goodsSkuService.getGoodsSkuInfoById(itemVo.getSkuId());
            itemVo.setShopId(shopPurchasesVo.getShopId());
            itemVo.setSkuPrice(skuVo.getMarketPrice());
            itemVo.setSkuName(skuVo.getName());
            itemVo.setSkuNum(itemVo.getSkuNum());
            BigDecimal skuAmount = new BigDecimal(skuVo.getMarketPrice()).multiply(new BigDecimal(itemVo.getSkuNum()));
            amount = amount.add(skuAmount);
            skuNum += Integer.parseInt(itemVo.getSkuNum());
        }

        Assert.isTrue(skuNum >= ShopConstant.BASE_NUM, "进货数量不满足最低要求");

        Integer totalNum = purchasesItemsService.getShopItemNum(shopPurchasesVo.getShopId());
        Integer totalRefundNum = shopPurchasesRefundItemsService.getShopRefundItemNum(shopPurchasesVo.getShopId());

        // id
        shopPurchasesVo.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_SHOP_PURCHASES));
        // 订单金额
        shopPurchasesVo.setAmount(String.valueOf(amount.setScale(2, BigDecimal.ROUND_HALF_UP)));
        // 累计进货数，计算阶梯价用
        shopPurchasesVo.setTotalSkuNum(skuNum + totalNum - totalRefundNum);
        // 商家订单默认普通快递
        shopPurchasesVo.setDeliveryType(DISTRIBUTE_TYPE_ORDINARY_EXPRESS.getId());
        // 从配置获取运费
        shopPurchasesVo.setDeliveryFee(ShopConstant.FREIGHT);
        // 订单状态
        shopPurchasesVo.setStatus(ShopPurchasesEnum.SHOP_PURCHASES_TO_PAY.getCode());
        // 创建时间
        shopPurchasesVo.setCreateTime(DateUtil.getNowTimeStr());

        shopPurchasesVo.setActualAmount(shopPurchasesVo.getAmount());

        ShopAddressVo shopAddressVo = shopAddressService.getShopAddressById(shopPurchasesVo.getAddressId());

        Assert.notNull(shopAddressVo, "收货地址不存在");
        // 收货地址
        shopPurchasesVo.setMobile(shopAddressVo.getReceivePhone());
        shopPurchasesVo.setConsignee(shopAddressVo.getReceiveName());
        shopPurchasesVo.setAddress(shopAddressVo.getUserAddress());
        shopPurchasesVo.setFullAddress(shopAddressVo.getUserAddress());
        shopPurchasesVo.setProvinceCode(shopAddressVo.getProvinceCode());
        shopPurchasesVo.setCityCode(shopAddressVo.getCityCode());
        shopPurchasesVo.setAddressId(shopAddressVo.getId());
        shopPurchasesVo.setDistrictCode(shopAddressVo.getDistrictCode());
        return shopPurchasesVo;
    }

}
