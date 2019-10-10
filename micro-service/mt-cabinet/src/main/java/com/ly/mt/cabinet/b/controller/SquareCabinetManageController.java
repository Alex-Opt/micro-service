package com.ly.mt.cabinet.b.controller;

import com.ly.mt.cabinet.b.common.annotation.AccessRequired;
import com.ly.mt.cabinet.b.common.annotation.MixController;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.CabinetMenageListRequestVo;
import com.ly.mt.cabinet.b.common.request.ImeiRequestBody;
import com.ly.mt.cabinet.b.common.response.PageInfoResponseVo;
import com.ly.mt.cabinet.b.common.response.ShowcaseCoopDefaultRespVo;
import com.ly.mt.cabinet.b.common.response.SquareCabinetBdMsgVo;
import com.ly.mt.cabinet.b.service.CabinetInfoService;
import com.ly.mt.cabinet.b.util.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 格子柜管理controller
 */
@Api(description = "格子柜管理")
@MixController("/cabinet/b/squareCabinet")
public class SquareCabinetManageController extends BaseMessageController {

    private static final Logger log = LoggerFactory.getLogger(SquareCabinetManageController.class);

    @Autowired
    CabinetInfoService cabinetInfoService;

    @ApiOperation("查询当前登录人员的格子柜")
    @PostMapping("/squareCabinets")
    public Resp<PageInfoResponseVo<SquareCabinetBdMsgVo>> squareCabinets(@RequestBody CabinetMenageListRequestVo body, HttpServletRequest request) {
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        PageInfoResponseVo<SquareCabinetBdMsgVo> vo = cabinetInfoService.findCabinetsByUser(body, tokenUserMessage);
        return Resp.createBySuccess(vo);
    }

    @ApiOperation("商务合作信息详情")
    @PostMapping("/squareCabinetCoopMsg")
    public Resp<ShowcaseCoopDefaultRespVo> squareCabinetCoopMsg(@RequestBody ImeiRequestBody imei, HttpServletRequest request) {
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        ShowcaseCoopDefaultRespVo vo = cabinetInfoService.findSquareCabinetCoopMsg(imei, tokenUserMessage);
        return Resp.createBySuccess(vo);
    }

    @ApiOperation("点击下架操作")
    @PostMapping("/down")
    public Resp squareCabinetDown(@RequestBody ImeiRequestBody imei, HttpServletRequest request) {
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        String result = cabinetInfoService.downSquareCabinet(imei, tokenUserMessage);
        return Resp.createBySuccess(result);
    }

}
