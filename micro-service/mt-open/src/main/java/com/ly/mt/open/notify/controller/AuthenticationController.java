package com.ly.mt.open.notify.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.open.notify.service.AuthenticationService;
import com.ly.mt.open.notify.service.ProvideApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "认证")
@RestController
@RequestMapping("/open/authentication")
public class AuthenticationController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Resource
    AuthenticationService service;

    @ApiOperation("第三方调用认证接口")
    @PostMapping("/login")
    public ResponseJson login(HttpServletRequest request) {
        try {
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            if (StringUtil.isEmpty(account)||StringUtil.isEmpty(password)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误");
            }
            return service.login(account,password,request);
        } catch (Exception e) {
            LOGGER.error("第三方调用认证接口,login出错:", e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "调用发生异常");
        }
    }
}
