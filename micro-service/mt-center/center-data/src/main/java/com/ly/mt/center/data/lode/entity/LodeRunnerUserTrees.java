package com.ly.mt.center.data.lode.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LodeRunnerUserTrees {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("淘金人编号")
    private String user_id;
    @ApiModelProperty("父级淘金人编号，无父级：-1")
    private String father_id;
    @ApiModelProperty("顶层淘金人编号（往上5层或不到5级的顶层user_id）")
    private String top_user_id;
    @ApiModelProperty("6层内的级别")
    private String user_level;
    @ApiModelProperty("用户来源（1：邀请，2：通讯录，3：流量，4：正常注册）")
    private String user_source;
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

    public String getFather_id() {
        return father_id;
    }

    public void setFather_id(String father_id) {
        this.father_id = father_id;
    }

    public String getTop_user_id() {
        return top_user_id;
    }

    public void setTop_user_id(String top_user_id) {
        this.top_user_id = top_user_id;
    }

    public String getUser_level() {
        return user_level;
    }

    public void setUser_level(String user_level) {
        this.user_level = user_level;
    }

    public String getUser_source() {
        return user_source;
    }

    public void setUser_source(String user_source) {
        this.user_source = user_source;
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