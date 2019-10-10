package com.ly.mt.center.third.userCertificate.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户认证接口
 */
@Api(description = "用户——实名认证服务")
@RestController
@RequestMapping("/center/third/")
public class UserCertificationController {
    @ApiOperation(value = "用户身份证信息扫描")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "images0", value = "身份证正面照片", paramType = "query", required = true),
            @ApiImplicitParam(name = "images1", value = "身份证反面照片 code", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/idcardscan")
    public ResponseJson idcardscan() {
        return null;
    }


    @ApiOperation(value = "用户三要素检测")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", required = true),
            @ApiImplicitParam(name = "idcard", value = "身份证号码", paramType = "query", required = true),
            @ApiImplicitParam(name = "realName", value = "真实姓名", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/therelecheck")
    public ResponseJson therelecheck() {
        return null;
    }
}
