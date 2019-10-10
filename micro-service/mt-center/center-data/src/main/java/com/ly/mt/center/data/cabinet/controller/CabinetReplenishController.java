package com.ly.mt.center.data.cabinet.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 格子柜B端补货接口
 *
 * @author zhanglifeng
 * @date 2019-08-28
 */
@Api(description = "格子柜B端补货接口接口")
@RestController
@RequestMapping("/data/center/cabinetReplenish")
public class CabinetReplenishController {

    @ApiOperation(value = "保存 补货单记录")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/insertCabinetReplenish")
    public ResponseJson insertCabinetReplenish(HttpServletRequest request) {
        return null;
    }



}
