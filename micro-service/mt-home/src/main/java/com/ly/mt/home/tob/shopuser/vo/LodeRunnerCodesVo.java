package com.ly.mt.home.tob.shopuser.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LodeRunnerCodesVo {
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("店铺编号")
    private String shop_id;
    @ApiModelProperty("专属邀请码")
    private String code;
    @ApiModelProperty("邀请成功次数")
    private String nums;
    @ApiModelProperty("")
    private String create_time;
    @ApiModelProperty("")
    private String modify_time;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
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
