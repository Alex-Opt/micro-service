package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("短信验证码请求")
public class PhoneCodeRequestBody extends BaseWechatHelpReauesyBody {

    @ApiModelProperty("phone")
    private  String phone;

    @ApiModelProperty("code")
    private String code;

}
