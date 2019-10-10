package com.ly.mt.open.notify.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.open.notify.service.ProvideApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.redis.RedisKey.REDIS_OFO_TIME_IP;

@Api(description = "ofo调用接口")
@RestController
@RequestMapping("/open/provideApi")
public class ProvideApiController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProvideApiController.class);
    @Resource
    ProvideApiService service;

    @Autowired
    public RedisService redisService;


    /**
     * ofo分销调用，查询订单数据，参数：startTime 格式：yyyy-MM-dd HH:MM:SS
     * @param request
     * @return
     */
    @ApiOperation("ofo查询订单数据")
    @PostMapping("/ofo/queryOrder")
    public ResponseJson queryOrder(HttpServletRequest request) {
        try {
            String startTime = request.getParameter("startTime");
            if (StringUtil.isEmpty(startTime)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误");
            }
            return service.queryOrder(startTime);
        } catch (Exception e) {
            LOGGER.error("ofo查询订单数据,queryOrder出错:", e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "调用发生异常");
        }
    }
}