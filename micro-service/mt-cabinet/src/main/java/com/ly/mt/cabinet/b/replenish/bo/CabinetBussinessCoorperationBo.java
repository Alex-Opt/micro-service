package com.ly.mt.cabinet.b.replenish.bo;

/**
 * 商务合作信息
 */
public class CabinetBussinessCoorperationBo {
    /**
     * 主键id
     */
    private String id;
    /**
     * 是否有押金
     */
    private String isDeposit;
    /**
     * 押金金额
     */
    private String depositAmount;

    /**
     * 店主姓名
     */
    private String ownerUserId;
    /**
     * 店主手机号
     */
    private String ownerPhone;

    /**
     * 店主姓名
     */
    private String ownerName;
    /**
     * BD手机号
     */
    private String bdPhone;
    /**
     * bd用户id
     */
    private String bdUserId;
    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 合作照片
     */
    private String coorperationPic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsDeposit() {
        return isDeposit;
    }

    public void setIsDeposit(String isDeposit) {
        this.isDeposit = isDeposit;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getBdUserId() {
        return bdUserId;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getBdPhone() {
        return bdPhone;
    }

    public void setBdPhone(String bdPhone) {
        this.bdPhone = bdPhone;
    }

    public void setBdUserId(String bdUserId) {
        this.bdUserId = bdUserId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCoorperationPic() {
        return coorperationPic;
    }

    public void setCoorperationPic(String coorperationPic) {
        this.coorperationPic = coorperationPic;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}