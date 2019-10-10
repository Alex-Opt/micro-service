package com.ly.mt.home.tob.coupon.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.base.constant.ShopConstant;
import com.ly.mt.home.tob.coupon.service.ShopCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 优惠券接口
 *
 * @author linan
 * @date 20190709
 */
@Api(value = "优惠券接口", tags = {"优惠券接口"})
@RestController
@RequestMapping("/home/coupon")
public class ShopCouponController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    ShopCouponService shopCouponService;

    @ApiOperation(value = "可用优惠券", notes = "可用优惠券")
    @PostMapping(path = "/getCouponList")
    @ResponseBody
    public ResponseJson getCouponList() {
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, shopCouponService.shopCouponList(ShopConstant.ShopCouponStatus.NO_USE.getValue()));
    }

    @ApiOperation(value = "我的优惠券", notes = "我的优惠券")
    @PostMapping(path = "/myCoupon")
    @ResponseBody
    public ResponseJson myCoupon(@ApiParam(value = "优惠券状态，1.未使用，2.已使用，3.已过期,不填默认为未使用", required = true) @RequestParam(value = "status") String status) {
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, shopCouponService.shopCouponList(status));
    }
}
