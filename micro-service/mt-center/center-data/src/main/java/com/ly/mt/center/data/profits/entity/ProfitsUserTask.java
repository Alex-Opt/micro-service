package com.ly.mt.center.data.profits.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
* @Description: 赚钱-任务表
* @Author:         zhuyh
*/
@ApiModel
public class ProfitsUserTask {

    @ApiModelProperty("任务id")
    private Long id;
    @ApiModelProperty("任务名称")
    private String taskName;
    @ApiModelProperty("任务描述")
    private String taskDesc;

    /**
     * 任务类型
     */
    private Integer taskWork;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Integer getTaskWork() {
        return taskWork;
    }

    public void setTaskWork(Integer taskWork) {
        this.taskWork = taskWork;
    }
}
