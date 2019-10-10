package com.ly.mt.core.common.entity.gzg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GzgReplenishRecord implements Serializable {
    private String id;

    private String replenishCode;

    private String replenishOrderId;

    private String replenishUserId;

    private Integer state;

    private Integer invalidType;

    private Date gradTime;

    private Date submitAuditTime;

    private Date arrivalAccountTime;

    private Date invalidTime;

    private BigDecimal amount;

    private String auditPicture;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReplenishCode() {
        return replenishCode;
    }

    public void setReplenishCode(String replenishCode) {
        this.replenishCode = replenishCode == null ? null : replenishCode.trim();
    }

    public String getReplenishOrderId() {
        return replenishOrderId;
    }

    public void setReplenishOrderId(String replenishOrderId) {
        this.replenishOrderId = replenishOrderId;
    }

    public String getReplenishUserId() {
        return replenishUserId;
    }

    public void setReplenishUserId(String replenishUserId) {
        this.replenishUserId = replenishUserId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getInvalidType() {
        return invalidType;
    }

    public void setInvalidType(Integer invalidType) {
        this.invalidType = invalidType;
    }

    public Date getGradTime() {
        return gradTime;
    }

    public void setGradTime(Date gradTime) {
        this.gradTime = gradTime;
    }

    public Date getSubmitAuditTime() {
        return submitAuditTime;
    }

    public void setSubmitAuditTime(Date submitAuditTime) {
        this.submitAuditTime = submitAuditTime;
    }

    public Date getArrivalAccountTime() {
        return arrivalAccountTime;
    }

    public void setArrivalAccountTime(Date arrivalAccountTime) {
        this.arrivalAccountTime = arrivalAccountTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAuditPicture() {
        return auditPicture;
    }

    public void setAuditPicture(String auditPicture) {
        this.auditPicture = auditPicture == null ? null : auditPicture.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", replenishCode=").append(replenishCode);
        sb.append(", replenishOrderId=").append(replenishOrderId);
        sb.append(", replenishUserId=").append(replenishUserId);
        sb.append(", state=").append(state);
        sb.append(", invalidType=").append(invalidType);
        sb.append(", gradTime=").append(gradTime);
        sb.append(", submitAuditTime=").append(submitAuditTime);
        sb.append(", arrivalAccountTime=").append(arrivalAccountTime);
        sb.append(", invalidTime=").append(invalidTime);
        sb.append(", amount=").append(amount);
        sb.append(", auditPicture=").append(auditPicture);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}