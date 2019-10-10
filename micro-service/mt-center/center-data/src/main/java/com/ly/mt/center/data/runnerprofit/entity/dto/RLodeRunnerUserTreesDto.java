package com.ly.mt.center.data.runnerprofit.entity.dto;

import java.util.Date;

/**
 * @description
 *
 * 赚钱人树dto
 *
 * @author panjingtian
 * @date 2019/6/28 5:00 PM
 */
public class RLodeRunnerUserTreesDto {


    private Long userId;

    private Long fatherId;

    private Long topUserId;

    private Integer userLevel;

    private Integer userSource;

    private Date createTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public Long getTopUserId() {
        return topUserId;
    }

    public void setTopUserId(Long topUserId) {
        this.topUserId = topUserId;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getUserSource() {
        return userSource;
    }

    public void setUserSource(Integer userSource) {
        this.userSource = userSource;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
