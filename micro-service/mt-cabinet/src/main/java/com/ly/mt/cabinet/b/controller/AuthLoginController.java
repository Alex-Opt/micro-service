package com.ly.mt.cabinet.b.controller;

import com.ly.mt.cabinet.b.common.annotation.CustomLog;
import com.ly.mt.cabinet.b.common.annotation.MixController;
import com.ly.mt.cabinet.b.common.request.LoginRequestBody;
import com.ly.mt.cabinet.b.common.response.TokenRespVo;
import com.ly.mt.cabinet.b.service.AuthLoginService;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api("认证登陆")
@MixController("/cabinet/b/auth")
public class AuthLoginController {

    private static final Logger log = LoggerFactory.getLogger(AuthLoginController.class);
    @Autowired
    AuthLoginService authLoginService;

    @ApiOperation("手机号登陆")
    @CustomLog
    @PostMapping("/login/phone")
    public ResponseJson<TokenRespVo> loginByPhone(@RequestBody LoginRequestBody body) {
        log.info("AuthLoginController.loginByPhone=登陆,body={}", body);
        // 校验参数
        if (StringUtil.isEmpty(body.getPhone()) || StringUtil.isEmpty(body.getDynamicCode())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"手机号/验证码");
        }
        try {
            return  authLoginService.loginByPhone(body);
        } catch (Exception e) {
            log.error("CABINET-B-APP-手机号/短信验证码登录出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @CustomLog
    @ApiOperation("登出")
    @PostMapping("/login/out")
    public Resp loginOut(@RequestBody String phone) {
        log.info("AuthLoginController.loginOut=登陆,phone={}", phone);
        Resp resp = authLoginService.loginOut(phone);
        return resp;
    }

}
