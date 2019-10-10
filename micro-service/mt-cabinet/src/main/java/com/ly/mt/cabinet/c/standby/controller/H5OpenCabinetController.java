package com.ly.mt.cabinet.c.standby.controller;

import com.ly.mt.cabinet.b.common.annotation.MixController;
import com.ly.mt.cabinet.b.common.request.LoginRequestBody;
import com.ly.mt.cabinet.b.common.response.TokenRespVo;
import com.ly.mt.cabinet.b.service.AuthLoginService;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.c.rujin.entity.RujinDoEnum;
import com.ly.mt.cabinet.c.rujin.service.GzgRujinDeviceService;
import com.ly.mt.cabinet.c.standby.Intercept.Intercept;
import com.ly.mt.cabinet.c.standby.entity.LoginRespVo;
import com.ly.mt.cabinet.c.standby.entity.OpenCabinetLoginRequestVo;
import com.ly.mt.cabinet.c.standby.service.OpenCabinetLoginService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Api(description = "维护人员打开柜门前登录" ,tags = "H5OpenDevice",value = "登录")
@MixController("/cabinet/c/h5OpenDevice")
public class H5OpenCabinetController {

    private static final Logger log = LoggerFactory.getLogger(H5OpenCabinetController.class);
    @Autowired
    OpenCabinetLoginService openCabinetLoginService;

    @Autowired
    GzgRujinDeviceService gzgRujinDeviceService;

    @ApiOperation("手机号登陆")
    @PostMapping("/login/phone")
    public ResponseJson<LoginRespVo> loginByPhone(@RequestBody OpenCabinetLoginRequestVo body) {
        log.info("H5OpenCabinetLoginController.loginByPhone=登陆,body={}", body);

        ResponseJson responseJson = openCabinetLoginService.loginByPhone(body);

        return responseJson;
    }


//    @ApiOperation("登出")
//    @PostMapping("/login/out")
//    public ResponseJson loginOut(@RequestBody String phone) {
//        log.info("AuthLoginController.loginOut=登陆,phone={}", phone);
//        Resp resp = authLoginService.loginOut(phone);
//        return resp;
//    }

    @ApiOperation(value = "如金柜子维护接口 参数: tname:格子柜名称， cabinet_no 多个货道号可以用“-” 区分，如： 1-2-3-4，表示 1 号， 2 号， 3 号，4 号 4 个货道同时开门 ")
    @PostMapping("/user/maintainByTname")
    @Intercept
    public ResponseJson maintainByTname(@RequestParam(value = "tname",required = true)String tname,
                                 @RequestParam(value = "cabinet_no",required = true)String cabinet_no){
    log.info("如金柜子维护接口 maintainByTname ，controller层接收到的参数tid:{},cabinet_no:{}",tname,cabinet_no);
    Boolean aBoolean = null;
    try {
            return gzgRujinDeviceService.maintainDeviceByTname(tname, cabinet_no, RujinDoEnum.RUJIN_ONLINE);
    } catch (Exception e) {
        log.info("如金柜子维护接口 maintainByTname 调用异常，tname:{},cabinet_no:{}",tname,cabinet_no);
        return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"打开柜子异常");
    }
    }






}
