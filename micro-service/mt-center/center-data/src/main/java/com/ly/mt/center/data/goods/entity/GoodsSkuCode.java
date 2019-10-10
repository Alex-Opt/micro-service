package com.ly.mt.center.data.goods.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsSkuCode {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商品SKU")
    private String sku_id;
    @ApiModelProperty("商品SKU条形码")
    private String bar_code;
    @ApiModelProperty("父标识码")
    private String parent_code;
    @ApiModelProperty("唯一标识码")
    private String code;
    @ApiModelProperty("验证次数")
    private String check_num;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String modify_time;


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

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCheck_num() {
        return check_num;
    }

    public void setCheck_num(String check_num) {
        this.check_num = check_num;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

}