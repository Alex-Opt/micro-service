package com.ly.mt.cabinet.b.replenish.bo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 格子柜B端补货：展柜订单补货理由表
 *
 * @author wanghongliang
 * @date 2019-09-18
 */
public class CabinetZgReplenishOrderReasonBo {

    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty(value = "对应展柜补货订单id", required = true)
    private String zgReplenishOrderId;

    @ApiModelProperty(value = "货柜编号", required = true)
    private String cabinetCode;

    @ApiModelProperty(value = "补货理由 补货理由 0:TOP3商品售罄（运营给）1:套装售罄 2:该展柜库存剩余小于7", required = true)
    private String replenishmentReason;

    @ApiModelProperty(value = "状态 0：待补货  1：已完成", required = true)
    private String status;

    @ApiModelProperty(value = "创建时间", required = true)
    private String createTime;

    @ApiModelProperty(value = "修改时间", required = true)
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZgReplenishOrderId() {
        return zgReplenishOrderId;
    }

    public void setZgReplenishOrderId(String zgReplenishOrderId) {
        this.zgReplenishOrderId = zgReplenishOrderId;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public String getReplenishmentReason() {
        return replenishmentReason;
    }

    public void setReplenishmentReason(String replenishmentReason) {
        this.replenishmentReason = replenishmentReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
