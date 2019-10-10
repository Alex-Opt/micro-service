package com.ly.mt.center.data.cabinet.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 个人奖励汇总表
 * @author zhanglifeng
 * @date 2019-08-28
 */
@ApiModel
public class CabinetReplenishReward {

    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty(value = "用户id", required = true)
    private String user_id;

    @ApiModelProperty(value = "补货用户的微信open_id,用户提现到微信零钱用", required = true)
    private String wx_open_id;

    @ApiModelProperty(value = "累计奖励金额", required = true)
    private String cumulative_reward_amount;

    @ApiModelProperty(value = "可提现金额", required = true)
    private String may_withdrawal_amount;

    @ApiModelProperty(value = "待结算金额", required = true)
    private String pending_settlement_amount;

    @ApiModelProperty(value = "累计提现金额（区分累计奖励金额：奖励不一定都提现）", required = true)
    private String cumulative_withdrawal_amount;

    @ApiModelProperty(value = "奖励类型 1-补货奖励  2-分成奖励", required = true)
    private String type;

    @ApiModelProperty(value = "版本号", required = true)
    private String version;

    @ApiModelProperty(value = "创建时间", required = true)
    private String create_time;

    @ApiModelProperty(value = "修改时间", required = true)
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWx_open_id() {
        return wx_open_id;
    }

    public void setWx_open_id(String wx_open_id) {
        this.wx_open_id = wx_open_id;
    }

    public String getCumulative_reward_amount() {
        return cumulative_reward_amount;
    }

    public void setCumulative_reward_amount(String cumulative_reward_amount) {
        this.cumulative_reward_amount = cumulative_reward_amount;
    }

    public String getMay_withdrawal_amount() {
        return may_withdrawal_amount;
    }

    public void setMay_withdrawal_amount(String may_withdrawal_amount) {
        this.may_withdrawal_amount = may_withdrawal_amount;
    }

    public String getPending_settlement_amount() {
        return pending_settlement_amount;
    }

    public void setPending_settlement_amount(String pending_settlement_amount) {
        this.pending_settlement_amount = pending_settlement_amount;
    }

    public String getCumulative_withdrawal_amount() {
        return cumulative_withdrawal_amount;
    }

    public void setCumulative_withdrawal_amount(String cumulative_withdrawal_amount) {
        this.cumulative_withdrawal_amount = cumulative_withdrawal_amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
