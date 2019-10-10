package com.ly.mt.center.third.wx.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(description = "微信小程序-模版消息发送")
@RestController
@RequestMapping("/center/third/")
public class WxTemplateMessageSendController {
    @ApiOperation(value = "微信小程序：推送模版消息给用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateId", value = "所需下发的模板消息的id", paramType = "query", required = true),
            @ApiImplicitParam(name = "openId", value = "用户的微信openId", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/sendTemplateMessage")
    public ResponseJson sendTemplateMessage(HttpServletRequest request) {
        return null;
    }
}
