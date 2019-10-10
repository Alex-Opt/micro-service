package com.ly.mt.core.data.cabinet.entity;

/**
 * cabinet_plan
 *
 * @author taoye
 */
public class CabinetPlan {
    /**
     * 主键id
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
     * 创建人名字
     */
    private String createName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改人名字
     */
    private String updateName;
    /**
     * 修改时间
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

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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