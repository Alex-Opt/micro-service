package com.ly.mt.cabinet.b.replenish.controller;

import com.ly.mt.cabinet.b.common.annotation.CustomLog;
import com.ly.mt.cabinet.b.replenish.service.ReplenishmentGirdService;
import com.ly.mt.cabinet.b.replenish.vo.CabinetReplenishListVo;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * @author wanghongliang
 * @description 格子柜B-补货-接口层。
 * @date 2019-08-26
 */
@Api(description = "CABINETB端用户-格子柜补货操作接口")
@RestController
@RequestMapping("/cabinet/b/grid/replenish")
public class ReplenishmentGridController {
    private static final Logger logger = LoggerFactory.getLogger(ReplenishmentGridController.class);

    @Resource
    private ReplenishmentGirdService replenishmentService;

    @ApiOperation(value = "生成补货单并推送消息测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "格子柜订单id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 6, message = "入参缺失!")
    })
    @CustomLog
    @PostMapping("/createReplenishOrderAndPushMsg")
    public ResponseJson createReplenishOrderAndPushMsg(@RequestParam(value = "orderId") String orderId) {
        // 校验参数
        if (StringUtil.isEmpty(orderId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"订单编号不能为空！");
        }
        try {
            return replenishmentService.createReplenishOrder(orderId);
        } catch (Exception e) {
            logger.error("CABINET-B-APP-补货单生成失败:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "获取BD补货列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "补货订单状态1:未完成 11:已完成", paramType = "query", required = true)
    })
    @CustomLog
    @PostMapping("/getBdReplenishmentList")
    public ResponseJson<CabinetReplenishListVo> getBdReplenishmentList(@RequestParam(value = "status") String status) {
        // 校验参数
        if (StringUtil.isEmpty(status)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"补货订单状态不能为空!");
        }
        try {
            return replenishmentService.replenishOrderListBd(status);
        } catch (Exception e) {
            logger.error("CABINET-B-APP-获取BD补货列表出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    @ApiOperation(value = "货柜效验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scanCabinetCode", value = "货柜编号通过扫描货柜二维码得到url截取code传给后端", paramType = "query", required = true),
            @ApiImplicitParam(name = "replenishId", value = "补货id", paramType = "query", required = true)
    })
    @CustomLog
    @PostMapping("/cabinetValidate")
    public ResponseJson cabinetValidate(@RequestParam(value = "scanCabinetCode") String scanCabinetCode,@RequestParam(value = "replenishId") String replenishId) {
        // 校验参数
        if (StringUtil.isEmpty(scanCabinetCode)||StringUtil.isEmpty(replenishId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"货柜编号不能为空！");
        }
        try {
            return replenishmentService.cabinetValidate(scanCabinetCode,replenishId);
        } catch (Exception e) {
            logger.error("CABINET-B-APP-货柜编号不能为空:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "BD提交审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "replenishId", value = "补货id", paramType = "query", required = true),
            @ApiImplicitParam(name = "file", value = "审核图片", paramType = "query", required = true)
    })
    @CustomLog
    @PostMapping("/sumbitAuditBd")
    public ResponseJson sumbitAuditBd(@RequestParam(value = "replenishId") String replenishId,
                                        @ApiParam(value = "补货照片", name = "file", required = true) MultipartFile file) {
        // 校验参数
        if (StringUtil.isEmpty(replenishId)||file==null) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"照片/补货订单id不能为空！");
        }
        try {
            return replenishmentService.sumbitAuditBd(replenishId,file);
        } catch (Exception e) {
            logger.error("CABINET-B-APP-BD提交审核报错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
