package com.ly.mt.center.data.lode.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LodeRunnerClues {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("线索类型")
    private String type;
    @ApiModelProperty("门店名称")
    private String shop_name;
    @ApiModelProperty("门店位置")
    private String shop_address;
    @ApiModelProperty("联系人")
    private String contacter;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("社会角色")
    private String social_role;
    @ApiModelProperty("关系")
    private String relation;
    @ApiModelProperty("带来")
    private String bring;
    @ApiModelProperty("合作方式")
    private String cooperation_mode;
    @ApiModelProperty("合作描述")
    private String cooperation_description;
    @ApiModelProperty("意向")
    private String intention;
    @ApiModelProperty("是否匿名")
    private String is_anonymous;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String modify_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSocial_role() {
        return social_role;
    }

    public void setSocial_role(String social_role) {
        this.social_role = social_role;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getBring() {
        return bring;
    }

    public void setBring(String bring) {
        this.bring = bring;
    }

    public String getCooperation_mode() {
        return cooperation_mode;
    }

    public void setCooperation_mode(String cooperation_mode) {
        this.cooperation_mode = cooperation_mode;
    }

    public String getCooperation_description() {
        return cooperation_description;
    }

    public void setCooperation_description(String cooperation_description) {
        this.cooperation_description = cooperation_description;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public String getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(String is_anonymous) {
        this.is_anonymous = is_anonymous;
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