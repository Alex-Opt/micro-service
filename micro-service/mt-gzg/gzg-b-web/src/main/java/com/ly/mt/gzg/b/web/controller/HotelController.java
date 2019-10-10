package com.ly.mt.gzg.b.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.method.GzgBMethodEnum;
import com.ly.mt.gzg.b.web.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * gzgb hotel控制层
 */
@Api("gzgb hotel控制层")
@RestController
@RequestMapping("/gzgb/hotel")
public class HotelController extends BaseController {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(HotelController.class);

    @ApiOperation(value = "保存酒店", notes = "保存酒店")
    @PostMapping("/saveOrUp")
    public JSONObject saveOrUp(@RequestBody JSONObject req){
        Logger.info("HotelController=>saveOrUp:param{}",req);
        return callGzgBServer(GzgBMethodEnum.GZG_HOTEL_INFO_SAVE, req);
    }
}
