package com.ly.mt.payment.web.alipay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.StringUtil;
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
import java.util.Iterator;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.base.method.PaymentMethodEnum.ALI_PAY_NOTIFY;
import static com.ly.mt.core.base.method.PaymentMethodEnum.ALI_PAY_WAP;

@Api(description = "阿里支付接口")
@RestController
@RequestMapping("/payment/alipay")
public class AlipayController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AlipayController.class);

    @ApiIgnore
    @ApiOperation("支付回调")
    @PostMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        String jsonStr = JSON.toJSONString(params);
        LOGGER.info("支付回调参数:jsonStr={}", jsonStr);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        jsonObject = callPaymentServer(ALI_PAY_NOTIFY, jsonObject);
        String code = jsonObject.getString("code");
        if (RESULT_CODE_OPERATION_SUCCESS.getCode().equals(code)) {
            JsonUtil.outPrint(response, "success");
        } else {
            JsonUtil.outPrint(response, "error");
        }
    }


    @ApiOperation(value = "WAP支付", notes = "操作成功后返回form页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "returnUrl", value = "支付成功后跳转地址", paramType = "query", required = true),
            @ApiImplicitParam(name = "orderNo", value = "订单id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/wapPay")
    public JSONObject wapPay(@RequestParam(value = "orderNo") String orderNo,
                             @RequestParam(value = "returnUrl") String returnUrl,
                             HttpServletResponse response,HttpServletRequest request) {
        //验证登陆状态
        String loginUserId = getLoginUserId(request);
        if (StringUtil.isEmpty(loginUserId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        // 参数校验
        if (StringUtil.isEmpty(returnUrl) || StringUtil.isEmpty(orderNo)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用服务接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNo", orderNo);
        jsonObject.put("returnUrl", returnUrl);
        return callPaymentServer(ALI_PAY_WAP, jsonObject);
    }
}