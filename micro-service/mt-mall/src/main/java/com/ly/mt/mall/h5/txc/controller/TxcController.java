package com.ly.mt.mall.h5.txc.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mall.h5.txc.service.TxcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Api(description = "事务测试样例，请忽略该接口")
@RestController
@RequestMapping("/h5/txc")
public class TxcController {
    @Resource
    TxcService service;

    @ApiOperation("测试事务")
    @PostMapping("/txc")
    public ResponseJson txc() {
        try {
            service.test();
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}