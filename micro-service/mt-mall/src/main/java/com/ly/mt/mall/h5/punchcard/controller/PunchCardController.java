package com.ly.mt.mall.h5.punchcard.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mall.h5.punchcard.service.PunchCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 用户打卡
 *
 * @author ypmu
 * @date 20150530
 */

@Api(description = "用户打卡接口")
@RestController
@RequestMapping("/mall/h5/punchCard")
public class PunchCardController {

    private final static Logger Logger = LoggerFactory.getLogger(PunchCardController.class);

    @Resource
    PunchCardService punchCardService;

    @ApiOperation(value = "用户打卡", notes = "用户打卡")
    @PostMapping(value = "/punchCard")
    @ResponseBody
    public ResponseJson punchCard(HttpServletRequest request) {
        try {
            return punchCardService.punchCard();
        } catch (Exception e) {
            Logger.error("用户打卡出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "查询用户打卡记录", notes = "用户打卡记录数据")
    @PostMapping(value = "/queryPointDataByUserId")
    @ResponseBody
    public ResponseJson queryPointDataByUserId(HttpServletRequest request) {
        try {
            return punchCardService.queryPointDataByUserId();
        } catch (Exception e) {
            Logger.error("查询用户打卡记录出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
