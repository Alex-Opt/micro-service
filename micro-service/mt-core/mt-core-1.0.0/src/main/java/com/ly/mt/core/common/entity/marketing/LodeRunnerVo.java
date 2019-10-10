package com.ly.mt.core.common.entity.marketing;

import java.math.BigDecimal;
import java.util.Date;

/**
 *@Description 淘金者对象
 *@Author  zhuyh
 */
public class LodeRunnerVo {

    private Long userId;

    /**
     * 商铺id
     */
    private Long shopId;
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 进货数量
     */
    private Integer purachasesNum;


    /**
     *  是否入住 Y是、N否
     */
    private String liveInState;

    /**
     * 淘金返利
     */
    private BigDecimal profits;

    /**
     * 时间
     */
    private Date createTime;

    /**
     * 人数
     */
    private Integer userCount;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getPurachasesNum() {
        return purachasesNum;
    }

    public void setPurachasesNum(Integer purachasesNum) {
        this.purachasesNum = purachasesNum;
    }

    public String getLiveInState() {
        return liveInState;
    }

    public void setLiveInState(String liveInState) {
        this.liveInState = liveInState;
    }

    public BigDecimal getProfits() {
        return profits;
    }

    public void setProfits(BigDecimal profits) {
        this.profits = profits;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
}
