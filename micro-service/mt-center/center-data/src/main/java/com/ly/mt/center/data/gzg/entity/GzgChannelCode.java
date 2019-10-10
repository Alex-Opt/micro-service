package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgChannelCode {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("条形码")
    private String bar_code;
    @ApiModelProperty("商品状态0未出售1已出售")
    private String state;
    @ApiModelProperty("")
    private String six_nine_code;
    @ApiModelProperty("")
    private String sku_code;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSix_nine_code() {
        return six_nine_code;
    }

    public void setSix_nine_code(String six_nine_code) {
        this.six_nine_code = six_nine_code;
    }

    public String getSku_code() {
        return sku_code;
    }

    public void setSku_code(String sku_code) {
        this.sku_code = sku_code;
    }

}