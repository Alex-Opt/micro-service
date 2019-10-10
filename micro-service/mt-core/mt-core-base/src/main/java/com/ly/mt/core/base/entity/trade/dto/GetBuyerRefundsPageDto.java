package com.ly.mt.core.base.entity.trade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @author 484876123@qq.com
 */
/** @deprecated */
@ApiModel( value = "根据买家Id查询退货单列表" ,description = "根据买家Id查询退货单列表")
public class GetBuyerRefundsPageDto {

    @ApiModelProperty(value = "买家id" ,name="buyerId" ,required=true)
    private String buyerId;
    @ApiModelProperty(value = "页号" ,name="page" ,required=true)
    private Integer page=1;
    @ApiModelProperty(value = "每页条数" ,name="rows" ,required=true)
    private Integer rows=10;

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
    public String getBuyerId() {
        return buyerId;
    }

    public void setPage(Integer page){
        this.page=page;
    }
    public Integer getPage(){
        return page;
    }
    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }


}
