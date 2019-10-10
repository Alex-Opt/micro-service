package com.ly.mt.core.data.gzg.entity;

/**
 * gzg_info
 *
 * @author taoye
 */
public class GzgInfo {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 格子柜唯一code码
     */
    private String code;
    /**
     * 柜子所在酒店id
     */
    private String hotelId;
    /**
     * 格子柜位置
     */
    private String position;
    /**
     * 柜子对应配货方案id
     */
    private String planId;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 价格
     */
    private String price;
    /**
     * 购买时间
     */
    private String buyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }
}