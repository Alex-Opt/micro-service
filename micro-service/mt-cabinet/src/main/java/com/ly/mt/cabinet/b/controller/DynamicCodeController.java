package com.ly.mt.cabinet.b.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.request.DynamicCodeReqVO;
import com.ly.mt.cabinet.b.service.DynamicCodeService;
import com.ly.mt.cabinet.b.util.ParameterValid;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.b.util.RespCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = "dynamic code")
@RestController
@RequestMapping("/cabinet/b")
public class DynamicCodeController {

    private static final Logger log = LoggerFactory.getLogger(DynamicCodeController.class);

    @Resource
    private DynamicCodeService dynamicCodeService;

    public Resp sendDynamicCode(HttpServletRequest request, @Valid @RequestBody DynamicCodeReqVO dynamicCodeReqVO, BindingResult bindingResult){
        if (!ParameterValid.valid(bindingResult)){
            return Resp.createMessage(RespCode.PARAMETER_VALID_FAIL);
        }
        if (!ParameterValid.phoneNoRuleValid(dynamicCodeReqVO.getPhoneNo())){
            return Resp.createMessage(RespCode.PHONENO_FORMAT_ERROR);
        }
        Resp resp = dynamicCodeService.sendDynamicCode(dynamicCodeReqVO.getPhoneNo());
        return resp;
    }
}
