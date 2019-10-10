package com.ly.mt.marketing.web.lode.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.method.MarketingMethodEnum;
import com.ly.mt.marketing.web.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *@Description  淘金人邀请码接口
 *@Author  zhuyh
 */
@Api(description = "淘金人邀请码接口")
@RestController
@RequestMapping("/marketing/lode/runner/codes")
public class LodeRunnerCodesController  extends BaseController {

    /**
     *@Description 获取邀请码
     *@Author  zhuyh
     */
    @ApiOperation(value = "获取邀请码")
    @PostMapping("/")
    public JSONObject getCOde(HttpServletRequest request){
        return callMarketingServer(MarketingMethodEnum.MARKETING_LODE_CODE, new JSONObject());
    }
}
