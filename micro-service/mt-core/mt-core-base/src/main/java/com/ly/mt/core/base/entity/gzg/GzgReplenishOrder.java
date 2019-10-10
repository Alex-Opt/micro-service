package com.ly.mt.core.base.entity.gzg;

import java.io.Serializable;
import java.util.Date;
/** @deprecated */
public class GzgReplenishOrder implements Serializable {
    private String id;

    private String gzgOrderId;

    private String gzgOrderItmeId;

    private String hotelId;

    private String gzgId;

    private String cabinetNo;

    private Integer state;

    private Date createTime;

    public String getCabinetNo() {
        return cabinetNo;
    }

    public void setCabinetNo(String cabinetNo) {
        this.cabinetNo = cabinetNo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGzgOrderId() {
        return gzgOrderId;
    }

    public void setGzgOrderId(String gzgOrderId) {
        this.gzgOrderId = gzgOrderId;
    }

    public String getGzgOrderItmeId() {
        return gzgOrderItmeId;
    }

    public void setGzgOrderItmeId(String gzgOrderItmeId) {
        this.gzgOrderItmeId = gzgOrderItmeId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getGzgId() {
        return gzgId;
    }

    public void setGzgId(String gzgId) {
        this.gzgId = gzgId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gzgOrderId=").append(gzgOrderId);
        sb.append(", gzgOrderItmeId=").append(gzgOrderItmeId);
        sb.append(", hotelId=").append(hotelId);
        sb.append(", gzgId=").append(gzgId);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}