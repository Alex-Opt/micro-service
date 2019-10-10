package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserNotices {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("发送者{0:系统,非0:用户}")
    private String from_id;
    @ApiModelProperty("接收者{-1:所有人,0:多人,大于0:指定单用户}")
    private String to_id;
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("内容")
    private String content;
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

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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