package com.ly.mt.cabinet.c.Device.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/gzg/device")
@Api
public class DeviceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);

    @PostMapping("/startDeviceAsyncAdvice")
    @Async
    public String startDeviceAsyncAdvice(@RequestBody JSONObject json) {
        LOGGER.info("亿联页面控制台设备启动比对秘钥调用接口，通知数据-->{}", json.toJSONString());
        return "success";
    }

    @PostMapping("/deviceStart")
    @Async
    public String deviceStart(HttpServletRequest request) {
        return "success";
    }

    @ApiOperation("添加格子柜配货方案")
    @PostMapping("/addDevice")
    public JSONObject addDevice(HttpServletRequest request, @RequestBody String json) {

        return null;
    }
}
