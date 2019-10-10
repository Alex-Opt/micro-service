package com.ly.mt.center.data.coupon.controller;

import com.ly.mt.center.data.coupon.entity.CouponInfo;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "优惠券接口")
@RestController
@RequestMapping("/data/center/couponInfo")
public class CouponInfoController {
    @ApiOperation(value = "保存CouponInfo")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserCouponInfo")
    public ResponseJson insertCouponInfo(@RequestBody CouponInfo couponInfo) {
        return null;
    }


    @ApiOperation(
            value = "删除CouponInfo",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteCouponInfo")
    public ResponseJson deleteCouponInfo() {
        return null;
    }


    @ApiOperation(
            value = "更新CouponInfo",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateCouponInfo")
    public ResponseJson updateCouponInfo(@RequestBody CouponInfo couponInfo) {
        return null;
    }


    @ApiOperation(value = "查询CouponInfo", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getCouponInfo")
    public ResponseJson getCouponInfo() {
        return null;
    }


    @ApiOperation(value = "根据userId查询CouponInfo", notes = "查询条件user_id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getCouponInfoByUserId")
    public ResponseJson getCouponInfoByUserId() {
        return null;
    }


    @ApiOperation(value = "根据spuId查询CouponInfo", notes = "查询条件spu_id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spu_id", value = "商品spuId", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getCouponInfoBySpuId")
    public ResponseJson getCouponInfoBySpuId() {
        return null;
    }



    @ApiOperation(value = "查询couponInfo表获取新人优惠券")
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getNewUserCoupons")
    public ResponseJson getNewUserCoupons() {
        return null;
    }
}