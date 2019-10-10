package com.ly.mt.cabinet.b.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺信息集合
 */
@ApiModel("店铺信息")
public class ShopRespVo {


    @ApiModelProperty("店铺id")
    private String id;

    @ApiModelProperty("店铺名称")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
