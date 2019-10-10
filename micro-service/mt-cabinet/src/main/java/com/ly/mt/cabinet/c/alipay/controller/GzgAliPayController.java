package com.ly.mt.cabinet.c.alipay.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.c.alipay.entity.AliPayProperties;
import com.ly.mt.cabinet.c.alipay.entity.AliPayReqVO;
import com.ly.mt.cabinet.c.alipay.service.GzgAliPayService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@Api(tags = {"alipay"})
@RequestMapping("/cabinet/c/gzg/alipay")
public class GzgAliPayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GzgAliPayController.class);
    @Autowired
    GzgAliPayService gzgAliPayService;

    @ApiOperation(value = "alipay")
    @RequestMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response, @RequestBody AliPayReqVO aliPayReqVO) {
        LOGGER.info("开始使用支付宝支付，controller层接收到的数据为{}", aliPayReqVO);
        PrintWriter writer = null;
        response.setContentType("text/html;charset=utf-8");
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ResponseJson result = gzgAliPayService.pay(aliPayReqVO);
            LOGGER.info("支付宝统一下单接口返回给前端得数据：{}", result);
            writer.write(JSONObject.toJSONString(result));
        } catch (Exception e) {
            LOGGER.error("call pay of GzgAliPayController has error,the error message is {}", e.getMessage());
            writer.write(JSONObject.toJSONString(ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR)));
            ;
        } finally {
            writer.close();
        }
    }

    /**
     * 支付完成异步通知
     *
     * @param request
     */
    @RequestMapping("/notify")
    public void notify(HttpServletRequest request) {
        LOGGER.info("支付宝支付成功回调notify 请求参数:{}",request);
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        ResponseJson result = gzgAliPayService.aliNotify(requestParams);

    }



    @ApiOperation("view pay result and goods info")
    @GetMapping("/payResult")
    public ResponseJson payResult(HttpServletRequest request, @RequestParam("orderNo") String orderNo) {
        LOGGER.info("調用支付寶支付完成，返貨結果接口，參數是：{}", orderNo);
        try {
            ResponseJson responseJson = gzgAliPayService.payResult((String)orderNo);
            return responseJson;
        } catch (Exception e) {
            LOGGER.error("call payResult of GzgAliPayController has error,the error message is {}", e.getMessage());
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }
}
