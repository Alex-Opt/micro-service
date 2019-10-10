package com.ly.mt.cabinet.b.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description user
 * @Author whl
 */
@ApiModel(value="格子柜用户信息")
public class CabInetBUserVo {
    @ApiModelProperty(value = "用户id", required = true)
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}