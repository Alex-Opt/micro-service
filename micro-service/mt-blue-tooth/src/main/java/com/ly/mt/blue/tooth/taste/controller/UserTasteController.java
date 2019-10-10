package com.ly.mt.blue.tooth.taste.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.taste.service.UserTasteService;
import com.ly.mt.blue.tooth.taste.vo.BluetoothIndexVo;
import com.ly.mt.blue.tooth.taste.vo.BluetoothTasteInfoVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "用户烟弹操作接口")
@RestController
@RequestMapping("/bluetooth/taste")
public class UserTasteController {
    private final static Logger Logger = LoggerFactory.getLogger(UserTasteController.class);
    @Resource
    UserTasteService tasteService;


    @ApiOperation("获取烟弹列表")
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/getTasteList")
    public ResponseJson<List<BluetoothTasteInfoVo>> getTasteList() {
        try {
            return tasteService.getTasteList();
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取烟弹列表出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("用户添加烟弹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tasteId", value = "烟弹id", paramType = "query", required = true),
            @ApiImplicitParam(name = "addTime", value = "添加时间", paramType = "query", required = true),
            @ApiImplicitParam(name = "macAddress", value = "烟弹所属烟杆mac地址", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "添加成功!"),
            @ApiResponse(code = 1, message = "添加失败!")
    })
    @CustomLog
    @PostMapping("/saveUserTaste")
    public ResponseJson insertUserTaste(@RequestParam(value = "tasteKey") String tasteKey,
                                  @RequestParam(value = "addTime") String addTime,@RequestParam(value = "macAddress") String macAddress) {
        // 校验参数
        if (StringUtil.isEmpty(tasteKey) || StringUtil.isEmpty(addTime)|| StringUtil.isEmpty(macAddress)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"烟弹id/添加时间/macAddress不能为空！");
        }
        try {
            return tasteService.saveUserTaste(tasteKey,addTime,macAddress);
        } catch (Exception e) {
            Logger.error("蓝牙APP-用户添加烟弹出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("获取最佳匹配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tasteKey", value = "口味Key", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    @CustomLog
    @PostMapping("/getTasteBest")
    public ResponseJson<List<BluetoothIndexVo>> getTasteBest(@RequestParam(value = "tasteKey") String tasteKey) {
         // 校验参数
        try {
            return tasteService.getBestTaste(tasteKey);
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取最佳匹配出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}