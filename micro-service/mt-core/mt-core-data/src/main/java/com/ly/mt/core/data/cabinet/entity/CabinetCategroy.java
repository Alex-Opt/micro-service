package com.ly.mt.core.data.cabinet.entity;

/**
 * cabinet_categroy
 *
 * @author taoye
 */
public class CabinetCategroy {
    /**
     * 主键id
     */
    private String id;
    /**
     * 货柜类别名称
     */
    private String cabinetName;
    /**
     * 货柜类别名称缩写
     */
    private String cabinetCode;
    /**
     * 货柜类型
     *
     * @see com.ly.mt.core.base.dict.CabinetType
     */
    private String cabinetType;
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
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人
     */
    private String createName;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 修改人
     */
    private String updateName;
    /**
     * 有效状态
     *
     * @see com.ly.mt.core.base.dict.ValidStatus
     */
    private String validStatus;


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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }
}