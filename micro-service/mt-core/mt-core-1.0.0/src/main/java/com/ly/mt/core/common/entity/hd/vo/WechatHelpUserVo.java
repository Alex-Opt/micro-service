package com.ly.mt.core.common.entity.hd.vo;

import com.ly.mt.core.common.entity.hd.dto.WechatHelpSubRuleDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description
 * 微信助力活动vo
 * 附带主用户信息
 * 附带助力人员微信信息
 * 附带规则进度信息
 * @author panjingtian
 * @date 2019/8/15 2:23 AM
 */
@Data
public class WechatHelpUserVo implements Serializable {

    /**
     * 助力用户信息
     */
    private WechatHelpUserSimpVo userMsg;

    /**
     * 助力用户集合
     */
    private List<WechatSimpMsgVo> wechatSubs;

    /**
     * 任务状态
     */
    private WechatHelpSubRuleDto task;

}

