package com.ly.mt.core.common.entity.hd.request;

import com.ly.mt.core.common.entity.hd.common.PageConditions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 */
@ApiModel("门店查询订单请求体")
public class ShopOrderRequestBody {

    @ApiModelProperty(value = "门店id主键",dataType = "string")
    private String shopId;

    @ApiModelProperty(value = "活动id",dataType = "long")
    private Long activityId;


    @ApiModelProperty("分页条件查询对象")
    private PageConditions conditions;


    @ApiModelProperty("页码")
    private Integer pageNum;

    @ApiModelProperty("数据条数")
    private Integer pagesize;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public PageConditions getConditions() {
        return conditions;
    }

    public void setConditions(PageConditions conditions) {
        this.conditions = conditions;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }
}
