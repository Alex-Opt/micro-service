package com.ly.mt.core.common.entity.hd.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WechatHelpMasterMsgDto implements Serializable {

    private Long id;

    private Long wechatId;

    private Long wechatMasterId;

    private Long taskId;

    private String taskStatus;

    private Long activityId;

    private String shareUrl;

    private String shareOssUrl;

    private String wechatNickname;

    private String wechatHeadeImgUrl;

}
