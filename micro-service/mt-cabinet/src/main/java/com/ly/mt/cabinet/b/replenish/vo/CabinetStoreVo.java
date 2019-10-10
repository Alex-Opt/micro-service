package com.ly.mt.cabinet.b.replenish.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 店铺信息VO
 */
public class CabinetStoreVo implements Serializable {
    private String id;

    private String name;//

    private String address;//店铺地址

    private String detailAddress;//详细地址

    private String belongCircle;//所属商圈

    private String createStatus;//0:待创建  1:已创建  2:已使用

    private long mId;//区域id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getBelongCircle() {
        return belongCircle;
    }

    public void setBelongCircle(String belongCircle) {
        this.belongCircle = belongCircle;
    }

    public String getCreateStatus() {
        return createStatus;
    }

    public void setCreateStatus(String createStatus) {
        this.createStatus = createStatus;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }
}