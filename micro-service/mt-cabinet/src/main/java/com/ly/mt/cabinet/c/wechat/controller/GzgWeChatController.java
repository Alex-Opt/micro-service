package com.ly.mt.cabinet.c.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.c.wechat.entity.WeChatPayReqVO;
import com.ly.mt.cabinet.c.wechat.entity.WeChatUtil;
import com.ly.mt.cabinet.c.wechat.service.GzgWeChatService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@Api(tags = {"wechat"})
@RestController
@RequestMapping("/cabinet/c/gzt/weChat")
public class GzgWeChatController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgWeChatController.class);
    @Resource
    WeChatUtil weChatUtil;
    @Resource
    GzgWeChatService weChatService;

    @ApiOperation(value = "wechat pay by H5")
    @PostMapping("/weChatPay")
    public ResponseJson weChatPay(HttpServletRequest request, @RequestBody WeChatPayReqVO weChatPayReqVO) {
        if (StringUtils.isBlank(weChatPayReqVO.getGzgOrderId())) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数错误");
        }
        String billCreateIp = getIpAddress(request);

        try {
            ResponseJson responseJson = weChatService.weChatPayByH5(billCreateIp, weChatPayReqVO.getGzgOrderId());
            LOGGER.info("controller层获取的IP地址：{}", billCreateIp);
            return responseJson;
        } catch (Exception e) {
            LOGGER.error("call weChatPay of GzgWeChatController error,the error message is {}", e.getMessage());
            e.printStackTrace();
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "wechat pay by JSAPI")
    @PostMapping("/weChatPayByJsApi")
    public ResponseJson weChatPayByJsApi(HttpServletRequest request, @RequestBody WeChatPayReqVO weChatPayReqVO) {
        if (StringUtils.isBlank(weChatPayReqVO.getGzgOrderId()) || StringUtils.isBlank(weChatPayReqVO.getCode())) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数错误");
        }
        JSONObject json = new JSONObject();
        json.put("gzgOrderId", weChatPayReqVO.getGzgOrderId());
        json.put("billCreateIp", getIpAddress(request));
        json.put("code", weChatPayReqVO.getCode());
        String billCreateIp = getIpAddress(request);
        try {
            ResponseJson responseJson = weChatService.weChatPayByJsApi(weChatPayReqVO, billCreateIp);
            return responseJson;
        } catch (Exception e) {
            LOGGER.error("微信浏览器支付调取controller层失败，失败信息 {}", e.getMessage());
            e.printStackTrace();
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 支付结果
     *
     * @return
     */
    @ApiOperation("pay result response")
    @PostMapping("/payResultResp")
    public ResponseJson payResultResp(HttpServletRequest request, @ApiParam(name = "orderNo", value = "订单id", required = true) @RequestBody String orderNo) {
        LOGGER.info("微信支付调用payResultResp接口接收的参数是：{}", orderNo);
        JSONObject jsonObject = JSONObject.parseObject(orderNo);
        String orderNo1 = jsonObject.getString("orderNo");
        if ("".equals(orderNo1)) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数错误");
        }
        try {
            ResponseJson responseJson = weChatService.weChatPayDetail(orderNo1);
            return responseJson;
        } catch (Exception e) {
            LOGGER.error("call payResultResp of GzgWeChatController error,the error message is {}", e.getMessage());
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 微信支付完成异步通知
     *
     * @param request
     */
    @RequestMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/xml;charset=UTF-8");
        LOGGER.info("微信支付回调controller层接收的request数据 {}", request);
        Map<String, Object> weChatReplay = weChatUtil.getWeChatReplay(request);
        LOGGER.info("微信回调controller层解析XML文件后map值：{}", weChatReplay);

        if (weChatReplay.size() == 0) {
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/xml; charset=utf-8");
                PrintWriter pw = response.getWriter();
                pw.print("<xm><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[参数错误！]]></return_msg></xml>");
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ResponseJson responseJson = weChatService.weChatNotify(weChatReplay);
                LOGGER.info("微信支付回调service返回结果：{}", responseJson);
                String code = responseJson.getCode();
                if (ResponseCode.RESPONSE_CODE_SUCCESS.getCode().equals(code)) {
                    LOGGER.info("微信支付回调controller层返回成功");
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/xml; charset=utf-8");
                    PrintWriter pw = response.getWriter();
                    pw.print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
                    pw.flush();
                    pw.close();
                } else {
                    LOGGER.info("微信支付回调controller层返回失败，交易失败");
                    //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
//                response.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[交易失败]]></return_msg></xml>");
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/xml; charset=utf-8");
                    PrintWriter pw = response.getWriter();
                    pw.print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[交易失败]]></return_msg></xml>");
                    pw.flush();
                    pw.close();
                }
            } catch (Exception e) {
                LOGGER.error("call payResultResp of GzgWeChatController error,the error message is {}", e.getMessage());
            }
        }
    }

    /*@ApiOperation(value = "测试一下")
    @PostMapping("/testOrder")
    public ResponseJson testOrder(String id) {
        try {
            weChatService.test(id);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
        }catch (Exception e){
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }*/
    /**
     * @Description 获取ip地址
     * @Author taoye
     */
    private String getIpAddress(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtil.isNotEmpty(ip)) {
            String[] ips = ip.split(",");
            return ips[0];
        }
        return ip;
    }


//    private String getBrowerType(HttpServletRequest request) {
//        // userAgent中有很多获取请求信息的方法
//        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//        // 获取客户端请求的浏览器类型
//        String browser = userAgent.getBrowser().toString();
//
//        LOGGER.info("访问的浏览器类型：{}", browser);
//        return browser;
//    }


}
