package com.ly.mt.cabinet.b.controller;

import com.alibaba.fastjson.JSON;
import com.ly.mt.cabinet.b.common.request.LoginReqVO;
import com.ly.mt.cabinet.b.service.UserService;
import com.ly.mt.cabinet.b.util.DateUtil;
import com.ly.mt.cabinet.b.util.ParameterValid;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.b.util.RespCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = "user")
@RestController
@RequestMapping("/cabinet/b/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;


    @ApiOperation("login")
    @PostMapping("/login")
    @ApiResponses({
            @ApiResponse(code = 0,message = "request success"),
            @ApiResponse(code = 1,message = "request fail")
    })
    public Resp login(HttpServletRequest request, @Valid @RequestBody LoginReqVO loginReqVO, BindingResult bindingResult){
        log.info("call method login of UserController at {} , the jsonParam = {}", DateUtil.currentDateTime(), JSON.toJSON(loginReqVO));
        try {
            long start = System.currentTimeMillis();
            if (ParameterValid.valid(bindingResult)){
                return Resp.createMessage(RespCode.PARAMETER_VALID_FAIL);
            }
            if (!ParameterValid.phoneNoRuleValid(loginReqVO.getPhoneNo())){
                return Resp.createMessage(RespCode.PHONENO_FORMAT_ERROR);
            }
            //业务调用数据中心

            long end = System.currentTimeMillis();
            log.info("call method login cost {} mills",(end - start));
            return null;
        }catch (Exception e){
            log.error("call method login has some errors,the error message is {}",e);
            return Resp.createByError();
        }
    }
}
