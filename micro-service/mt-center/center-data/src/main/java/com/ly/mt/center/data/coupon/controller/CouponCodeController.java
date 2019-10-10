package com.ly.mt.center.data.coupon.controller;

import com.ly.mt.center.data.coupon.entity.CouponCode;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "优惠券码接口")
@RestController
@RequestMapping("/data/center/couponCode")
public class CouponCodeController {
    @ApiOperation(value = "保存CouponCode")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserCouponCode")
    public ResponseJson insertCouponCode(@RequestBody CouponCode couponCode) {
        return null;
    }


    @ApiOperation(
            value = "删除CouponCode",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteCouponCode")
    public ResponseJson deleteCouponCode() {
        return null;
    }


    @ApiOperation(
            value = "更新CouponCode",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateCouponCode")
    public ResponseJson updateCouponCode(@RequestBody CouponCode couponCode) {
        return null;
    }


    @ApiOperation(value = "查询CouponCode", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getCouponCode")
    public ResponseJson getCouponCode() {
        return null;
    }


    @ApiOperation("用户兑换兑换码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pull_time", value = "领取时间", required = true, paramType = "query"),
            @ApiImplicitParam(name = "coupon_code", value = "优惠码", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "兑换成功!"),
            @ApiResponse(code = 1, message = "兑换失败!")
    })
    @PostMapping("/updateConponCodeByCode")
    public ResponseJson updateConponCodeByCode() {
        return null;
    }
}