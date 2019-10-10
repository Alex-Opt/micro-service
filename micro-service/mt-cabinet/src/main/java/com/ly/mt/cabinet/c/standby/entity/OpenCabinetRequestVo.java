package com.ly.mt.cabinet.c.standby.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("维护人员打开柜门入参")
public class OpenCabinetRequestVo {

    @ApiModelProperty("格子柜名称")
    private String tname;
    @ApiModelProperty("格子柜货道")
    private String cabinet_no;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getCabinet_no() {
        return cabinet_no;
    }

    public void setCabinet_no(String cabinet_no) {
        this.cabinet_no = cabinet_no;
    }
}
