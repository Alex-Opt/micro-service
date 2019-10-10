package com.ly.mt.center.third.gy.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.gy.service.GyService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "管易接口")
@RestController
@RequestMapping("/center/third/gy/deliveryInfo")
public class GyController {

    @Resource
    GyService gyService;

    @ApiOperation(
            value = "管易查询接口"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/query")
    public ResponseJson query(@RequestBody JSONObject jsonObject) {
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, gyService.gyDeliverInfo(jsonObject));
    }
}