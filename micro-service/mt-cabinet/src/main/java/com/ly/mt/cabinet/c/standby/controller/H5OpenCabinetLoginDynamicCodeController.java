package com.ly.mt.cabinet.c.standby.controller;

import com.ly.mt.cabinet.c.code.service.GzgDynamicCodeService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.dict.DynamicCodeType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "动态验证码接口" ,tags = "H5OpenDevice",value = "动态验证码")
@RestController
@RequestMapping("/cabinet/c/code")
public class H5OpenCabinetLoginDynamicCodeController {
    @Resource
    GzgDynamicCodeService service;

    @ApiOperation( value = "发送动态验证到手机" )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/sendDynamicCode")
    public ResponseJson sendDynamicCode(@RequestParam(value = "mobile") String mobile) {

        // 校验参数非空
        if (!StringUtil.isPhoneNumber(mobile)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        String dynamicCodeType = DynamicCodeType.DYNAMIC_CODE_TYPE_CABINET_OPEN_DEVICE_LOGIN.getId();
        return service.sendDynamicCode(mobile, dynamicCodeType);
    }
}