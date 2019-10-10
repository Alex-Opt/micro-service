package com.ly.mt.center.data.cabinet.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户奖励金额接口
 *
 * @author zhanglifeng
 * @date 2019-08-28
 */
@Api(description = "用户奖励金额接口")
@RestController
@RequestMapping("/data/center/cabinetReplenishReward")
public class CabinetReplenishRewardController {

    @ApiOperation(value = "保存一条用户的奖励记录")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/insertCabinetReplenishReward")
    public ResponseJson insertCabinetReplenishReward(HttpServletRequest request) {
        return null;
    }

    @ApiOperation(value = "根据数据的逐渐id更新一条用户的奖励记录")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/updateCabinetReplenishRewardById")
    public ResponseJson updateCabinetReplenishRewardById(HttpServletRequest request) {
        return null;
    }


    @ApiOperation(value = "根据userId查询一条用户的奖励记录")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/queryCabinetReplenishRewardByUserId")
    public ResponseJson queryCabinetReplenishRewardByUserId(HttpServletRequest request) {
        return null;
    }


}
