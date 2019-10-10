package com.ly.mt.activity.advertisement.controller;

import com.ly.mt.activity.advertisement.service.DynamicCodeService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "动态验证码接口")
@RestController
@RequestMapping("/mall/h5/code")
public class DynamicCodeController {
    @Resource
    DynamicCodeService service;

    @ApiOperation(
            value = "发送动态验证到手机",
            notes = "dynamicCodeType：  \n" +
                    "101——H5商城用户注册  \n" +
                    "102——H5商城用户登录  \n" +
                    "103——H5商城修改密码  \n" +
                    "104——H5商城绑定手机  \n" +
                    "```  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "codeType", value = "验证码类型(101:用户注册；102:手机号登录；103:修改密码；104:绑定手机)", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/sendDynamicCode")
    public ResponseJson sendDynamicCode(@RequestParam(value = "mobile") String mobile,
                                        @RequestParam(value = "codeType") String dynamicCodeType) {
        // 校验参数非空
        if (!StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCodeType)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.sendDynamicCode(mobile, dynamicCodeType);
    }
}