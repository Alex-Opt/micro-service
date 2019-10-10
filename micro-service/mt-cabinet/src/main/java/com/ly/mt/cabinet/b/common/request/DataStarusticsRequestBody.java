package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据统计请求体
 */
@ApiModel("数据统计请求对象")
public class DataStarusticsRequestBody extends  BasePageRequestVo{

    @ApiModelProperty("开始时间，2019-5-1")
    private String start_time;

    @ApiModelProperty("开始时间，2019-5-2")
    private String end_time;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }


}
