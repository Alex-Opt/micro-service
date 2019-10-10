package com.ly.mt.mis.gzg.plan.vo;

/**
 * 配货方案分页表格vo
 *
 * @author taoye
 */
public class GzgPlanDatagridVo {
    /**
     * id
     */
    private String id;
    /**
     * 方案名称
     */
    private String planName;
    /**
     * 货柜类型
     *
     * @see com.ly.mt.core.data.cabinet.entity.CabinetCategroy
     */
    private String cabinetCategroyId;
    /**
     * 货柜类别名称
     */
    private String cabinetCategroyName;
    /**
     * 使用方案货柜数量
     */
    private String planCount;
    /**
     * 可放商品数
     */
    private String goodsCount;
    /**
     * 货道数
     */
    private String aisleNumber;
    /**
     * 舱门数
     */
    private String doorNumber;
    /**
     * 操作人
     */
    private String updateName;
    /**
     * 操作时间
     */
    private String updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getCabinetCategroyId() {
        return cabinetCategroyId;
    }

    public void setCabinetCategroyId(String cabinetCategroyId) {
        this.cabinetCategroyId = cabinetCategroyId;
    }

    public String getCabinetCategroyName() {
        return cabinetCategroyName;
    }

    public void setCabinetCategroyName(String cabinetCategroyName) {
        this.cabinetCategroyName = cabinetCategroyName;
    }

    public String getPlanCount() {
        return planCount;
    }

    public void setPlanCount(String planCount) {
        this.planCount = planCount;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getAisleNumber() {
        return aisleNumber;
    }

    public void setAisleNumber(String aisleNumber) {
        this.aisleNumber = aisleNumber;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}