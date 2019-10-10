package com.ly.mt.mis.home.user.vo;

/**
 * HomebUserDatagridVo
 *
 * @author taoye
 */
public class HomebUserDatagridVo {
    /**
     * 商家ID
     */
    private String id;
    /**
     * 注册信息
     */
    private String registInfo;
    /**
     * 商家信息
     */
    private String sellerInfo;
    /**
     * 商家属性
     */
    private String sellerAttribute;
    /**
     * 店铺信息
     */
    private String shopInfo;
    /**
     * 售卖记录
     */
    private String salesRecord;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistInfo() {
        return registInfo;
    }

    public void setRegistInfo(String registInfo) {
        this.registInfo = registInfo;
    }

    public String getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(String sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public String getSellerAttribute() {
        return sellerAttribute;
    }

    public void setSellerAttribute(String sellerAttribute) {
        this.sellerAttribute = sellerAttribute;
    }

    public String getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(String shopInfo) {
        this.shopInfo = shopInfo;
    }

    public String getSalesRecord() {
        return salesRecord;
    }

    public void setSalesRecord(String salesRecord) {
        this.salesRecord = salesRecord;
    }
}