package com.ly.mt.core.common.entity.hd.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * 活动代理商dto
 */
public class HdoperatorDto implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String operatorName;


    public HdoperatorDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
