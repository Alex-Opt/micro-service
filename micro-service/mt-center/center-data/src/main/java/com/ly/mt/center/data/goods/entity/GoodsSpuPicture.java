package com.ly.mt.center.data.goods.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsSpuPicture {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商品SPU编号")
    private String spu_id;
    @ApiModelProperty("图片url")
    private String picture_url;
    @ApiModelProperty("排序号,排序号最小的作为主图，从1开始")
    private String sort_number;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getSort_number() {
        return sort_number;
    }

    public void setSort_number(String sort_number) {
        this.sort_number = sort_number;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}