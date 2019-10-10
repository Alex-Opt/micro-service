package com.ly.mt.core.data.basic.entity;

/**
 * basic_area
 *
 * @author taoye
 */
public class BasicArea {
    /**
     * 管易区域ID
     */
    private String id;
    /**
     * 管易上级区域ID
     */
    private String pid;
    /**
     * 管易区域名称
     */
    private String name;
    /**
     * 前端区域ID
     */
    private String mId;
    /**
     * 前端上级区域ID
     */
    private String mPid;
    /**
     * 前端区域名称
     */
    private String mName;
    /**
     * 前端组件定位
     */
    private String mIndex;
    /**
     * 区域代码
     */
    private String code;
    /**
     * 区域级别
     */
    private String level;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;
    /**
     * 数据来源
     */
    private String dataSource;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public String getMPid() {
        return mPid;
    }

    public void setMPid(String mPid) {
        this.mPid = mPid;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getMIndex() {
        return mIndex;
    }

    public void setMIndex(String mIndex) {
        this.mIndex = mIndex;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}