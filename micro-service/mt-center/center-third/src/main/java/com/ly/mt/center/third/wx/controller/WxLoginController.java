package com.ly.mt.center.third.wx.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "微信——登陆服务")
@RestController
@RequestMapping("/center/third/")
public class WxLoginController {

    @ApiOperation(value = "微信小程序登录凭证校验接口，前端通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "登录时获取的 code", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wx/applet/auth/code2Session")
    public ResponseJson appletAuthCode2Session() {
        return null;
    }

}
