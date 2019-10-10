package com.ly.mt.mall.h5.coupon.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.h5.coupon.service.CouponInfoService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.*;


/**
 * @author xinguangzhi
 * @author zhanglifeng
 * 优惠券控制层
 */
@Api(description = "优惠券控制层")
@RestController
@RequestMapping("/mall/h5/couponInfo")
public class CouponInfoController {

    private final static Logger Logger = LoggerFactory.getLogger(CouponInfoController.class);

    @Resource
    CouponInfoService couponInfoService;

    @ApiOperation(value = "领取单张优惠券接口", notes = "领取优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponId", value = "优惠券id", paramType = "query", required = true)
    })
    @PostMapping("/saveCouponCode")
    public ResponseJson saveCouponCode(HttpServletRequest request) {
        try {
            String couponId = request.getParameter("couponId");
            if (StringUtil.isEmpty(couponId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return couponInfoService.saveCouponCode(couponId);
        } catch (Exception e) {
            Logger.error("领取优惠券接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * description:该接口目的是支撑落地页，商城的优惠券领取逻辑。
     * 业务场景：一个用户一次性领取多张优惠券，每张优惠券每个用户限制领取次数：根据入参灵活判断。
     * 安全考虑：优惠券领取由于涉及的参数字段比较明晰，这里给出一般入参字段。前后端约定好顺序传过来。防止有心人知道字段含义篡改数据。
     *
     * @param request p1:优惠券id集合,为逗号分割的字符串格式, p2:领取优惠券的用户id, p3:每张优惠券每个用户限制领取次数
     * @return
     * @author zhanglifeng
     * @date 2019-08-12
     */
    @ApiOperation(value = "批量领取优惠券接口", notes = "支持一张或者多张优惠券领取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "p1", value = "优惠券id集合,为逗号分割的字符串格式。比如 couponIds:32132,465465,496851222", paramType = "query", required = true),
            @ApiImplicitParam(name = "p2", value = "领取优惠券的用户id", paramType = "query", required = true),
            @ApiImplicitParam(name = "p3", value = "每张优惠券每个用户限制领取次数", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "领取成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 6, message = "入参缺失!"),
            @ApiResponse(code = 16, message = "领取已达上限，感谢您的参与")
    })
    @PostMapping("/batchDrawCoupons")
    public ResponseJson batchDrawCoupons(HttpServletRequest request,
                                         @RequestParam(value = "p1") String couponIds,
                                         @RequestParam(value = "p2") String userId,
                                         @RequestParam(value = "p3") String times) {
        try {
            if (StringUtil.isEmpty(couponIds) || StringUtil.isEmpty(userId) || StringUtil.isEmpty(times)) {
                return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "入参缺失！");
            }
            return couponInfoService.batchDrawCoupons(couponIds, userId, times);
        } catch (Exception e) {
            Logger.error("批量领取优惠券接口出错:", e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_COUPON_PULL_ALREADY, "领取已达上限，感谢您的参与");
        }
    }

    /**
     * 区别与下面这个接口：复用兑换码接口是一个兑换码可以多次使用，这里是使用一次。
     * 1.先生成兑换的优惠券
     * 2.生成多张不同兑换码的code
     * 3.更新用户数据进去。
     *
     * @param couponCode
     * @return
     */
    @ApiOperation(value = "一次性兑换码-兑换接口", notes = "根据兑换码兑换全品类优惠券接口，兑换码逻辑是一个码只能使用一次。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponCode", value = "兑换码")
    })
    @PostMapping("/redemptionCode")
    public ResponseJson redemptionCode(
            @ApiParam(value = "couponCode", required = true) @RequestParam(value = "couponCode") String couponCode) {
        try {
            if (StringUtil.isEmpty(couponCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return couponInfoService.redemptionCode(couponCode);
        } catch (Exception e) {
            Logger.error("兑换码兑换接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 1.先生成兑换的优惠券
     * 2.生成多张一样兑换码的code
     * 3.更新用户数据进去。
     *
     * @param couponCode
     * @return
     */
    @ApiOperation(value = "复用兑换码-兑换接口", notes = "根据兑换码兑换全品类优惠券接口，兑换码逻辑是一个码可以使用多次，但限制每个人只能兑换一次")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponCode", value = "兑换码", required = true),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "领取成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 14, message = "兑换码兑换成功"),
            @ApiResponse(code = 15, message = "该兑换码已经兑换过了")
    })
    @PostMapping("/reuseRedemptionCode")
    public ResponseJson reuseRedemptionCode(
            @RequestParam(value = "couponCode") String couponCode,
            @RequestParam(value = "userId") String userId,
            HttpServletRequest request) {
        try {
            if (StringUtil.isEmpty(couponCode) || StringUtil.isEmpty(userId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return couponInfoService.reuseRedemptionCode(couponCode,userId);
        } catch (Exception e) {
            Logger.error("兑换码兑换接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "查询用户优惠券接口", notes = "查询用户优惠券接口,不分页。")
    @PostMapping("/showUserCouponList")
    public ResponseJson showUserCouponList() {
        try {
            return couponInfoService.getUserCouponList();
        } catch (Exception e) {
            Logger.error("查询用户优惠券接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "注册时系统发放生成新人优惠券接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true)
    })
    @PostMapping("/createNewCustomerCoupons")
    public ResponseJson createNewCustomerCoupons(HttpServletRequest request) {
        try {
            String userId = request.getParameter("userId");
            Logger.info("------------------------------------用户userId：" + userId);
            if (StringUtil.isEmpty(userId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return couponInfoService.createNewCustomerCoupons(userId);
        } catch (Exception e) {
            Logger.error("领取优惠券接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "创建优惠券接口，未来有后台（平台）管理系统时可以直接调用这个接口生成。目前用postman来生成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coupon_name", value = "优惠券名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "start_time", value = "优惠开始时间,不传系统默认为当前时间", paramType = "query", required = false),
            @ApiImplicitParam(name = "end_time", value = "优惠结束时间", paramType = "query", required = true),
            @ApiImplicitParam(name = "validity_day", value = "优惠券有效天数,优惠券类型为1时会用到，但非必需", paramType = "query", required = false),
            @ApiImplicitParam(name = "denomination", value = "优惠金额", paramType = "query", required = true),
            @ApiImplicitParam(name = "discount_rate", value = "优惠折扣，和优惠活动金额是互斥的", paramType = "query", required = true),
            @ApiImplicitParam(name = "limit_type", value = "优惠类型 1：全品类，2：限定商品", paramType = "query", required = true),
            @ApiImplicitParam(name = "coupon_use_system", value = "优惠券面向的系统 10:到家C,20:格子柜,30:到家B", paramType = "query", required = true),
            @ApiImplicitParam(name = "coupon_type", value = "优惠券类型 10:新人券-系统发放,20:新人券-自领,30:普通券", paramType = "query", required = true),
            @ApiImplicitParam(name = "start_fee", value = "最小订单金额", paramType = "query", required = true),
            @ApiImplicitParam(name = "remark", value = "优惠券描述", paramType = "query", required = true)
    })
    @PostMapping("/createCouponInfo")
    public ResponseJson createCouponInfo(HttpServletRequest request, @RequestBody String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (StringUtil.isEmpty(jsonObject.getString("coupon_type"))) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "优惠券类型为空!");
            }
            if (StringUtil.isEmpty(jsonObject.getString("coupon_use_system"))) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "优惠券面向的系统为空!");
            }
            if (StringUtil.isEmpty(jsonObject.getString("coupon_name"))) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "优惠券名称为空!");
            }
            if (StringUtil.isEmpty(jsonObject.getString("end_time"))) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "优惠结束时间为空!");
            }
            if (StringUtil.isEmpty(jsonObject.getString("limit_type"))) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "优惠类型为空!");
            }
            if (StringUtil.isEmpty(jsonObject.getString("denomination")) && StringUtil.isEmpty(jsonObject.getString("discount_rate"))) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "优惠券金额或者折扣为空!");
            }
            if (StringUtil.isEmpty(jsonObject.getString("start_fee"))) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "最小订单金额为空!");
            }
            return couponInfoService.createCouponInfo(json);
        } catch (Exception e) {
            Logger.error("创建优惠券接口出错:" + e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "用户手动领取新人优惠券大礼包接口")
    @ApiResponses({
            @ApiResponse(code = 0, message = "领取成功!"),
            @ApiResponse(code = 1, message = "领取异常!"),
            @ApiResponse(code = 208, message = "来晚啦 大礼包已经发完了 明天再过来看看吧"),
            @ApiResponse(code = 209, message = "新人礼包您已经领过啦")
    })
    @PostMapping("/receiveNewCustomerCoupons")
    public ResponseJson receiveNewCustomerCoupons(HttpServletRequest request) {
        try {
            String userId = request.getParameter("userId");
            Logger.info("--------------153----------------------用户userId：" + userId);
            if (StringUtil.isEmpty(userId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return couponInfoService.receiveNewCustomerCoupons(userId);
        } catch (Exception e) {
            Logger.error("领取优惠券接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "查询新人优惠券大礼包接口")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "操作异常!"),
    })
    @PostMapping("/getCustomerCouponsSpree")
    public ResponseJson getCustomerCouponsSpree(HttpServletRequest request) {
        try {
            return couponInfoService.getCustomerCouponsSpree();
        } catch (Exception e) {
            Logger.error("领取优惠券接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "蓝牙预售活动,判断是否为首次分享，首次分享")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "操作异常!"),
    })
    @PostMapping("/getShareCoupon")
    public ResponseJson getShareCoupon(@RequestParam("skuId") String skuId) {
        try {
            return couponInfoService.getShareCoupon(skuId);
        } catch (Exception e) {
            Logger.error("领取分享优惠券接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
