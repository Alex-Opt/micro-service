package com.ly.mt.payment.web.wxpay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.base.util.XmlUtil;
import com.ly.mt.payment.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.base.method.PaymentMethodEnum.*;

@Api(description = "微信支付接口")
@RestController
@RequestMapping("/payment/wxpay")
public class WxpayController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxpayController.class);

    /**
     * @Description 支付回调
     * @Author taoye
     */
    @ApiIgnore
    @ApiOperation("支付回调")
    @PostMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder();
        String inputLine;
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                sb.append(inputLine);
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String jsonStr = JSON.toJSONString(sb.toString()).replace("<![CDATA[", "").replace("]]>", "").replace("\"", "");
        LOGGER.info("支付回调参数:jsonStr={}", jsonStr);
        try {
            Map<String, Object> map = XmlUtil.transXml2Map(jsonStr);
            jsonStr = JSONObject.toJSONString(map);
        } catch (Exception e) {
            LOGGER.error("支付回调参数解析出错:", e);
            JsonUtil.outPrint(response, getFallXml());
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        jsonObject = callPaymentServer(WX_PAY_NOTIFY, jsonObject);
        String code = jsonObject.getString("code");
        if (RESULT_CODE_OPERATION_SUCCESS.getCode().equals(code)) {
            JsonUtil.outPrint(response, getSuccessXml());
        } else {
            JsonUtil.outPrint(response, getFallXml());
        }
    }


    @ApiOperation(value = "WAP支付", notes = "操作成功后返回result：拉起微信客户端的url")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单编号", paramType = "query", required = true)

    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/wapPay")
    public JSONObject wapPay(@RequestParam(value = "orderNo") String orderNo,HttpServletRequest request) {
        //验证登陆状态
        String loginUserId =getLoginUserId(request);
        String loginIpAddress = getLoginIpAddress(request);
        LOGGER.info("=====================微信支付获取userId:"+loginUserId);
        LOGGER.info("=====================微信支付获取loginIpAddress:"+loginIpAddress);
        if (StringUtil.isEmpty(loginUserId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        if (StringUtil.isEmpty(orderNo)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNo", orderNo);
        jsonObject.put("userId",loginUserId);
        jsonObject.put("loginIpAddress",loginIpAddress);
        return callPaymentServer(WX_PAY_WAP, jsonObject);
    }



    @ApiOperation(value = "微信退款", notes = "操作成功后返回result：拉起微信客户端的url")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单编号", paramType = "query", required = true),
            @ApiImplicitParam(name = "refundNo", value = "退款单Id", paramType = "query", required = true)

    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常! 返回 return_msg 退款失败原因 "),
    })
    @PostMapping("/refund")
    public JSONObject refund(@RequestParam(value = "orderNo")String orderNo,
                             @RequestParam(value = "refundNo")String refundNo,
                             HttpServletRequest request){
        //验证登陆状态
        String loginUserId = getLoginUserId(request);
        if (StringUtil.isEmpty(loginUserId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        if(StringUtil.isEmpty(orderNo) && StringUtil.isEmpty(refundNo)){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject param = new JSONObject();
        param.put("orderNo",orderNo);
        param.put("refundNo",refundNo);
        return callPaymentServer(WX_REFUND_WAP,param);
    }



    /**
     * @Description 获取错误返回
     * @Author taoye
     */
    private String getFallXml() {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("return_code", "<![CDATA[FALL]]>");
        resultMap.put("return_msg", "<![CDATA[NO]]>");
        return XmlUtil.transMap2Xml(resultMap);
    }

    /**
     * @Description 获取成功返回
     * @Author taoye
     */
    private String getSuccessXml() {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("return_code", "<![CDATA[SUCCESS]]>");
        resultMap.put("return_msg", "<![CDATA[OK]]>");
        return XmlUtil.transMap2Xml(resultMap);
    }
}