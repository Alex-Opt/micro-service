
package com.ly.mt.cabinet.b.common.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("提现数据")
public class WithdrawalVO {

    @ApiModelProperty
    private String withdrawalAmount;//提现金额

    @ApiModelProperty
    private String userId; //用户id

    @ApiModelProperty
    private String type; //奖励类型

    public String getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(String withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}