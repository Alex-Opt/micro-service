package com.ly.mt.activity.wechart.controller;

import com.ly.mt.activity.wechart.service.WeChartAuthService;
import com.ly.mt.activity.wechart.vo.WeChartOpenIdVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 微信公众号认证登录
 *
 * @author taoye
 */
@Api(description = "微信公众号认证登录")
@RestController
@RequestMapping("/activity1/wechart/auth")
public class WeChartAuthController {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeChartAuthController.class);
    @Resource
    private WeChartAuthService service;

    @ApiOperation("认证登录返回用户openid")
    @GetMapping("/getOpenId")
    public ResponseJson<WeChartOpenIdVo> getOpenId(@RequestParam("code") String code) {
        if (StringUtil.isEmpty(code)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        try {
            return service.getOpenId(code);
        } catch (Exception e) {
            LOGGER.error("AuthController.getOpenId error ", e);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_ERROR, "认证登录失败");
        }
    }
}