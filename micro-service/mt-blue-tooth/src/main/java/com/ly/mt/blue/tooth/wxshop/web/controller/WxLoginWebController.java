package com.ly.mt.blue.tooth.wxshop.web.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.login.service.LoginService;
import com.ly.mt.blue.tooth.login.vo.LoginResultVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "微信店铺Web后台登录操作接口")
@RestController
@RequestMapping("/bluetooth/web/login")
public class WxLoginWebController {
    private final static Logger Logger = LoggerFactory.getLogger(WxLoginWebController.class);

    @Resource
    LoginService userService;

    @ApiOperation(value = "Web用户账号密码登录",notes = "登录成功后返回{\n" +
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
    @ResponseBody
    public ResponseJson<LoginResultVo> nameLogin(@RequestParam(value = "loginName") String loginName,
                                                 @RequestParam(value = "password") String password) {
        // 校验参数
        if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"用户名密码不能为空");
        }
        try {
            return userService.nameLogin(loginName,password);
        } catch (Exception e) {
            Logger.error("店铺Web-用户账号密码登录出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}