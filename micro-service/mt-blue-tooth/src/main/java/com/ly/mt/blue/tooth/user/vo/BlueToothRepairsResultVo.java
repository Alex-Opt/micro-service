package com.ly.mt.blue.tooth.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @program: mt-blue-tooth
* @description: 用户一键换新结果对象
* @author: wanghongliang
* @create: 2019/8/27 15:32
**/
@ApiModel(value="一键换新结果对象")
public class BlueToothRepairsResultVo {
    @ApiModelProperty("报修Id")
    private String id;
    @ApiModelProperty("保修状态 0:已填写 1:已关闭 2:待审核 3:已收货")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}