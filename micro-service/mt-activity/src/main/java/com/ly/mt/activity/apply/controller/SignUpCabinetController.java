package com.ly.mt.activity.apply.controller;

import com.ly.mt.activity.apply.service.SignUpCabinetService;
import com.ly.mt.activity.apply.vo.SignUpCabinet;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "格子柜入驻报名接口")
@RestController
@RequestMapping("/activity/apply/sign/up")
public class SignUpCabinetController {
    @Resource
    SignUpCabinetService service;

    @ApiOperation("发送动态验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    @PostMapping("/sendDynamicCode")
    public ResponseJson sendDynamicCode(@RequestParam(value = "mobile") String mobile) {
        // 参数校验
        if (!StringUtil.isPhoneNumber(mobile)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.sendDynamicCode(mobile);
    }


    @ApiOperation("保存报名信息")
    @ApiResponses({
            @ApiResponse(code = 0, message = "报名成功!"),
            @ApiResponse(code = 1, message = "报名失败!")
    })
    @PostMapping("/saveSignUpInfo")
    public ResponseJson saveSignUpInfo(@RequestBody SignUpCabinet cabinet) {
        // 参数校验
        if (StringUtil.isEmpty(cabinet.getShopName())
                || StringUtil.isEmpty(cabinet.getAreaName())
                || StringUtil.isEmpty(cabinet.getCityName())
                || StringUtil.isEmpty(cabinet.getProvinceName())
                || StringUtil.isEmpty(cabinet.getUserName())
                || StringUtil.isEmpty(cabinet.getDynamicCode())
                || !StringUtil.isPhoneNumber(cabinet.getMobile())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.saveSignUpInfo(cabinet);
    }
}