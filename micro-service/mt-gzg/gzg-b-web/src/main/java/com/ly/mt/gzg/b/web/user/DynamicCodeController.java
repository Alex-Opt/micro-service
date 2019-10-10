package com.ly.mt.gzg.b.web.user;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.method.GzgBMethodEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.gzg.b.web.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Api(tags = {"get dynamic code"})
@RestController
@RequestMapping("/gzgb/code")
@Slf4j
public class DynamicCodeController extends BaseController {

    @ApiOperation("获取动态验证码")
    @GetMapping("/dynamicCode")
    public JSONObject dynamicCode(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "phoneNo") @RequestParam("phoneNo") String phoneNo, @ApiParam(name = "dynamicType") @RequestParam("dynamicType") int dynamicType){
        log.info("ip地址 {}, 手机号 {}, 请求发送验证码at{}",request.getRemoteAddr(),phoneNo,new Date());
        if (StringUtils.isBlank(phoneNo)){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        JSONObject json = new JSONObject();
        json.put("phoneNo",phoneNo);
        json.put("dynamicType",dynamicType);
        response.setCharacterEncoding("UTF-8");
        JSONObject result = callGzgBServer(GzgBMethodEnum.GZG_B_SEND_CODE, json);
        return result;
    }
}
