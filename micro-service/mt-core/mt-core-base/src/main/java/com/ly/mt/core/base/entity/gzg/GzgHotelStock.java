package com.ly.mt.core.base.entity.gzg;

import java.io.Serializable;
/** @deprecated */
public class GzgHotelStock implements Serializable {
    private String id;

    private String hotelId;

    private String skuId;

    private String skuName;

    private String skuCode;

    private Integer totalNum;

    private Integer sellOutNum;

    private Integer returnNum;

    private Integer surplusNum;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getSellOutNum() {
        return sellOutNum;
    }

    public void setSellOutNum(Integer sellOutNum) {
        this.sellOutNum = sellOutNum;
    }

    public Integer getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Integer returnNum) {
        this.returnNum = returnNum;
    }

    public Integer getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", hotelId=").append(hotelId);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuName=").append(skuName);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", sellOutNum=").append(sellOutNum);
        sb.append(", returnNum=").append(returnNum);
        sb.append(", surplusNum=").append(surplusNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}