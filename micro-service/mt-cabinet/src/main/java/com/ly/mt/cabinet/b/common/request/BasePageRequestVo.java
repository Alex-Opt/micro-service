package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 基础分页查询请求对象
 * 有涉及分页的请求对象直接继承此类
 */
@ApiModel("分页请求基础对象")
public class BasePageRequestVo {

    /**
     * 每页数量
     */
    @ApiModelProperty("每页数据数量")
    private Integer pageSize;

    /**
     * 页码
     */
    @ApiModelProperty("页码")
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
