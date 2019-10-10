package com.ly.mt.center.data.profits.entity;

/**
* @Description: 赚钱收益日志参数对象
* @Author:         zhuyh
*/
public class ProfitsUserProfitsLogsParam {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 分页数据
     */
    private Integer rows;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 类型 0：收入、1：支出
     */
    private Integer type;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
