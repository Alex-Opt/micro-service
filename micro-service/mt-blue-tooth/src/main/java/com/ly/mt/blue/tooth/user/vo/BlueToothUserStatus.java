package com.ly.mt.blue.tooth.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description user
 * @Author whl
 */
@ApiModel(value="用户状态")
public class BlueToothUserStatus {
    /**
     * @Description 用户状态
     */
    @ApiModelProperty(value = "用户状态 0:未注册 1:已注册", required = true)
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}