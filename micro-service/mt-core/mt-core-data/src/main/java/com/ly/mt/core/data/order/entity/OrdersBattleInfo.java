package com.ly.mt.core.data.order.entity;

/**
 * orders_battle_info
 *
 * @author taoye
 */
public class OrdersBattleInfo {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 店铺ID
     */
    private String shopId;
    /**
     * 抢单用户ID
     */
    private String userId;
    /**
     * 买家ID
     */
    private String buyerId;
    /**
     * 骑手名称
     */
    private String deliverymanName;
    /**
     * 骑手手机号码
     */
    private String deliverymanPhone;
    /**
     * 订单类型
     *
     * @see com.ly.mt.core.base.dict.OrderType
     */
    private String type;
    /**
     * status
     *
     * @see com.ly.mt.core.base.dict.OrderBattleStatus
     */
    private String status;
    /**
     * 抢单次数
     */
    private String grabedNum;
    /**
     * 抢单时间
     */
    private String grabedAt;
    /**
     * 校验截止时间
     */
    private String checkedAt;
    /**
     * 接单时间
     */
    private String takedAt;
    /**
     * 预计取货时间
     */
    private String estimateSendedAt;
    /**
     * 发货方式
     */
    private String sendModel;
    /**
     * 发货公司
     */
    private String sendCom;
    /**
     * 发货号
     */
    private String sendNo;
    /**
     * 发货时间
     */
    private String sendedAt;
    /**
     * 预计完成时间
     */
    private String estimatedSuccessedAt;
    /**
     * 完成时间
     */
    private String successedAt;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
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
        this.deliverymanName = deliverymanName;
    }

    public String getDeliverymanPhone() {
        return deliverymanPhone;
    }

    public void setDeliverymanPhone(String deliverymanPhone) {
        this.deliverymanPhone = deliverymanPhone;
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

    public String getGrabedNum() {
        return grabedNum;
    }

    public void setGrabedNum(String grabedNum) {
        this.grabedNum = grabedNum;
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
        this.sendCom = sendCom;
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
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