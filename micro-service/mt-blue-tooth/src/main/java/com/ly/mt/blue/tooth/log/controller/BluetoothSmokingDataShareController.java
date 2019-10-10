package com.ly.mt.blue.tooth.log.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.log.service.BluetoothLogShareService;
import com.ly.mt.blue.tooth.log.vo.BluetoothSmokingDayDataShareVo;
import com.ly.mt.blue.tooth.log.vo.BluetoothSmokingMonthDataShareVo;
import com.ly.mt.blue.tooth.log.vo.BluetoothSmokingWeekDataShareVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * @program: my-blue-tooth
 * @description: 抽烟数据分享
 * @author: wanghongliang
 * @create: 2019-07-26 11:18
 **/
@Api(description = "抽烟数据分享")
@RestController
@RequestMapping("/bluetooth/share")
public class BluetoothSmokingDataShareController {
    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(BluetoothSmokingDataShareController.class);

    @Resource
    BluetoothLogShareService bluetoothLogShareService;

    @ApiOperation(value="抽烟日数据分享",notes = "date:yyyy-MM-dd")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "烟杆id", paramType = "query", required = true),
            @ApiImplicitParam(name = "date", value = "分享日期 示例:2018-01-01", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/shareDaySmokingData")
    public ResponseJson<BluetoothSmokingDayDataShareVo> shareDaySmokingData(@RequestParam(value = "id") String id,
                                                                     @RequestParam(value = "date") String date) {
        // 校验参数
        if (StringUtil.isEmpty(id)||StringUtil.isEmpty(date)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "日分享请求参数不正确");
        }
        try {
            return bluetoothLogShareService.shareSmokoingDataByDay(id,date);
        } catch (Exception e) {
            Logger.error("蓝牙APP-分享日数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value="抽烟周数据分享",notes = "date:yyyy-MM-dd")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "macAddress", value = "烟杆mac", paramType = "query", required = true),
            @ApiImplicitParam(name = "startDate", value = "分享开始日期(周一) 示例:2019-07-29", paramType = "query", required = true),
            @ApiImplicitParam(name = "endDate", value = "分享结束日期(周日 ) 示例:2019-08-04", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/shareWeekSmokingData")
    public ResponseJson<BluetoothSmokingWeekDataShareVo> shareWeekSmokingData(@RequestParam(value = "macAddress") String macAddress,
                                                                              @RequestParam(value = "startDate") String startDate,
                                                                              @RequestParam(value = "endDate") String endDate) {
        // 校验参数
        if (StringUtil.isEmpty(macAddress)||StringUtil.isEmpty(startDate)||StringUtil.isEmpty(endDate)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "周分享请求参数不正确");
        }
        try {
            return bluetoothLogShareService.shareSmokoingDataByWeek(macAddress,startDate,endDate);
        } catch (Exception e) {
            Logger.error("蓝牙APP-分享周数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value="抽烟月数据分享",notes = "date:yyyy-MM-dd")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "macAddress", value = "烟杆mac", paramType = "query", required = true),
            @ApiImplicitParam(name = "startDate", value = "分享开始日期(月初) 示例:2019-07-01", paramType = "query", required = true),
            @ApiImplicitParam(name = "endDate", value = "分享结束日期(月末 ) 示例:2019-07-31", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/shareMonthSmokingData")
    public ResponseJson<BluetoothSmokingMonthDataShareVo> shareMonthSmokingData(@RequestParam(value = "macAddress") String macAddress,
                                                                                @RequestParam(value = "startDate") String startDate,
                                                                                @RequestParam(value = "endDate") String endDate) {
        // 校验参数
        if (StringUtil.isEmpty(macAddress)||StringUtil.isEmpty(startDate)||StringUtil.isEmpty(endDate)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "月分享请求参数不正确");
        }
        try {
            return bluetoothLogShareService.shareSmokoingDataByMonth(macAddress,startDate,endDate);
        } catch (Exception e) {
            Logger.error("蓝牙APP-分享周数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}