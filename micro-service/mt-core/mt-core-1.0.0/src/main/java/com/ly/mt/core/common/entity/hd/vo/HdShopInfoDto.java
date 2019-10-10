package com.ly.mt.core.common.entity.hd.vo;


import java.util.List;

/**
 * @description
 * 活动门店商品dto
 * @author panjingtian
 * @date 2019/6/19 4:31 PM
 */
public class HdShopInfoDto {

    private Long id;

    private String shopName;
    private Long operatorId;
    private String shopAddress;
    private String mobile;

    public HdShopInfoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
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

    public static List<HdShopInfoVoString> convert(){

        return null;

    }
}
