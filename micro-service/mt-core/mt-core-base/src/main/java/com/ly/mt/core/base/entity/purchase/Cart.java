package com.ly.mt.core.base.entity.purchase;

import com.ly.mt.core.base.entity.buycar.CarSku;
import com.ly.mt.core.base.util.StringUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/** @deprecated */
public class Cart {

    private Map<String, CarSku> items = new HashMap<>();
    private String buyerId;
    private String sellerId;
    private String shopId;

    /**
     * add Items
     *
     * @param sku
     */
    public void addItems(CarSku sku) {
        if (items.isEmpty()) {
            items.put(sku.getSkuId(), sku);
            return;
        }
        if (items.get(sku.getSkuId()) != null) {
            CarSku itemSku = items.get(sku.getSkuId());
            int itemNum = Integer.parseInt(itemSku.getNum());
            int skuNum = Integer.parseInt(sku.getNum());
            itemSku.setNum(String.valueOf(itemNum + skuNum));
        } else {
            items.put(sku.getSkuId(), sku);
        }
    }

    public void subCarNum(String skuId, int num) {
        CarSku itemSku = items.get(skuId);
        int itemNum = Integer.parseInt(itemSku.getNum());
        if (itemNum == 1 || itemNum <= num) {
            subCar(skuId);
        } else {
            itemSku.setNum(String.valueOf(itemNum - num));
        }
    }

    public void updateCarNum(String skuId, int num) {
        CarSku itemSku = items.get(skuId);
        itemSku.setNum(String.valueOf(num));
    }

    public void subCar(String skuId) {
        if(StringUtil.isNotEmpty(skuId)) {
            items.remove(skuId);
        }
    }

    /**
     * 购物车数量小计
     *
     * @return
     */
    public int getProductAmount() {
        Iterator<String> iterator = items.keySet().iterator();
        int result = 0;
        while (iterator.hasNext()) {
            CarSku itemSku = items.get(iterator.next());
            int num = Integer.parseInt(itemSku.getNum());
            result += num;
        }
        return result;
    }

    /**
     * 价格小计
     *
     * @return
     */
    public String getProductPrice() {
        double result = 0;
        Iterator<String> iterator = items.keySet().iterator();
        while (iterator.hasNext()) {
            CarSku itemSku = items.get(iterator.next());
            double p = Double.parseDouble(itemSku.getPrice());
            result += p;
        }
        BigDecimal b = new BigDecimal(result);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public Map<String, CarSku> getItems() {
        return items;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
