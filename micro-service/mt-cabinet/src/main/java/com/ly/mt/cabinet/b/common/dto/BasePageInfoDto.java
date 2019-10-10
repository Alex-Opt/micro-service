package com.ly.mt.cabinet.b.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页数据dto
 */
@ApiModel
public class BasePageInfoDto {
    /**
     * 每页数量
     */
    @ApiModelProperty(name = "每页数量")
    private Integer pageSize;

    /**
     * 页码
     */
    @ApiModelProperty(name = "页码")
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
