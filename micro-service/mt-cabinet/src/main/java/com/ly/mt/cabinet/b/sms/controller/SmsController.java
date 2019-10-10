package com.ly.mt.cabinet.b.sms.controller;


import com.ly.mt.cabinet.b.common.annotation.CustomLog;
import com.ly.mt.cabinet.b.sms.service.SmsService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "CABINETB端短信发送接口")
@RestController
@RequestMapping("/cabinet/b/sms")
public class SmsController {
    private final static Logger Logger = LoggerFactory.getLogger(SmsController.class);
    @Resource
    SmsService smsService;

    @ApiOperation(value = "发送短信" ,notes = "操作成功后返回code:0 \n 短信验证码类型 1:登录验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "smsType", value = "短信验证码类型", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "发送成功!"),
            @ApiResponse(code = 1, message = "发送失败!")
    })
    @CustomLog
    @PostMapping("/sendSms")
    public ResponseJson sendSms(@RequestParam(value = "mobile") String mobile,
                                  @RequestParam(value = "smsType") String smsType) {
        // 校验参数
        if (StringUtil.isEmpty(mobile) || StringUtil.isEmpty(smsType)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"手机号/验证码类型不能为空");
        }
        try {
            return smsService.sendSms(mobile,smsType);
        } catch (Exception e) {
            Logger.error("CABINET-APP-发送短信出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}