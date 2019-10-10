package com.ly.mt.core.base.entity.point;

import com.ly.mt.core.base.entity.base.BaseEntity;

/**
 * @author zhanglifeng
 * 积分等级配置
 *//** @deprecated */
public class PointGrade extends BaseEntity {

    private String id;
    private String title;
    private String icon;
    private String score;
    private String createTime;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
