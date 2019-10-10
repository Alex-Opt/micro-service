package com.ly.mt.mall.h5.goods.controller;


import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mall.h5.goods.service.RotationInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 轮播图Rotation控制层
 */
@Api(description = "轮播图Rotation控制层")
@RestController
@RequestMapping("/mall/h5/goodsSpu")
public class RotationController {

    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(RotationController.class);

    @Resource
    RotationInfoService rotationInfoService;

    @ApiOperation(value = "轮播图查询接口", notes = "查询轮播图")
    @PostMapping("/rotationInfoList")
    public ResponseJson rotationInfoList(HttpServletRequest request) {
        try {
            return rotationInfoService.queryRotationInfoList();
        } catch (Exception e) {
            Logger.error("轮播图查询接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

}
