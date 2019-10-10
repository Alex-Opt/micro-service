package com.ly.mt.core.data.cabinet.entity;

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
    private String userId;

    @ApiModelProperty(value = "补货用户的微信open_id,用户提现到微信零钱用", required = true)
    private String wxOpenId;

    @ApiModelProperty(value = "累计奖励金额", required = true)
    private String cumulativeRewardAmount;

    @ApiModelProperty(value = "可提现金额", required = true)
    private String mayWithdrawalAmount;

    @ApiModelProperty(value = "待结算金额", required = true)
    private String pendingSettlementAmount;

    @ApiModelProperty(value = "累计提现金额（区分累计奖励金额：奖励不一定都提现）", required = true)
    private String cumulativeWithdrawalAmount;

    @ApiModelProperty(value = "奖励类型 1-补货奖励  2-分成奖励", required = true)
    private String type;

    @ApiModelProperty(value = "版本号", required = true)
    private String version;

    @ApiModelProperty(value = "创建时间", required = true)
    private String createTime;

    @ApiModelProperty(value = "修改时间", required = true)
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getCumulativeRewardAmount() {
        return cumulativeRewardAmount;
    }

    public void setCumulativeRewardAmount(String cumulativeRewardAmount) {
        this.cumulativeRewardAmount = cumulativeRewardAmount;
    }

    public String getMayWithdrawalAmount() {
        return mayWithdrawalAmount;
    }

    public void setMayWithdrawalAmount(String mayWithdrawalAmount) {
        this.mayWithdrawalAmount = mayWithdrawalAmount;
    }

    public String getPendingSettlementAmount() {
        return pendingSettlementAmount;
    }

    public void setPendingSettlementAmount(String pendingSettlementAmount) {
        this.pendingSettlementAmount = pendingSettlementAmount;
    }

    public String getCumulativeWithdrawalAmount() {
        return cumulativeWithdrawalAmount;
    }

    public void setCumulativeWithdrawalAmount(String cumulativeWithdrawalAmount) {
        this.cumulativeWithdrawalAmount = cumulativeWithdrawalAmount;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
