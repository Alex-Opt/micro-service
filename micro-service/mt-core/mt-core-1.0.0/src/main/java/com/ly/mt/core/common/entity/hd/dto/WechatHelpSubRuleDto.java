package com.ly.mt.core.common.entity.hd.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 助力+子任务
 */
@Data
public class WechatHelpSubRuleDto implements Serializable {


    @JsonSerialize(using = ToStringSerializer.class)
    private Long taskId;
    /**
     * 主任务名称
     */
    private String taskName;


    /**
     * 任务名称及其细节
     */
    private List<WechatHelpSubContentDto> taskContents;

}
