package com.ly.mt.activity.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class MotiPicCommit {
    @ApiModelProperty
    private String picUrl;
    @ApiModelProperty
    private String weChatHelpId;
}
