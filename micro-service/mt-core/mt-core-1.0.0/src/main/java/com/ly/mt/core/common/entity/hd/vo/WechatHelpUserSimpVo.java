package com.ly.mt.core.common.entity.hd.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * 微信助力用户简单vo
 */
@Data
public class WechatHelpUserSimpVo extends WechatSimpMsgVo {

    private String shareUrl;

    private String shareOssUrl;

    private String taskStatus;

}
