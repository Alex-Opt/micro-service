package com.ly.mt.core.common.entity.border;

/**
 * 商家抢单信息表--可以理解为发货单
 * @author zhanglifeng
 * @date 2019-06-13
 */
public class OrdersBattleInfo {
    private String id;

    private String orderId;

    private String shopId;

    private String userId;

    private String buyerId;

    private String deliverymanName;

    private String deliverymanPhone;

    private String type;

    private String status;

    private String grabedNum;

    private String grabedAt;

    private String checkedAt;

    private String takedAt;

    private String estimateSendedAt;

    private String sendModel;

    private String sendCom;

    private String sendNo;

    private String sendedAt;

    private String estimatedSuccessedAt;

    private String successedAt;

    private String createTime;

    private String modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getDeliverymanName() {
        return deliverymanName;
    }

    public void setDeliverymanName(String deliverymanName) {
        this.deliverymanName = deliverymanName == null ? null : deliverymanName.trim();
    }

    public String getGrabedNum() {
        return grabedNum;
    }

    public void setGrabedNum(String grabedNum) {
        this.grabedNum = grabedNum;
    }

    public String getDeliverymanPhone() {
        return deliverymanPhone;
    }

    public void setDeliverymanPhone(String deliverymanPhone) {
        this.deliverymanPhone = deliverymanPhone == null ? null : deliverymanPhone.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrabedAt() {
        return grabedAt;
    }

    public void setGrabedAt(String grabedAt) {
        this.grabedAt = grabedAt;
    }

    public String getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(String checkedAt) {
        this.checkedAt = checkedAt;
    }

    public String getTakedAt() {
        return takedAt;
    }

    public void setTakedAt(String takedAt) {
        this.takedAt = takedAt;
    }

    public String getEstimateSendedAt() {
        return estimateSendedAt;
    }

    public void setEstimateSendedAt(String estimateSendedAt) {
        this.estimateSendedAt = estimateSendedAt;
    }

    public String getSendModel() {
        return sendModel;
    }

    public void setSendModel(String sendModel) {
        this.sendModel = sendModel;
    }

    public String getSendCom() {
        return sendCom;
    }

    public void setSendCom(String sendCom) {
        this.sendCom = sendCom == null ? null : sendCom.trim();
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo == null ? null : sendNo.trim();
    }

    public String getSendedAt() {
        return sendedAt;
    }

    public void setSendedAt(String sendedAt) {
        this.sendedAt = sendedAt;
    }

    public String getEstimatedSuccessedAt() {
        return estimatedSuccessedAt;
    }

    public void setEstimatedSuccessedAt(String estimatedSuccessedAt) {
        this.estimatedSuccessedAt = estimatedSuccessedAt;
    }

    public String getSuccessedAt() {
        return successedAt;
    }

    public void setSuccessedAt(String successedAt) {
        this.successedAt = successedAt;
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