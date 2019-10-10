package com.ly.mt.center.data.share.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShareGoods {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商品SPU")
    private String spu_id;
    @ApiModelProperty("商品SKU")
    private String sku_id;
    @ApiModelProperty("分享APP 1：商城，2：到家C端，3：到家B端， 4：格子柜C端，5：格子柜B端，6：蓝牙APP")
    private String share_type;
    @ApiModelProperty("状态 1：正常， 2：取消")
    private String share_status;
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

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getShare_type() {
        return share_type;
    }

    public void setShare_type(String share_type) {
        this.share_type = share_type;
    }

    public String getShare_status() {
        return share_status;
    }

    public void setShare_status(String share_status) {
        this.share_status = share_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}