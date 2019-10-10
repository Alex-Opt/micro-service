package com.ly.mt.core.data.cabinet.entity;

/**
 * cabinet_plan_list
 *
 * @author taoye
 */
public class CabinetPlanList {
    /**
     * 主键id
     */
    private String id;
    /**
     * 方案id
     *
     * @see com.ly.mt.core.data.cabinet.entity.CabinetPlan
     */
    private String cabinetPlanId;
    /**
     * 方案类型
     *
     * @see com.ly.mt.core.base.dict.CabinetPlanType
     */
    private String cabinetPlanType;
    /**
     * 舱门/货道编号
     */
    private String number;
    /**
     * 商品sku_id
     */
    private String skuId;
    /**
     * 商品数量
     */
    private String skuCount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCabinetPlanId() {
        return cabinetPlanId;
    }

    public void setCabinetPlanId(String cabinetPlanId) {
        this.cabinetPlanId = cabinetPlanId;
    }

    public String getCabinetPlanType() {
        return cabinetPlanType;
    }

    public void setCabinetPlanType(String cabinetPlanType) {
        this.cabinetPlanType = cabinetPlanType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuCount() {
        return skuCount;
    }

    public void setSkuCount(String skuCount) {
        this.skuCount = skuCount;
    }
}