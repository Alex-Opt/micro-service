package com.ly.mt.center.data.cabinet.bo;

import java.io.Serializable;

/**
 * 商品名称信息
 */
public class GoodsInfoNameBo implements Serializable {
    /**
     * sku_name
     */
    private String sku_name;
    /**
     * spu_name
     */
    private String spu_name;

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }
}