package com.ly.mt.open.notify.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.open.notify.service.NotifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(description = "回调接口")
@RestController
@RequestMapping("/open/notify")
public class NotifyController {
    private final static Logger LOGGER = LoggerFactory.getLogger(NotifyController.class);
    @Resource
    NotifyService service;

    @ApiOperation("阿里支付回调")
    @PostMapping("/alPay")
    public void aliPay(HttpServletRequest request, HttpServletResponse response) {
        String result = service.alPayNotify(request);
        ResponseUtil.outPrint(response, result);
    }


    @ApiOperation("微信支付回调")
    @PostMapping("/wxPay")
    public void wxPay(HttpServletRequest request, HttpServletResponse response) {
        String result = service.wxPayNotify(request);
        ResponseUtil.outPrint(response, result);
    }


    @ApiOperation("蜂鸟订单状态回调")
    @PostMapping("/fnOrder")
    public void fnOrder(@RequestBody String json) {
        LOGGER.info("==============蜂鸟订单状态回调成功！========================json"+json);
        try {
            service.fnOrder(JSONObject.parseObject(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}