package com.ly.mt.center.data.runnerprofit.entity.dto;

/**
 * @description
 * treesDto
 * @author panjingtian
 * @date 2019/6/30 5:07 PM
 */
public class RSimpRunnerTreesDto {

    private Long userId;
    private Long fatherId;
    private Integer rank;
    /**
     * 用户来源（1：邀请，2：通讯录，3：流量，4：正常注册）
     */
    private Integer userSource;

    public RSimpRunnerTreesDto() {
    }

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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getUserSource() {
        return userSource;
    }

    public void setUserSource(Integer userSource) {
        this.userSource = userSource;
    }
}
