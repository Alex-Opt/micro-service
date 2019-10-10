package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CabinetContractReqVo {
    private String id;
    private String take_effect_datetime;
    private String lose_efficacy_datetime;
    private String contract_pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContract_pic() {
        return contract_pic;
    }

    public void setContract_pic(String contract_pic) {
        this.contract_pic = contract_pic;
    }

    public String getTake_effect_datetime() {
        return take_effect_datetime;
    }

    public void setTake_effect_datetime(String take_effect_datetime) {
        this.take_effect_datetime = take_effect_datetime;
    }

    public String getLose_efficacy_datetime() {
        return lose_efficacy_datetime;
    }

    public void setLose_efficacy_datetime(String lose_efficacy_datetime) {
        this.lose_efficacy_datetime = lose_efficacy_datetime;
    }
}
