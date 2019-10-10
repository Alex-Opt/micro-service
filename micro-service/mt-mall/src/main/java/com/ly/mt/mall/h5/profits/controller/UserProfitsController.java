package com.ly.mt.mall.h5.profits.controller;


import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mall.h5.profits.service.UserProfitsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "用户收益接口")
@RestController
@RequestMapping("/mall/h5/user/profits")
public class UserProfitsController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserProfitsController.class);
    @Resource
    private UserProfitsService profitsService;


    @ApiOperation(value = "获取赚钱滚屏数据")
    @PostMapping("/cash/scrolling")
    public ResponseJson listAddress(HttpServletRequest request) {
        try {
            return profitsService.getCashScorolling();
        } catch (Exception e) {
            LOGGER.error("收货地址查询列表接口出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation("获取赚取首页最下方的排行")
    @PostMapping("/rank")
    public ResponseJson getTop(HttpServletRequest request){
        return profitsService.getRank();
    }

    @ApiOperation("获取提现页面详情")
    @PostMapping("/details")
    public ResponseJson getDetails(HttpServletRequest request){
        return profitsService.getDetails();
    }

    @ApiOperation("获取提现页面日志列表")
    @PostMapping("/logs")
    public ResponseJson getLogs(HttpServletRequest request,
                                @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows,
                                @ApiParam(value = "类型 0：收入、1：支出", required = true) @RequestParam(value = "type") int type){
        return profitsService.getLogs(page, rows, type);
    }


}
