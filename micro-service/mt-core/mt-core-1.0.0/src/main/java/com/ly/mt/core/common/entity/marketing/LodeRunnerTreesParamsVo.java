package com.ly.mt.core.common.entity.marketing;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 *@Description 淘金人树
 *@Author  zhuyh
 */
public class LodeRunnerTreesParamsVo extends BaseEntity {

    /**
     * 淘金人id
     */
    private Long userId;

    /**
     * 父级淘金人编号，无父级：-1
     */
    private Long fatherId;

    /**
     * 顶层淘金人编号（往上5层或不到5级的顶层user_id）
     */
    private Long topUserId;

    /**
     * 6层内的级别
     */
    private Integer userLevel;

    /**
     * 用户来源（1：邀请，2：通讯录，3：流量，4：正常注册）
     */

    private Integer userSource;

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
}
