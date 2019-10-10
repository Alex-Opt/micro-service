package com.ly.mt.home.tob.purchases.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.tob.purchases.service.ShopExclusiveDiscountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 专属优惠接口
 *
 * @author: linan
 * @date: 2019/9/16
 **/
@Api(tags = "专属优惠接口")
@RestController
@RequestMapping("/home/exclusive")
public class ShopExclusiveController {

    @Resource
    ShopExclusiveDiscountService service;

    @ApiOperation("专属优惠")
    @PostMapping(path = "/get")
    @ResponseBody
    public ResponseJson getShopExclusive() {
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, service.getShopExclusiveDiscount());
    }

}
