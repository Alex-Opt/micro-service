package com.ly.mt.blue.tooth.subsidary.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.subsidary.service.SubsidaryService;
import com.ly.mt.blue.tooth.subsidary.vo.FirmwareVo;
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

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "用户附属信息")
@RestController
@RequestMapping("/bluetooth/subsidary")
public class SubsidaryController {
    private final static Logger Logger = LoggerFactory.getLogger(SubsidaryController.class);
    @Resource
    SubsidaryService subsidaryService;

    @ApiOperation("用户投诉建议")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "姓名", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "remark", value = "备注", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @CustomLog
    @PostMapping("/saveUserSuggest")
    public ResponseJson saveUserSuggest(@RequestParam(value = "name") String name,
                                            @RequestParam(value = "mobile") String mobile,
                                            @RequestParam(value = "remark") String remark) {

        // 校验参数
        if (StringUtil.isEmpty(remark)||StringUtil.isEmpty(name)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"姓名/建议不能为空！");
        }// 校验参数
        if (!StringUtil.isPhoneNumber(mobile)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"手机号有误");
        }
        try {
            return subsidaryService.saveUserSuggest(name,mobile,remark);
        } catch (Exception e) {
            Logger.error("蓝牙APP-用户投诉建议出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("用户设定抽烟目标")
    @ApiImplicitParams({@ApiImplicitParam(name = "smokingTarget", value = "抽烟目标", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @CustomLog
    @PostMapping("/saveOrModifySmokingTarget")
    public ResponseJson saveOrModifySmokingTarget(@RequestParam(value = "smokingTarget") String smokingTarget) {
        // 校验参数
        if (StringUtil.isEmpty(smokingTarget)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"抽烟目标不能为空！");
        }
        try {
            return subsidaryService.saveOrModifySmokingTarget(smokingTarget);
        } catch (Exception e) {
            Logger.error("蓝牙APP-用户设定抽烟目标出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("用户设定达标天数")
    @ApiImplicitParams({@ApiImplicitParam(name = "complianceDaysTarget", value = "达标天数", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @CustomLog
    @PostMapping("/saveOrModifyComplianceDaysTarget")
    public ResponseJson saveOrModifyComplianceDaysTarget(@RequestParam(value = "complianceDaysTarget") String complianceDaysTarget) {
        // 校验参数
        if (StringUtil.isEmpty(complianceDaysTarget)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"达标天数不能为空！");
        }
        try {
            return subsidaryService.saveOrModifyComplianceDaysTarget(complianceDaysTarget);
        } catch (Exception e) {
            Logger.error("蓝牙APP-用户设定达标天数出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }



    @ApiOperation("获取最新版本")
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "类型1:APP升级 2:固件升级 当type=1时 下面终端类型必传/type=2时 终端类型可空", paramType = "query", required = true),
            @ApiImplicitParam(name = "terminalType", value = "终端类型1:ios 2:安卓", paramType = "query", required = true),
    })
    @CustomLog
    @PostMapping("/getFirmwareUpgrade")
    public ResponseJson<FirmwareVo> getFirmwareUpgrade(@RequestParam(value = "type") String type,@RequestParam(value = "terminalType") String terminalType) {
        try {
            // 校验参数
            if (StringUtil.isEmpty(type)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"升级类型或终端类型不能为空");
            }
            if(type.equals("1")&&StringUtil.isEmpty(terminalType)){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"APP升级时终端类型不能为空");
            }
            return subsidaryService.getFirmwareUpgrade(type,terminalType);
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取最新固件版本出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}