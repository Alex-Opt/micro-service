package com.ly.mt.core.common.entity.hd.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 活动主任务dto
 */
@Data
public class ActivityTaskMasterDto implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String taskName;

    private String taskMsg;

    private Long activityId;

    private List<ActivityTaskSubDto> subs;

}
