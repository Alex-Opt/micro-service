package com.ly.mt.blue.tooth.user.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.user.service.UserRepairService;
import com.ly.mt.blue.tooth.user.vo.BlueToothRepairsResultVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 用户一键换新Controller
 */
@Api(description = "用户一键换新操作接口")
@RestController
@RequestMapping("/bluetooth/repair")
public class UserRepairController {

    private final static Logger logger = LoggerFactory.getLogger(UserRepairController.class);

    @Resource
    private UserRepairService userRepairService;
    @ApiOperation(value = "填写报修信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "shippingAddress", value = "收货地址", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "生成报修单成功！"),
            @ApiResponse(code = 1, message = "生成报修单失败!"),
    })
    @CustomLog
    @PostMapping("/applicationFrom")
    public ResponseJson<BlueToothRepairsResultVo> applicationFrom(String name, String mobile, String shippingAddress){
        // 校验参数
        if (StringUtil.isEmpty(name) || !StringUtil.isPhoneNumber(mobile)||StringUtil.isEmpty(shippingAddress)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"参数不正确!");
        }
        try {
            return userRepairService.applicationFrom(name,mobile,shippingAddress);
        } catch (Exception e) {
            logger.error("蓝牙APP-填写报修信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "填写物流信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repairsId", value = "报修单Id", paramType = "query", required = true),
            @ApiImplicitParam(name = "logisticsCompany", value = "物流公司", paramType = "query", required = true),
            @ApiImplicitParam(name = "logisticsNumber", value = "物流编号", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @CustomLog
    @PostMapping("/applicationLogistics")
    public ResponseJson<BlueToothRepairsResultVo> applicationLogistics(String repairsId, String logisticsCompany,String logisticsNumber){
        // 校验参数
        if (StringUtil.isEmpty(repairsId)||StringUtil.isEmpty(logisticsCompany) || StringUtil.isEmpty(logisticsNumber)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"参数不正确!");
        }
        try {
            return userRepairService.applicationLogistics(repairsId,logisticsCompany,logisticsNumber);
        } catch (Exception e) {
            logger.error("蓝牙APP-填写物流信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "用户关闭报修")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repairsId", value = "报修Id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @CustomLog
    @PostMapping("/closeBluetoothRepairs")
    public ResponseJson<BlueToothRepairsResultVo> closeBluetoothRepairs(String repairsId){
        // 校验参数
        if (StringUtil.isEmpty(repairsId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"参数不正确!");
        }
        try {
            return userRepairService.closeBluetoothRepairs(repairsId);
        } catch (Exception e) {
            logger.error("蓝牙APP-用户关闭报修出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "用户确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repairsId", value = "报修Id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @CustomLog
    @PostMapping("/confirmReceipt")
    public ResponseJson confirmReceipt(String repairsId){
        // 校验参数
        if (StringUtil.isEmpty(repairsId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"参数不正确!");
        }
        try {
            return userRepairService.confirmReceipt(repairsId);
        } catch (Exception e) {
            logger.error("蓝牙APP-用户确认收货出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
