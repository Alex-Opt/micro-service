package com.ly.mt.core.base.entity.shop;

/**
 * 淘金人树
 *//** @deprecated */
public class LodeRunnerTrees {

    /**
     * 编号
     */
    private String id;


    /**
     * 淘金人编号
     */
    private String userId;

    /**
     *父级淘金人编号，无父级：-1
     */
    private String fatherId;

    /**
     * 顶层淘金人编号（往上5层或不到5级的顶层user_id）
     */
    private String topUserId;

    /**
     * 6层内的级别
     */
    private String userLevel;

    /**
     * 用户来源（1：邀请，2：通讯录，3：流量，4：正常注册）
     */
    private String userSource;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String modifyTime;

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

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getTopUserId() {
        return topUserId;
    }

    public void setTopUserId(String topUserId) {
        this.topUserId = topUserId;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserSource() {
        return userSource;
    }

    public void setUserSource(String userSource) {
        this.userSource = userSource;
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
}