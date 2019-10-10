package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgStoreInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("格子柜唯一标识码code值")
    private String code;
    @ApiModelProperty("方案名称")
    private String plan;
    @ApiModelProperty("格子柜内格子号码")
    private String num;
    @ApiModelProperty("格子产品spu的id值")
    private String spu_id;
    @ApiModelProperty("格子产品sku的id值")
    private String sku_id;
    @ApiModelProperty("格子产品价格")
    private String price;
    @ApiModelProperty("产品文案")
    private String copywriting;
    @ApiModelProperty("产品配图")
    private String pic;
    @ApiModelProperty("产品规格")
    private String standards;
    @ApiModelProperty("格子号内商品数量")
    private String store_num;
    @ApiModelProperty("格子状态，0停用，1正常")
    private String state;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCopywriting() {
        return copywriting;
    }

    public void setCopywriting(String copywriting) {
        this.copywriting = copywriting;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getStore_num() {
        return store_num;
    }

    public void setStore_num(String store_num) {
        this.store_num = store_num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}