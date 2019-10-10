package com.ly.mt.cabinet.b.user.controller;

import com.ly.mt.cabinet.b.common.annotation.CustomLog;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.controller.BaseMessageController;
import com.ly.mt.cabinet.b.replenish.service.ReplenishmentShowCaseService;
import com.ly.mt.cabinet.b.replenish.vo.GoodsAddZgVo;
import com.ly.mt.cabinet.b.replenish.vo.GoodsSkuInfoVo;
import com.ly.mt.cabinet.b.replenish.vo.GoodsSpuInfoVo;
import com.ly.mt.cabinet.b.user.service.CabinetMessageService;
import com.ly.mt.cabinet.b.user.vo.CabinetMessageVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * @author wanghongliang
 * @description 格子柜B-补货-接口层。
 * @date 2019-09-16
 */
@Api(description = "CABINETB端用户-消息操作接口")
@RestController
@RequestMapping("/cabinet/b/user/message")
public class CabinetMessageController {
    private static final Logger logger = LoggerFactory.getLogger(CabinetMessageController.class);

    @Resource
    private CabinetMessageService cabinetMessageService;


    @ApiOperation(value = "获取消息列表")
    @CustomLog
    @PostMapping("/getCabinetMessage")
    public ResponseJson<CabinetMessageVo> getCabinetMessage() {
        try {
            return cabinetMessageService.getMessage();
        } catch (Exception e) {
            logger.error("CABINET-B-APP-获取消息列表出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "更新消息阅读状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageType", value = "消息类型 0:格子柜补货消息 1:展柜补货消息'", paramType = "query", required = true)
    })
    @CustomLog
    @PostMapping("/updateCabinetMessage")
    public ResponseJson updateCabinetMessage(@RequestParam(value = "messageType") String messageType) {
        // 校验参数
        if (StringUtil.isEmpty(messageType)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"消息类型不能为空");
        }
        try {
            return cabinetMessageService.updateMessageStatus(messageType);
        } catch (Exception e) {
            logger.error("CABINET-B-APP-更新消息阅读状态报错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
