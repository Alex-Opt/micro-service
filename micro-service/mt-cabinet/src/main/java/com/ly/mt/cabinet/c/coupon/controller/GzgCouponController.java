package com.ly.mt.cabinet.c.coupon.controller;

import com.ly.mt.cabinet.c.coupon.service.GzgCouponCodeService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@RestController
@RequestMapping("/cabinet/c/gzg/coupon")
@Api(value = "coupon controller", tags = {"coupon"})
public class GzgCouponController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GzgCouponController.class);

    @Autowired
    GzgCouponCodeService gzgCouponCodeService;

    /**
     * 格子柜商品列表
     *
     * @return
     */
    @ApiOperation(value = "优惠券列表", notes = "前端通过用户id获取该用户的所有优惠券信息")
    @PostMapping("/couponList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true)
    })
    public ResponseJson couponList(@RequestParam(required = false) String userId) {
        LOGGER.info("格子柜用户优惠券列表，controller层接接收到的数据：gzgCode ={}，userId = {}",userId);
        if (StringUtils.isBlank(userId) || StringUtils.equals("undefined", userId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
        }
        ResponseJson responseJson = null;
        try {
            responseJson = gzgCouponCodeService.selectAllCouponByUserId(userId);
            LOGGER.info("格子柜用户优惠券列表，controller层返回给前段的数据{}", responseJson);
            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("格子柜用户优惠券列表，controller层异常，异常结果{}", e.getMessage());
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }





}
