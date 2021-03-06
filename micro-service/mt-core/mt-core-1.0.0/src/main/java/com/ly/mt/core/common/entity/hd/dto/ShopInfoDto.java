package com.ly.mt.core.common.entity.hd.dto;

import java.io.Serializable;

/**
 * @description
 *
 * 门店dto
 *
 * @author panjingtian
 * @date 2019/6/15 12:21 PM
 */
public class ShopInfoDto implements Serializable {

    /**
     * 店铺名称
     */
    private String shopName;


    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 手机号
     */
    private String mobile;


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
