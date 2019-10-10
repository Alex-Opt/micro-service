package com.ly.mt.center.data.hd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HdShopAttDetail {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("门店信息主键")
    private String shop_id;
    @ApiModelProperty("活动详情主键")
    private String activity_id;
    @ApiModelProperty("活动图片保存地址")
    private String image_url;
    @ApiModelProperty("活动负责人")
    private String activity_manager;
    @ApiModelProperty("负责人手机号")
    private String manager_phone;
    @ApiModelProperty("门店活动有效性(0失效、1有效)")
    private String shop_att_status;
    @ApiModelProperty("入库时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String update_time;
    @ApiModelProperty("限制用户参与次数 0则不限制")
    private String user_join_num;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getActivity_manager() {
        return activity_manager;
    }

    public void setActivity_manager(String activity_manager) {
        this.activity_manager = activity_manager;
    }

    public String getManager_phone() {
        return manager_phone;
    }

    public void setManager_phone(String manager_phone) {
        this.manager_phone = manager_phone;
    }

    public String getShop_att_status() {
        return shop_att_status;
    }

    public void setShop_att_status(String shop_att_status) {
        this.shop_att_status = shop_att_status;
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

    public String getUser_join_num() {
        return user_join_num;
    }

    public void setUser_join_num(String user_join_num) {
        this.user_join_num = user_join_num;
    }

}