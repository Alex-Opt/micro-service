package com.ly.mt.cabinet.b.controller;

import com.ly.mt.cabinet.b.common.annotation.AccessRequired;
import com.ly.mt.cabinet.b.common.annotation.MixController;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.CabinetMenageListRequestVo;
import com.ly.mt.cabinet.b.common.request.ImeiRequestBody;
import com.ly.mt.cabinet.b.common.response.PageInfoResponseVo;
import com.ly.mt.cabinet.b.common.response.ShowCabinetByBdMsgVo;
import com.ly.mt.cabinet.b.common.response.ShowcaseCoopDefaultRespVo;
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
 * 展柜管理
 */
@Api(description = "展柜管理")
@MixController("/cabinet/b/showcaseCabinet")
public class ShowcaseCabinetManageController extends BaseMessageController {

    private static final Logger log = LoggerFactory.getLogger(ShowcaseCabinetManageController.class);

    @Autowired
    private CabinetInfoService cabinetInfoService;

    @AccessRequired
    @ApiOperation("/bd展柜列表")
    @PostMapping("/showcaseCabinets")
    public Resp<PageInfoResponseVo> showcaseCabinets(@RequestBody CabinetMenageListRequestVo body, HttpServletRequest request) {
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        PageInfoResponseVo<ShowCabinetByBdMsgVo> vo = cabinetInfoService.showcaseCabinets(body, tokenUserMessage);
        return Resp.createBySuccess(vo);
    }

    @AccessRequired
    @ApiOperation("/展柜合作信息")
    @PostMapping("/showcaseCabinetCoopMsg")
    public Resp<ShowcaseCoopDefaultRespVo> showcaseCabinetCoopMsg(@RequestBody ImeiRequestBody imei, HttpServletRequest request) {
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        ShowcaseCoopDefaultRespVo vo = cabinetInfoService.findShowcaseCabinetCoopMsg(imei, tokenUserMessage);
        return Resp.createBySuccess(vo);
    }

    @AccessRequired
    @ApiOperation("展柜下架")
    @PostMapping("/down")
    public Resp down(@RequestBody ImeiRequestBody imei, HttpServletRequest request) {
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        String result = cabinetInfoService.downShowCabinet(imei, tokenUserMessage);
        return Resp.createBySuccess(result);
    }

    @ApiOperation("展柜上架")
    @PostMapping("/up")
    public Resp up(@RequestBody ImeiRequestBody imei, HttpServletRequest request) {
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        String result = cabinetInfoService.upShowCabinet(imei, tokenUserMessage);
        return Resp.createBySuccess(result);
    }


}
