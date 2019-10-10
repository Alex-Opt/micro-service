package com.ly.mt.center.third.al.controller;

import com.ly.mt.center.third.al.entity.AlSmsRequest;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "阿里——SMS服务")
@RestController
@RequestMapping("/center/third/al/sms")
public class AlSmsController {
    @ApiOperation("发送短信接口")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/send")
    public ResponseJson sendSms(@RequestBody AlSmsRequest alSmsRequest) {
        return null;
    }
}