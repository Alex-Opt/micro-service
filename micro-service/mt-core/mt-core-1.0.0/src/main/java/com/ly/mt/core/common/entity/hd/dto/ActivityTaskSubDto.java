package com.ly.mt.core.common.entity.hd.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityTaskSubDto implements Serializable {

    private Long id;

    private Long taskId;

    private Long activityId;

    private String subName;

    private String subMsg;

    private String subRule;

    /**
     * 转移规则
     * @param subRule
     * @return
     */
    public Object getObjRule(String subRule){

        return null;
    }

}
