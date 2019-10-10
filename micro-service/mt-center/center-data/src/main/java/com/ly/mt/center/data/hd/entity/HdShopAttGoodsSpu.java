package com.ly.mt.center.data.hd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HdShopAttGoodsSpu {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("门店活动信息主键")
    private String shop_att_detail_id;
    @ApiModelProperty("商品sku id")
    private String goods_spu_info_id;
    @ApiModelProperty("上下架状态  0下架 1上架")
    private String goods_status;
    @ApiModelProperty("入库时间")
    private String create_time;
    @ApiModelProperty("更新时间时间")
    private String update_time;
    @ApiModelProperty("门店id")
    private String shop_id;
    @ApiModelProperty("活动id")
    private String activity_id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_att_detail_id() {
        return shop_att_detail_id;
    }

    public void setShop_att_detail_id(String shop_att_detail_id) {
        this.shop_att_detail_id = shop_att_detail_id;
    }

    public String getGoods_spu_info_id() {
        return goods_spu_info_id;
    }

    public void setGoods_spu_info_id(String goods_spu_info_id) {
        this.goods_spu_info_id = goods_spu_info_id;
    }

    public String getGoods_status() {
        return goods_status;
    }

    public void setGoods_status(String goods_status) {
        this.goods_status = goods_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

}