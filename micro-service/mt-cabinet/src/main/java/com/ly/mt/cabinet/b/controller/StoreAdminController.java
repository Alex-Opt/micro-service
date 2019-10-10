package com.ly.mt.cabinet.b.controller;

import com.ly.mt.cabinet.b.common.annotation.MixController;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.StoreAdminOrdersRequestBody;
import com.ly.mt.cabinet.b.common.response.CabinetImeiRespVo;
import com.ly.mt.cabinet.b.common.response.PageInfoResponseVo;
import com.ly.mt.cabinet.b.common.response.StoreAdminOrdersRespVo;
import com.ly.mt.cabinet.b.service.StoreAdminService;
import com.ly.mt.cabinet.b.util.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "店铺管理员身份-订单管理")
@MixController("/cabinet/b/storeAdmin")
public class StoreAdminController extends BaseMessageController {


    @Resource
    StoreAdminService storeAdminService;

    @ApiOperation("查询所有的格子柜")
    @PostMapping("/imeis")
    public Resp findUserCabinetImeis(HttpServletRequest request){
        TokenUserMessage body = getTokenUserMessage(request);
        List<CabinetImeiRespVo> result = storeAdminService.findUserCabinetImeis(body);
        return Resp.createBySuccess(result);
    }

    @ApiOperation("条件查询订单 分页显示")
    @PostMapping("/conditionsOrders")
    public Resp findConditionsOrders(@RequestBody StoreAdminOrdersRequestBody body, HttpServletRequest request){
        TokenUserMessage user = getTokenUserMessage(request);
        String s = storeAdminService.findConditionsOrders(body, user);
        return Resp.createBySuccess(s);
    }

}
