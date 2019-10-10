package com.ly.mt.gzg.b.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.gzg.b.web.base.controller.BaseController;
import com.ly.mt.gzg.b.web.config.annotation.Permission;
import com.ly.mt.gzg.b.web.config.request.RefundReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api("wx/alipay refund interface")
@RestController
@RequestMapping("/gzgb/refund")
public class RefundController extends BaseController {

    @ApiOperation("wx pay refund")
    @PostMapping("/wxPayRefund")
    @Permission
    public JSONObject wxRefund(HttpServletRequest request, @RequestBody @Valid RefundReqVO refundReqVO, BindingResult bindingResult){
        String userId = (String) request.getAttribute("userId");
        if (bindingResult.hasErrors()){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        paramHandler(userId, refundReqVO.getOrderId());
        return null;
    }

    @ApiOperation("ali pay refund")
    @PostMapping("/alipayRefund")
    @Permission
    public JSONObject aliPayRefund(HttpServletRequest request, @RequestBody @Valid RefundReqVO refundReqVO,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        String userId = (String) request.getAttribute("userId");
        JSONObject paramData = paramHandler(userId, refundReqVO.getOrderId());
        return null;
    }

    private JSONObject paramHandler(String userId,String orderId){
        JSONObject json = new JSONObject();
        json.put("userId",userId);
        json.put("orderId",orderId);
        return json;
    }
}
