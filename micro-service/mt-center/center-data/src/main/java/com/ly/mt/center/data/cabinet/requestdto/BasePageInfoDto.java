package com.ly.mt.center.data.cabinet.requestdto;

/**
 * 分页数据dto
 */
public class BasePageInfoDto {
    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 页码
     */
    private Integer pageNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
