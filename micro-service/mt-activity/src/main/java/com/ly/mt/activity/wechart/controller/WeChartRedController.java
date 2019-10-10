package com.ly.mt.activity.wechart.controller;

import com.ly.mt.activity.wechart.service.WeChartRedService;
import com.ly.mt.activity.wechart.vo.WeChartSendRedVo;
import com.ly.mt.activity.wechart.vo.WeChartRedStatusVo;
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

@Api(description = "现金红包")
@RestController
@RequestMapping("/activity1/wechart/red/")
public class WeChartRedController {
    @Resource
    private WeChartRedService service;

    @ApiOperation("微信现金红包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "微信openid", required = true, paramType = "query"),
            @ApiImplicitParam(name = "securityCode", value = "防伪码", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "操作失败!")
    })
    @PostMapping("/sendRed")
    public ResponseJson<WeChartSendRedVo> sendRed(@RequestParam(value = "openId") String openId, @RequestParam(value = "securityCode") String securityCode) {
        // 参数校验
        if (StringUtil.isEmpty(openId) || StringUtil.isEmpty(securityCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        if (securityCode.length() != 16) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.sendRed(openId, securityCode);
    }


    @ApiOperation("微信现金红包发放结果查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "操作失败!")
    })
    @PostMapping("/getRedStatus")
    public ResponseJson<WeChartRedStatusVo> getRedStatus(@RequestParam(value = "orderNo") String orderNo) {
        // 参数校验
        if (StringUtil.isEmpty(orderNo)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.getRedStatus(orderNo);
    }
}