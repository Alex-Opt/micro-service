package com.ly.mt.core.common.entity.hd.Rule;


import lombok.Data;

/**
 * 基础规则
 */
@Data
public class BaseWechatHelpRule {

    /**
     * 规则type
     */
    private String ruleType;

    /**
     * 状态 0未完成 1完成
     */
    private String status;

}
