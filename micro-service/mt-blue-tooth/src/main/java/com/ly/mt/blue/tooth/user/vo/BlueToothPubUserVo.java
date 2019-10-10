package com.ly.mt.blue.tooth.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description user
 * @Author whl
 */
@ApiModel(value="用户公用对象")
public class BlueToothPubUserVo {
    @ApiModelProperty(value = "用户id", required = true)
    private String id;
    /**
     * 十六进制userId
     */
    @ApiModelProperty(value = "用户密码", required = true)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}