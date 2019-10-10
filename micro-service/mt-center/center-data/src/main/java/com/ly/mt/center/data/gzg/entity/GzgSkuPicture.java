package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgSkuPicture {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商品SKU编号")
    private String sku_id;
    @ApiModelProperty("商品bar_code编号--条形码")
    private String bar_code;
    @ApiModelProperty("大图片url")
    private String big_pic_url;
    @ApiModelProperty("小图片url")
    private String small_pic_url;
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

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getBig_pic_url() {
        return big_pic_url;
    }

    public void setBig_pic_url(String big_pic_url) {
        this.big_pic_url = big_pic_url;
    }

    public String getSmall_pic_url() {
        return small_pic_url;
    }

    public void setSmall_pic_url(String small_pic_url) {
        this.small_pic_url = small_pic_url;
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