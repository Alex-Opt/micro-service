package com.ly.mt.core.data.redis.entity;

/**
 * redis_refresh
 *
 * @author taoye
 */
public class RedisRefresh {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 缓存处理主键id1
     */
    private String id1;
    /**
     * 缓存处理主键id2
     */
    private String id2;
    /**
     * 缓存处理主键id3
     */
    private String id3;
    /**
     * 缓存处理主键id4
     */
    private String id4;
    /**
     * 缓存处理主键id5
     */
    private String id5;
    /**
     * 缓存刷新触发类型
     *
     * @see com.ly.mt.core.base.dict.TriggerType
     */
    private String triggerType;
    /**
     * 缓存刷新处理类型
     */
    private String refreshType;
    /**
     * 缓存刷新处理状态
     *
     * @see com.ly.mt.core.base.dict.RedisRefreshStatus
     */
    private String refreshStatus;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId3() {
        return id3;
    }

    public void setId3(String id3) {
        this.id3 = id3;
    }

    public String getId4() {
        return id4;
    }

    public void setId4(String id4) {
        this.id4 = id4;
    }

    public String getId5() {
        return id5;
    }

    public void setId5(String id5) {
        this.id5 = id5;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public String getRefreshType() {
        return refreshType;
    }

    public void setRefreshType(String refreshType) {
        this.refreshType = refreshType;
    }

    public String getRefreshStatus() {
        return refreshStatus;
    }

    public void setRefreshStatus(String refreshStatus) {
        this.refreshStatus = refreshStatus;
    }
}