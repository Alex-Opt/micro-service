package com.ly.mt.cabinet.c.code.controller;

import com.ly.mt.cabinet.c.code.service.GzgDynamicCodeService;
import com.ly.mt.core.redis.dict.DynamicCodeType;
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

@Api(description = "动态验证码接口" ,tags = "code",value = "动态验证码")
@RestController
@RequestMapping("/cabinet/c/gzg/code")
public class GzgDynamicCodeController {
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
        String dynamicCodeType = DynamicCodeType.DYNAMIC_CODE_TYPE_MALL_H5_LOGIN.getId();
        return service.sendDynamicCode(mobile, dynamicCodeType);
    }
}