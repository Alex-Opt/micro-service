package com.ly.mt.core.base.entity.basic;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class BasicArea extends BaseEntity {
    private String pid;
    private String mId;
    private String mName;
    private String level;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}