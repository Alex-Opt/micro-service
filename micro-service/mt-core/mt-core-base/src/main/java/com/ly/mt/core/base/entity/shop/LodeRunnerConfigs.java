package com.ly.mt.core.base.entity.shop;

/**
 * 淘金人配置
 *//** @deprecated */
public class LodeRunnerConfigs {

    /**
     * 编号
     */
    private String id;

    /**
     * 层级
     */
    private String level;

    /**
     * 返现比例
     */
    private String proportion;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建者
     */
    private String createdUser;

    /**
     * 更新时间
     */
    private String modifyTime;

    /**
     * 修改者
     */
    private String updatedUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }
}