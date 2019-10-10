package com.ly.mt.core.common.entity.hd.Rule;

import lombok.Data;

/**
 * @description
 * 助力数量规则
 *
 *  ruleType
 * @author panjingtian
 * @date 2019/8/14 11:19 PM
 */
@Data
public class WechatHelpCountRule extends BaseWechatHelpRule{


    /**
     * 条件数量作为对比
     */
    private Integer countCondition;

    /**
     * 实际计数
     */
    private Integer countData;


}
