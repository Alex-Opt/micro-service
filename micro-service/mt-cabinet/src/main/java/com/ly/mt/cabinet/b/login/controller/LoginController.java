package com.ly.mt.cabinet.b.login.controller;


import com.ly.mt.cabinet.b.login.service.LoginService;
import com.ly.mt.cabinet.b.login.vo.LoginResultVo;
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

@Api(description = "CABINETB端用户登录接口")
@RestController
@RequestMapping("/cabinet/b/login")
public class LoginController {
    private final static Logger Logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    LoginService loginService;

    @ApiOperation("登出")
    @ApiResponses({
            @ApiResponse(code = 0, message = "登出成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/out")
    public ResponseJson out() {
        try {
            return loginService.loginOut();
        } catch (Exception e) {
            Logger.error("CABINET-B-APP-登出出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("手机号/短信验证码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true),
            @ApiImplicitParam(name = "clientId", value = "手机客户端id(用于appPush)", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/dynamicCodeLogin")
    public ResponseJson<LoginResultVo> dynamicCodeLogin(@RequestParam(value = "mobile") String mobile,
                                  @RequestParam(value = "dynamicCode") String dynamicCode, @RequestParam(value = "clientId") String clientId) {
        // 校验参数
        if (StringUtil.isEmpty(mobile) || StringUtil.isEmpty(dynamicCode)||StringUtil.isEmpty(clientId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"手机号/验证码/设备id不能为空");
        }
        try {
            return loginService.dynamicCodeLogin(mobile,dynamicCode,clientId);
        } catch (Exception e) {
            Logger.error("CABINET-B-APP-手机号/短信验证码登录出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}