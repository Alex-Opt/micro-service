package com.ly.mt.core.base.entity.marketing;

import com.ly.mt.core.base.entity.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 *@Description 收益日志参数对象
 *@Author  zhuyh
 *//** @deprecated */
public class ShopProfitsLogsParamsVo extends BaseEntity {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 收益类型
     */
    private Integer tp;


    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 收益类型列表
     */
    private List<Integer> tpList;

    /**
     * 收益状态
     */
    private Integer status;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTp() {
        return tp;
    }

    public void setTp(Integer tp) {
        this.tp = tp;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getTpList() {
        return tpList;
    }

    public void setTpList(List<Integer> tpList) {
        this.tpList = tpList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
