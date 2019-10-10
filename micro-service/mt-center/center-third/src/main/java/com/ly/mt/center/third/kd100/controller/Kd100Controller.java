package com.ly.mt.center.third.kd100.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.kd100.service.Kd100Service;
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

@Api(description = "快递100接口")
@RestController
@RequestMapping("/center/third/kd100/deliveryInfo")
public class Kd100Controller {

    @Resource
    Kd100Service kd100Service;

    @ApiOperation(
            value = "查询物流信息接口",
            notes = "1、当查询顺丰快递物流信息时,手机号不能为空  \n" +
                    "2、物流公司编码  \n" +
                    "```  \n" +
                    "shunfeng——顺丰  \n" +
                    "shentong——申通  \n" +
                    "yuantong——圆通  \n" +
                    "zhongtong——中通  \n" +
                    "huitongkuaidi——汇通  \n" +
                    "yunda——韵达  \n" +
                    "```  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/query")
    public ResponseJson query(@RequestBody JSONObject jsonObject) {
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,kd100Service.getDeliveryInfo(jsonObject));
    }
}