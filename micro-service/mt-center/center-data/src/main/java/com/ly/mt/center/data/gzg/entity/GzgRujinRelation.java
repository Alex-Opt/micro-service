package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgRujinRelation {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("公司自己对格子柜的编号")
    private String name;
    @ApiModelProperty("如金服务器对应的柜子id（终端ID）")
    private String tid;
    @ApiModelProperty("终端编号")
    private String tname;

    @ApiModelProperty("货柜编号（1-99）")
    private String hg;
    @ApiModelProperty("货道总数（1-99）")
    private String hd;
    @ApiModelProperty("如金柜子配货方案名称")
    private String plan_name;
    @ApiModelProperty("创建时间")
    private String create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getHg() {
        return hg;
    }

    public void setHg(String hg) {
        this.hg = hg;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}