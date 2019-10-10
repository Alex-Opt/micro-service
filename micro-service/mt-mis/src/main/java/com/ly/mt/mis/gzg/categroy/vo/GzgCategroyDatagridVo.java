package com.ly.mt.mis.gzg.categroy.vo;

/**
 * 货柜类型分页表格vo
 *
 * @author taoye
 */
public class GzgCategroyDatagridVo {
    /**
     * 主键id
     */
    private String id;
    /**
     * 货柜名称
     */
    private String cabinetName;
    /**
     * 货柜名称缩写
     */
    private String cabinetCode;
    /**
     * 货柜类型
     */
    private String cabinetType;
    /**
     * 货柜类型
     */
    private String cabinetTypeName;
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
     * 最后操作人
     */
    private String updateName;
    /**
     * 最后操作时间
     */
    private String updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public String getCabinetType() {
        return cabinetType;
    }

    public void setCabinetType(String cabinetType) {
        this.cabinetType = cabinetType;
    }

    public String getCabinetTypeName() {
        return cabinetTypeName;
    }

    public void setCabinetTypeName(String cabinetTypeName) {
        this.cabinetTypeName = cabinetTypeName;
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