package com.ly.mt.cabinet.b.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * request中的token用户信息
 */
public class TokenUserMessage implements Serializable {


    /**
     * user表中的主键id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 手机号
     */
    private String phoneNo;

    public TokenUserMessage() {
    }

    public TokenUserMessage(Long userId, String phoneNo) {
        this.userId = userId;
        this.phoneNo = phoneNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
