package com.ly.mt.gzg.b.web.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.method.GzgBMethodEnum;
import com.ly.mt.core.common.request.UserLoginReqVO;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.gzg.b.web.base.controller.BaseController;
import com.ly.mt.gzg.b.web.config.request.RegisterReqVO;
import com.ly.mt.gzg.b.web.config.request.UserReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Api(tags = {"用户"})
@RestController
@RequestMapping("/gzgb/user")
@Slf4j
public class UserController extends BaseController {

    private Cookie cookie;

    /**
     * 登录（手机号/验证码）
     * @param userReqVO
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public JSONObject login(HttpServletRequest request, HttpServletResponse response, @RequestBody UserReqVO userReqVO){
        log.info("ip地址 {}, 手机号 {},clientId {}, 请求发送验证码at{}",request.getRemoteAddr(),userReqVO.getPhoneNo(),userReqVO.getClientId(),new Date());
        String userId = (String) request.getAttribute("userId");
        String phoneNo = userReqVO.getPhoneNo();
        log.info("登录手机号-->{}",phoneNo);
        String dynamicCode = userReqVO.getDynamicCode();
        if (StringUtils.isBlank(phoneNo) || StringUtils.isBlank(dynamicCode)){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        JSONObject json = JSON.parseObject(JSON.toJSONString(userReqVO));
        json.put("userId",userId);
        return callGzgBServer(GzgBMethodEnum.GZG_USER_LOGIN, json);
    }

    /**
     * 注册（手机号/验证码）
     * @param userReqVO
     * @return
     */
    @ApiOperation("注册")
    /*@ApiResponses({
            @ApiResponse(code)
    })*/
    @PostMapping("/register")
    public JSONObject register(HttpServletRequest request,@RequestBody UserReqVO userReqVO){
        log.info("ip地址 {}, 手机号 {},clientId {}, 请求发送验证码at{}",request.getRemoteAddr(),userReqVO.getPhoneNo(),userReqVO.getClientId(),new Date());
        String phoneNo = userReqVO.getPhoneNo();
        String dynamicCode = userReqVO.getDynamicCode();
        String clientId = userReqVO.getClientId();
        if (StringUtils.isBlank(phoneNo) || StringUtils.isBlank(dynamicCode) || StringUtils.isBlank(clientId)){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        JSONObject json = new JSONObject();
        json.put("phoneNo",phoneNo);
        json.put("dynamicCode",dynamicCode);
        json.put("clientId",clientId);
        return callGzgBServer(GzgBMethodEnum.GZG_USER_REGISTER, json);
    }

    @PostMapping("/register_")
    public JSONObject register_(@RequestBody RegisterReqVO registerReqVO){
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(registerReqVO));
        return callGzgBServer(GzgBMethodEnum.GZG_USER_REGISTER, jsonObject);
    }

    /**
     * 推出登录
     * @return
     */
    @PostMapping("/out")
    public JSONObject out(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        json.put("userId",userId);
        JSONObject jsonObject = callGzgBServer(GzgBMethodEnum.GZG_USER_OUT, json);
        return jsonObject;
    }
}
