package com.ly.mt.core.common.entity.hd.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @description
 * 微信助力子任务进度及其名称dto
 * @author panjingtian
 * @date 2019/8/15 6:41 AM
 */
@Data
public class WechatHelpSubContentDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long subTaskId;
    private String subTaskName;
    private String content;
    /**
     * 任务状态 0进行中 1完成
     */
    private String status;

}
