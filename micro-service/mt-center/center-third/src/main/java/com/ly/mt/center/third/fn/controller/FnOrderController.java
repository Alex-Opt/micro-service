package com.ly.mt.center.third.fn.controller;

import com.ly.mt.center.third.fn.entity.FnOrderCancel;
import com.ly.mt.center.third.fn.entity.FnOrderCreate;
import com.ly.mt.center.third.fn.entity.FnOrderQuery;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(description = "蜂鸟——订单接口")
@RestController
@RequestMapping("/center/third/fn/order")
public class FnOrderController {
    @ApiOperation("推送订单接口")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/create")
    public ResponseJson create(@RequestBody FnOrderCreate fnOrderCreate) {
        return null;
    }


    @ApiOperation(
            value = "取消订单接口",
            notes = "1、订单取消原因代码  \n" +
                    "```  \n" +
                    "1—用户取消  \n" +
                    "2—商家取消  \n" +
                    "```  \n" +
                    "2、订单取消编码  \n" +
                    "```  \n" +
                    "1—物流原因：订单长时间未分配骑手  \n" +
                    "2—物流原因：分配骑手后,骑手长时间未取件  \n" +
                    "3—物流原因：骑手告知不配送,让取消订单  \n" +
                    "4—商品缺货/无法出货/已售完  \n" +
                    "5—商户联系不上门店/门店关门了  \n" +
                    "6—商户发错单  \n" +
                    "7—商户/顾客自身定位错误  \n" +
                    "8—商户改其他第三方配送  \n" +
                    "9—顾客下错单/临时不想要了  \n" +
                    "10—顾客自取/不在家/要求另改时间配送）  \n" +
                    "```  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/cancle")
    public ResponseJson cancle(@RequestBody FnOrderCancel fnOrderCancel) {
        return null;
    }


    @ApiOperation("查询订单接口")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/query")
    public ResponseJson query(@RequestBody FnOrderQuery fnOrderQuery) {
        return null;
    }
}