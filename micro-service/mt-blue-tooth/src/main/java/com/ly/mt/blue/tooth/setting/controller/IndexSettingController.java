package com.ly.mt.blue.tooth.setting.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.setting.vo.BluetoothIndexSettingVo;
import com.ly.mt.blue.tooth.setting.service.IndexSettingService;
import com.ly.mt.blue.tooth.taste.vo.BluetoothIndexVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "用户指标设置接口")
@RestController
@RequestMapping("/bluetooth/setting")
public class IndexSettingController {
    private final static Logger Logger = LoggerFactory.getLogger(IndexSettingController.class);
    @Resource
    IndexSettingService indexSettingService;


    @ApiOperation("用户口感/亮度/频次设置 PS:需要在headers中增加 Content-Type：application/json\n " +
            "indexType 1:口感-自定义 2：灯光 3:频次 \\n\n" +
            "bluetoothIndexList：  indexKey:P1-P10代表口感十个参数\\n\n" +
            "\t\t\t\t\t  indexKey:light-亮度 indexValue:1-弱 2-中 3：强\\n\n" +
            "\t\t\t\t\t  indexKey:color-颜色 indexValue:#egaeeg\\n\n" +
            "\t\t\t\t\t  indexKey:model-模式 indexValue:1-轻度模式 2-中度模式 3-重度模式 4-无限制 5-自定义\\n\n" +
            "\t\t\t\t\t  indexKey:mouthNumber-口数 \\n\n" +
            "\t\t\t\t\t  indexKey:tasteModel-口感模式 indexValue:1-恒定预设 2-最佳匹配 3-冰火冲天 4-激流勇进 5-戏水缠流 6-云端漫步 7-冬日暖阳  8-自定义\\n")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bluetoothIndexList", value = "指标List", paramType = "query", required = true),
                    @ApiImplicitParam(name = "indexType", value = "指标类型", paramType = "query", required = true),
            @ApiImplicitParam(name = "macAddress", value = "mac地址", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @CustomLog
    @PostMapping("/saveUserSetting")
    public ResponseJson saveUserSetting(@RequestBody BluetoothIndexSettingVo bluetoothIndexSettingVo) {
        // 校验参数
        if (bluetoothIndexSettingVo ==null|| bluetoothIndexSettingVo.getBluetoothIndexVoList()==null||StringUtil.isEmpty(bluetoothIndexSettingVo.getIndexType())||
                StringUtil.isEmpty(bluetoothIndexSettingVo.getMacAddress())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"指标参数不能为空");
        }
        try {
            return indexSettingService.saveUserSettings(bluetoothIndexSettingVo.getBluetoothIndexVoList(), bluetoothIndexSettingVo.getIndexType(), bluetoothIndexSettingVo.getMacAddress());
        } catch (Exception e) {
            Logger.error("蓝牙APP-用户口感/亮度/频次设置出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("获取用户口感/亮度/频次设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexType", value = "指标类型", paramType = "query", required = true),
            @ApiImplicitParam(name = "macAddress", value = "mac地址", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    @CustomLog
    @PostMapping("/getUserSetting")
    public ResponseJson<List<BluetoothIndexVo>> getUserSetting(@RequestParam(value = "indexType") String indexType,
                                                              @RequestParam(value = "macAddress") String macAddress) {
        // 校验参数
        if (StringUtil.isEmpty(indexType)||
                StringUtil.isEmpty(macAddress)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"参数有误");
        }
        try {
            return indexSettingService.getUserSettings(indexType,macAddress);
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取用户口感/亮度/频次设置出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}