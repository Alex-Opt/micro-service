package com.ly.mt.center.data.cabinet.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 格子柜B端补货：消息表
 *
 * @author wanghongliang
 * @date 2019-09-16
 */
@ApiModel
public class CabinetMessage {

    @ApiModelProperty(value = "主键id", required = true)
    private String message_id;

    @ApiModelProperty(value = "用户id", required = true)
    private String user_id;

    @ApiModelProperty(value = "消息类型 0:格子柜补货消息 1:展柜补货消息", required = true)
    private String message_type;

    @ApiModelProperty(value = "是否已读 0:未读 1:已读", required = true)
    private String read_status;

    @ApiModelProperty(value = "消息标题", required = true)
    private String message_title;

    @ApiModelProperty(value = "消息内容", required = true)
    private String message_content;

    @ApiModelProperty(value = "创建时间", required = true)
    private String create_time;

    @ApiModelProperty(value = "修改时间", required = true)
    private String modify_time;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getRead_status() {
        return read_status;
    }

    public void setRead_status(String read_status) {
        this.read_status = read_status;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
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
