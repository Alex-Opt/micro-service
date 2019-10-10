package com.ly.mt.blue.tooth.log.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.log.service.BluetoothLogService;
import com.ly.mt.blue.tooth.log.vo.BluetoothLogCountInfoVo;
import com.ly.mt.blue.tooth.log.vo.BluetoothLogTastInfoVo;
import com.ly.mt.blue.tooth.mq.vo.BluetoothLogMqVo;
import com.ly.mt.blue.tooth.mq.vo.BluetoothLogVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
@Api(description = "蓝牙日志数据接口")
@RestController
@RequestMapping("/bluetooth/log")
public class BluetoothLogController {
    private final static Logger Logger = LoggerFactory.getLogger(BluetoothLogController.class);
    @Resource
    BluetoothLogService bluetoothLogService;


    @ApiOperation("首页烟弹数据/烟杆剩余电量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "macAddress", value = "烟杆mac地址", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/getBuluetoothTasteLog")
    public ResponseJson<BluetoothLogTastInfoVo> getBuluetoothTasteLog(@RequestParam(value = "macAddress") String macAddress) {
        // 校验参数
        if (StringUtil.isEmpty(macAddress)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "mac地址不能为空");
        }
        try {
            return bluetoothLogService.getBuluetoothTasteLog(macAddress);
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取首页烟弹数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value="统计烟弹数据",notes = "type:1-日 2-周 3-月 查询时间格式:yyyy-MM-dd HH:mm:ss")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "查询类型", paramType = "query", required = true),
            @ApiImplicitParam(name = "macAddress", value = "烟杆mac地址", paramType = "query", required = true),
            @ApiImplicitParam(name = "startTime", value = "查询开始时间", paramType = "query", required = true),
            @ApiImplicitParam(name = "endTime", value = "查询结束时间", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/countBuluetoothLog")
    public ResponseJson<BluetoothLogCountInfoVo> countBuluetoothLog(@RequestParam(value = "type") String type,
                                                                    @RequestParam(value = "macAddress") String macAddress,
                                                                    @RequestParam(value = "startTime") String startTime,
                                                                    @RequestParam(value = "endTime") String endTime) {
        // 校验参数
        if (StringUtil.isEmpty(type)||StringUtil.isEmpty(macAddress)||StringUtil.isEmpty(startTime)||StringUtil.isEmpty(endTime)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "mac地址不能为空");
        }
        try {
            return bluetoothLogService.countBlueToothLog(type,macAddress,startTime,endTime);
        } catch (Exception e) {
            Logger.error("蓝牙APP-统计烟弹数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value="蓝牙同步抽烟数据",notes = "需要在headers中增加 Content-Type：application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bluetoothLog", value = "蓝牙同步烟杆数据", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @CustomLog
    @PostMapping("/synchronizeBluetoothLog")
    public ResponseJson saveBluetoothLog(@RequestBody BluetoothLogMqVo<BluetoothLogVo> bluetoothLogMqVo) {
        // 校验参数
        if (StringUtil.isEmpty(bluetoothLogMqVo.getMacAddress())||
                bluetoothLogMqVo.getBluetoothLogVoList()==null||bluetoothLogMqVo.getElectricQuantity()==0) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"mac地址不能为空/抽烟数据不能为空/电量不能为0");
        }
        try {
            return bluetoothLogService.saveBlueToothLog(bluetoothLogMqVo);
        } catch (Exception e) {
            Logger.error("蓝牙APP-蓝牙同步抽烟数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}