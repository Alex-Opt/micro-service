package com.ly.mt.center.data.runnerprofit.entity.dto;


import java.math.BigDecimal;
import java.util.Date;

/**
 * @description
 * 赚钱人配置dto
 * @author panjingtian
 * @date 2019/6/28 4:59 PM
 */
public class RLodeRunnerUserConfigsDto {


    private Long level;

    private BigDecimal proportion;

    private Long createdUser;

    private Date createTime;


    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public Long getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Long createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
