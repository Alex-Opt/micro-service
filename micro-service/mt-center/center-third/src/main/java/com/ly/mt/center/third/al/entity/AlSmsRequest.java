package com.ly.mt.center.third.al.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("阿里发送短信实体")
public class AlSmsRequest {
    @ApiModelProperty(value = "手机号,群发用英文逗号隔开", required = true)
    private String phone;
    @ApiModelProperty(value = "阿里短信模版code", required = true)
    private String templateCode;
    @ApiModelProperty(value = "阿里短信签名", required = true)
    private String signName;
    @ApiModelProperty(value = "阿里短信模版占位符替换参数,示例:{\"code\":\"1111\"}", required = true)
    private String templateParam;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }
}