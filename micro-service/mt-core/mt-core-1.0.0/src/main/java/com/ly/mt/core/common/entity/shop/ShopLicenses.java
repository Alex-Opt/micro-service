package com.ly.mt.core.common.entity.shop;


/**
 *@Description 商家营业执照
 * wanglong
 */
public class ShopLicenses{

    /**
     * @Description商家营业执照Id
     */
    private String id;

    /**
     *@Description 店铺编号
     */
    private String shopId;

    /**
     * @Description地址
     */
    private String path;

    /**
     * @Description创建时间
     */
    private String  createTime;


    /**
     * @Description更新时间
     */
    private String modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}