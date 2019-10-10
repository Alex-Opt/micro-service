package com.ly.mt.center.third.fn.controller;

import com.ly.mt.center.third.fn.entity.FnCarrierQuery;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(description = "蜂鸟——骑手接口")
@RestController
@RequestMapping("/center/third/fn/carrier")
public class FnCarrierController {
    @ApiOperation("查询骑手位置接口")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/query")
    public ResponseJson query(@RequestBody FnCarrierQuery fnCarrierQuery) {
        return null;
    }
}