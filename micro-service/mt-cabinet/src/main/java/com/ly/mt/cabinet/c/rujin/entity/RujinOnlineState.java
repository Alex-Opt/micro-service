package com.ly.mt.cabinet.c.rujin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RujinOnlineState {
    @ApiModelProperty("终端ID")
    private String tid;
    @ApiModelProperty("终端状态  1：在线  2：离线")
    private String state;
    @ApiModelProperty("签名 md5(终端ID+state+skey)")
    private String token;


    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "RujinOnlineState{" +
                "tid='" + tid + '\'' +
                ", state='" + state + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
