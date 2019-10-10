package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserTask {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("任务名称")
    private String task_name;
    @ApiModelProperty("任务说明")
    private String task_desc;
    @ApiModelProperty("任务类别 1：新手任务，2：其他任务")
    private String task_type;
    @ApiModelProperty("任务工作 1：下单，2：邀请，3：实名认证， 4：关注公众号，5：入驻MOTI")
    private String task_work;
    @ApiModelProperty("该任务是否取消 0：取消，1：正常")
    private String task_yn;
    @ApiModelProperty("应用类别 1：商城，2：到家C端，3：到家B端， 4：格子柜C端，5：格子柜B端，6：蓝牙APP")
    private String app_type;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTask_work() {
        return task_work;
    }

    public void setTask_work(String task_work) {
        this.task_work = task_work;
    }

    public String getTask_yn() {
        return task_yn;
    }

    public void setTask_yn(String task_yn) {
        this.task_yn = task_yn;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}