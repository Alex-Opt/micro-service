package com.ly.mt.home.tob.ladder.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.tob.ladder.service.LadderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 阶梯价操作
 *
 * @author linan
 * @date 20190709
 */
@Api(value = "阶梯价配置", tags = {"阶梯价接口"})
@RestController
@RequestMapping("/home/ladder")
public class LadderController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    LadderService ladderService;

    @ApiOperation(value = "阶梯价配置", notes = "返回阶梯价配置")
    @GetMapping
    @ResponseBody
    public ResponseJson shopLadder() {
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, ladderService.shopLadder());
    }

}
