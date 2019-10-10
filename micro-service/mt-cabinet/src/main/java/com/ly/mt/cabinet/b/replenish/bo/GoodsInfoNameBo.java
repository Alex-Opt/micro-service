package com.ly.mt.cabinet.b.replenish.bo;

import java.io.Serializable;

/**
 * 商品名称信息
 */
public class GoodsInfoNameBo implements Serializable {
    /**
     * sku_name
     */
    private String skuName;
    /**
     * spu_name
     */
    private String spuName;

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }
}