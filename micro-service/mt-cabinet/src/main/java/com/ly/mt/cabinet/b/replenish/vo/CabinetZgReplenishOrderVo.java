package com.ly.mt.cabinet.b.replenish.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 格子柜B端补货：展柜补货单订单表
 *
 * @author wanghongliang
 * @date 2019-09-18
 */
@ApiModel(value = "展柜补货订单对象")
public class CabinetZgReplenishOrderVo {
    @ApiModelProperty(value = "展柜补货订单ID", required = true)
    private String id;

    @ApiModelProperty(value = "用户id", required = true)
    private String userId;

    @ApiModelProperty(value = "展柜编号", required = true)
    private String cabinetCode;

    @ApiModelProperty(value = "上线时间", required = true)
    private String onlineTime;

    @ApiModelProperty(value = "店铺id", required = true)
    private String cabinetStoreId;

    @ApiModelProperty(value = "店铺名称", required = true)
    private String cabinetStoreName;

    @ApiModelProperty(value = "店主姓名", required = true)
    private String ownerName;

    @ApiModelProperty(value = "店铺手机号", required = true)
    private String ownerPhone;

    @ApiModelProperty(value = "补货理由 0:TOP3商品售罄（运营给）1:套装售罄 2:该展柜库存剩余小于3 多条理由时0:1:2", required = true)
    private List<String> replenishmentReason;

    @ApiModelProperty(value = "店铺名称", required = true)
    private String shopName;

    @ApiModelProperty(value = "店铺地址", required = true)
    private String address;

    @ApiModelProperty(value = "店铺详细地址", required = true)
    private String detailAddress;

    @ApiModelProperty(value = "代补数量", required = true)
    private String num;

    @ApiModelProperty(value = "总库存", required = true)
    private String stockTotal;

    @ApiModelProperty(value = "状态 0：待补货  1：已完成", required = true)
    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getCabinetStoreId() {
        return cabinetStoreId;
    }

    public void setCabinetStoreId(String cabinetStoreId) {
        this.cabinetStoreId = cabinetStoreId;
    }

    public String getCabinetStoreName() {
        return cabinetStoreName;
    }

    public void setCabinetStoreName(String cabinetStoreName) {
        this.cabinetStoreName = cabinetStoreName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public List<String> getReplenishmentReason() {
        return replenishmentReason;
    }

    public void setReplenishmentReason(List<String> replenishmentReason) {
        this.replenishmentReason = replenishmentReason;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(String stockTotal) {
        this.stockTotal = stockTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
