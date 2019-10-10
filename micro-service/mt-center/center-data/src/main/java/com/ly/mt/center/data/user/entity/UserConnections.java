package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserConnections {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("姓名/店名")
    private String name;
    @ApiModelProperty("职位/门店地址")
    private String position;
    @ApiModelProperty("业务")
    private String work;
    @ApiModelProperty("优势")
    private String advantage;
    @ApiModelProperty("与我关系")
    private String relation;
    @ApiModelProperty("门店照片")
    private String photo;
    @ApiModelProperty("是否匿名")
    private String is_anonymous;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("创建时间")
    private String create_time;


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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(String is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}