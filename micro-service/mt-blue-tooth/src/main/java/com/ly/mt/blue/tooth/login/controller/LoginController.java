package com.ly.mt.blue.tooth.login.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.login.service.LoginService;
import com.ly.mt.blue.tooth.login.vo.LoginResultVo;
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

@Api(description = "用户登录接口")
@RestController
@RequestMapping("/bluetooth/login")
public class LoginController {
    private final static Logger Logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    LoginService userService;


    @ApiOperation("登出")
    @ApiResponses({
            @ApiResponse(code = 0, message = "登出成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @CustomLog
    @PostMapping("/out")
    public ResponseJson out() {
        try {
            return userService.loginOut();
        } catch (Exception e) {
            Logger.error("蓝牙APP-登出出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("更新用户首次登陆状态")
    @ApiResponses({
            @ApiResponse(code = 0, message = "登出成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @CustomLog
    @PostMapping("/updateFirstLogin")
    public ResponseJson updateFirstLogin() {
        try {
            return userService.updateFirstLogin();
        } catch (Exception e) {
            Logger.error("蓝牙APP-更新蓝牙用户首次登陆状态:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "用户账号密码登录",notes = "登录成功后返回{\n" +
            "    \"code\": \"0\",\n" +
            "    \"msg\": \"操作成功\",\n" +
            "    \"result\": \"{\\n\\t\\\"token\\\":\\\"8bad218dad642d4ef41804cfc272637f\\\",\\n\\t\\\"userId\\\":\\\"603244621316968448\\\"\\n}\"\n" +
            "}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户名/手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @CustomLog
    @PostMapping("/nameLogin")
    public ResponseJson<LoginResultVo> nameLogin(@RequestParam(value = "loginName") String loginName,
                                                 @RequestParam(value = "password") String password) {
        // 校验参数
        if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"用户名密码不能为空");
        }
        try {
            return userService.nameLogin(loginName,password);
        } catch (Exception e) {
            Logger.error("蓝牙APP-用户账号密码登录出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("手机号/短信验证码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @CustomLog
    @PostMapping("/dynamicCodeLogin")
    public ResponseJson<LoginResultVo> dynamicCodeLogin(@RequestParam(value = "mobile") String mobile,
                                  @RequestParam(value = "dynamicCode") String dynamicCode) {
        // 校验参数
        if (StringUtil.isEmpty(mobile) || StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"手机号/验证码不能为空");
        }
        try {
            return userService.dynamicCodeLogin(mobile,dynamicCode);
        } catch (Exception e) {
            Logger.error("蓝牙APP-手机号/短信验证码登录出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}