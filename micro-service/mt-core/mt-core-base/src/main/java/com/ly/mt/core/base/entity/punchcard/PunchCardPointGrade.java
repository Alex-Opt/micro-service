package com.ly.mt.core.base.entity.punchcard;

import com.ly.mt.core.base.entity.base.BaseEntity;

/**
 * 积分等级配置
 * @author  ypmu
 * @date 20190529
 *//** @deprecated */
public class PunchCardPointGrade extends BaseEntity {

    /**
     * 等级名称
     */
    private String title;
    /**
     * 等级图标
     */
    private String icon;
    /**
     * 累计积分（消费金额）要求的值
     */
    private String score;

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
}
