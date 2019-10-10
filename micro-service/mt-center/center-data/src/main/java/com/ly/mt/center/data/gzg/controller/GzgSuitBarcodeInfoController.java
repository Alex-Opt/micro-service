package com.ly.mt.center.data.gzg.controller;

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
 * 格子柜六好格子套装对应的商品明细
 *
 * @author zhanglifeng
 * @date 2019-08-28
 */
@Api(description = "格子套装对应的商品明细接口")
@RestController
@RequestMapping("/data/center/gzgSuitBarcodeInfo")
public class GzgSuitBarcodeInfoController {

    @ApiOperation(value = "查询 gzgSuitBarcodeInfo")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/queryBySuitSpuId")
    public ResponseJson queryBySuitSpuId(HttpServletRequest request) {
        return null;
    }
}
