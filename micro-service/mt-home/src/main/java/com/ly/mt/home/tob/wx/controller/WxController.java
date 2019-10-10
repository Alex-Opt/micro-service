package com.ly.mt.home.tob.wx.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.home.tob.wx.service.WxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公众号登录
 *
 * @author: linan
 * @date: 2019/9/4
 */
@Api(tags = "公众号静默登录")
@RestController
@RequestMapping("/home/wx/login")
public class WxController {
    @Resource
    WxService wxService;

    @ApiOperation("b端手机号密码登录")
    @PostMapping("/name")
    public ResponseJson nameLogin(@ApiParam(value = "用户手机号", required = true) @RequestParam(value = "mobile") String mobile,
                                  @ApiParam(value = "密码", required = true) @RequestParam(value = "password") String password,
                                  @ApiParam(value = "微信授权码", required = true) @RequestParam(value = "code") String code) {

        return wxService.nameLogin(mobile, password, code);
    }

    @ApiOperation("b端手机号动态码登录")
    @PostMapping("/mobile")
    public ResponseJson mobileLogin(@ApiParam(value = "用户手机号", required = true) @RequestParam(value = "mobile") String mobile,
                                    @ApiParam(value = "动态验证码", required = true) @RequestParam(value = "dynamicCode") String dynamicCode,
                                    @ApiParam(value = "微信授权码", required = true) @RequestParam(value = "code") String code) {

        return wxService.mobileLogin(mobile, dynamicCode, code);
    }

    @ApiOperation("静默授权登录")
    @PostMapping("/token")
    public ResponseJson tokenLogin(@ApiParam(value = "wx access_token", required = true) @RequestParam(value = "token") String token,
                                   @ApiParam(value = "wx refresh_token", required = true) @RequestParam(value = "refreshToken") String refreshToken,
                                   @ApiParam(value = "wx open_id", required = true) @RequestParam(value = "openId") String openId) {

        return wxService.loginByToken(token, refreshToken, openId);
    }
}
