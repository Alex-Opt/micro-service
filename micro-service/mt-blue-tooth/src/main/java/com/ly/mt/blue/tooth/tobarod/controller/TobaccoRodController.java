package com.ly.mt.blue.tooth.tobarod.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.tobarod.service.TobaccoRodService;
import com.ly.mt.blue.tooth.tobarod.vo.BluetoothElectricQuantityDetailVo;
import com.ly.mt.blue.tooth.tobarod.vo.BluetoothUserBindVo;
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

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "烟杆操作接口")
@RestController
@RequestMapping("/bluetooth/tobacco")
public class TobaccoRodController {
    private final static Logger Logger = LoggerFactory.getLogger(TobaccoRodController.class);
    @Resource
    TobaccoRodService tobaccoRodService;


    @ApiOperation("获取用户绑定烟杆列表")
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/getTobaccoRodList")
    public ResponseJson<List<BluetoothUserBindVo>> getTasteList() {
        try {
            return tobaccoRodService.getTobaccoRodList();
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取用户绑定烟杆列表出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("用户绑定烟杆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "烟杆名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "macAddress", value = "mac地址", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "绑定成功!"),
            @ApiResponse(code = 1, message = "绑定失败!")
    })
    @CustomLog
    @PostMapping("/bindTobaccoRod")
    public ResponseJson bindTobaccoRod(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "macAddress") String macAddress) {
        // 校验参数
        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(macAddress)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"烟杆名称/mac地址不能为空！");
        }
        try {
            return tobaccoRodService.bindTobaccoRod(name,macAddress);
        } catch (Exception e) {
            Logger.error("蓝牙APP-用户绑定烟杆出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("用户解绑烟杆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "烟杆id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "解绑成功!"),
            @ApiResponse(code = 1, message = "解绑失败!")
    })
    @CustomLog
    @PostMapping("/unbindTobaccoRod")
    public ResponseJson unbindTobaccoRod(@RequestParam(value = "id") String id) {
        // 校验参数
        if (StringUtil.isEmpty(id) ) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"烟杆id不能为空！");
        }
        try {
            return tobaccoRodService.unbindTobaccoRod(id);
        } catch (Exception e) {
            Logger.error("蓝牙APP-用户解绑烟杆出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("用户修改烟杆名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "烟杆id", paramType = "query", required = true),
            @ApiImplicitParam(name = "name", value = "烟杆名称", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "修改失败!")
    })
    @CustomLog
    @PostMapping("/modifyTobaccoRod")
    public ResponseJson modifyTobaccoRod(@RequestParam(value = "id") String id,
                                         @RequestParam(value = "name") String name) {
        // 校验参数
        if (StringUtil.isEmpty(id)||StringUtil.isEmpty(name) ) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"烟杆名称不能为空！");
        }
        try {
            return tobaccoRodService.modifyTobaccoRod(id,name);
        } catch (Exception e) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("烟杆设置儿童锁")
    @ApiImplicitParams({@ApiImplicitParam(name = "action", value = "动作 0:关闭 1:开启", paramType = "query", required = true),
            @ApiImplicitParam(name = "id", value = "烟杆id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "解绑成功!"),
            @ApiResponse(code = 1, message = "解绑失败!")
    })
    @CustomLog
    @PostMapping("/settingChildLock")
    public ResponseJson settingChildLock(@RequestParam(value = "action") String action,@RequestParam(value = "id") String id) {
        // 校验参数
        if (StringUtil.isEmpty(id)|| StringUtil.isEmpty(action)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"烟杆id/动作不能为空");
        }
        try {
            return tobaccoRodService.settingChildLock(action,id);
        } catch (Exception e) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("获取烟杆电量详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "烟杆id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    @CustomLog
    @PostMapping("/getElectricQuantityDetail")
    public ResponseJson<BluetoothElectricQuantityDetailVo> getElectricQuantityDetail(@RequestParam(value = "id") String id) {
        // 校验参数
        if (StringUtil.isEmpty(id) ) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"烟杆id不能为空！");
        }
        try {
            return tobaccoRodService.getElectricQuantityDetail(id);
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取烟杆电量详情", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}