package com.ly.mt.order.web.border.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.shop.ShopAddress;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.order.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.method.OrderMethodEnum.*;


@Api(description = "到家B端-我的抢单模块接口")
@RestController
@RequestMapping("/order/bOrder/myInfo")
public class BOrderMyInfoController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BOrderMyInfoController.class);


    @ApiOperation(value = "我的进货单信息接口", notes = "查询店铺的进货单列表  id和page,rows二选一必填")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false),
            @ApiImplicitParam(name = "page", value = "page", required = false),
            @ApiImplicitParam(name = "rows", value = "rows", required = false),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/shopPurchaseList")
    public JSONObject getShopPurchaseList(@RequestParam(value = "id", required = false) String id,
                                          @RequestParam(value = "page", required = true) String page,
                                          @RequestParam(value = "rows", required = true) String rows) {


        JSONObject param = new JSONObject();
        param.put("id", (id == null || "".equals(id)) ? "" : id);
        param.put("page", page);
        param.put("rows", rows);
        return callOrderServer(MY_INFO_SHOP_PURCHASE_LIST, param);
    }


    @ApiOperation(value = "我的进货库存", notes = "根据用户id查询库存列表，无需入参")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/myPurchaseStockList")
    public JSONObject myPurchaseStockList(HttpServletRequest request) {
        return callOrderServer(MY_INFO_SHOP_PURCHASE_STACK, new JSONObject());
    }


    @ApiOperation(value = "我的-优惠券列表", notes = "查询优惠券列表,无需入参")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/couponCodeList")
    public JSONObject getCouponCodeList(HttpServletRequest request) {
        return callOrderServer(MY_INFO_SHOP_COUPON_INFO, new JSONObject());
    }


    @ApiOperation(value = "我的-账户与安全", notes = "根据用户id查询当前用户账户信息")
    @ApiImplicitParam(name = "request", value = "用户id,这里后台可以从session中获取，不用前端传入参")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/myUserAccountInfo")
    public JSONObject myUserAccountInfo(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        return callOrderServer(MY_INFO_USER_ACCOUNT_INFO, jsonObject);
    }


    @ApiOperation(value = "我的-查询默认地址接口", notes = "根据店铺shopId查询店铺默认发货地址,无入参")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/shopDefaultAddress")
    public JSONObject getShopDefaultAddress(HttpServletRequest request) {
        return callOrderServer(MY_INFO_SHOP_DEFAULT_ADDRESS, new JSONObject());
    }

    @ApiOperation(value = "我的-查询店铺地址", notes = "根据id查询店铺地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/getShopAddressById")
    public JSONObject getShopAddressById(HttpServletRequest request, @RequestParam(value = "id") String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        return callOrderServer(MY_INFO_SHOP_ADDRESS_BY_ID, jsonObject);
    }


    @ApiOperation(value = "用户名修改接口", notes = "根据用户-userId修改用户名-loginName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "小B用户修改后的用户名", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/modifyLoginName")
    public JSONObject modifyLoginName(@RequestParam(value = "loginName") String loginName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginName", loginName);
        return callOrderServer(MY_INFO_UPDATE_LOGIN_NAME, jsonObject);
    }


    @ApiOperation(value = "查询小B店铺的地址列表接口", notes = "根据用户-userId，shopId查询小B店铺的地址列表,无需入参")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/shopAddressList")
    public JSONObject getShopAddressList(HttpServletRequest request) {
        return callOrderServer(MY_INFO_SHOP_ADDRESS_LIST, new JSONObject());
    }


    @ApiOperation(value = "小B店铺的新增地址接口", notes = "小B店铺的新增地址接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provinceCode", value = "省（直辖市）代码", required = true),
            @ApiImplicitParam(name = "provinceName", value = "省（直辖市）名称", required = true),
            @ApiImplicitParam(name = "cityCode", value = "省辖市代码", required = true),
            @ApiImplicitParam(name = "cityName", value = "省辖市名称", required = true),
            @ApiImplicitParam(name = "districtCode", value = "县区代码", required = true),
            @ApiImplicitParam(name = "districtName", value = "县区名称", required = true),
            @ApiImplicitParam(name = "userAddress", value = "详细地址", required = true),
            @ApiImplicitParam(name = "isDefault", value = "是否默认地址  0-非默认地址  1-默认地址", required = true),
            @ApiImplicitParam(name = "sendLon", value = "地址经度", required = true),
            @ApiImplicitParam(name = "sendLat", value = "地址纬度", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/addShopAddress")
    public JSONObject addShopAddress(@RequestBody String addressJson) {
        try {
            JSONObject jsonObject = JsonUtil.parseAndRequire(ShopAddress.class, addressJson, new String[]{"userAddress", "isDefault", "sendLon", "sendLat"});
            return callOrderServer(MY_INFO_ADD_SHOP_ADDRESS, jsonObject);
        } catch (Exception e) {
            LOGGER.error("入参转成实体类异常：" + e.getMessage(), e);
            return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @ApiOperation(value = "更新店铺默认收货地址接口", notes = "根据id更新查询小B店铺的默认地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/updateDefaultShopAddress")
    public JSONObject updateDefaultShopAddress(@RequestParam(value = "id") String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        return callOrderServer(MY_INFO_UPDATE_DEFAULT_SHOP_ADDRESS, jsonObject);
    }

    @ApiOperation(value = "修改店铺地址接口", notes = "修改店铺地址接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true),
            @ApiImplicitParam(name = "provinceCode", value = "省（直辖市）代码", required = true),
            @ApiImplicitParam(name = "provinceName", value = "省（直辖市）名称", required = true),
            @ApiImplicitParam(name = "cityCode", value = "省辖市代码", required = true),
            @ApiImplicitParam(name = "cityName", value = "省辖市名称", required = true),
            @ApiImplicitParam(name = "districtCode", value = "县区代码", required = true),
            @ApiImplicitParam(name = "districtName", value = "县区名称", required = true),
            @ApiImplicitParam(name = "userAddress", value = "详细地址", required = true),
            @ApiImplicitParam(name = "isDefault", value = "是否默认地址  0-非默认地址  1-默认地址", required = true),
            @ApiImplicitParam(name = "sendLon", value = "地址经度", required = true),
            @ApiImplicitParam(name = "sendLat", value = "地址纬度", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/updateShopAddress")
    public JSONObject updateShopAddress(@RequestBody String addressJson) {
        try {
            JSONObject jsonObject = JsonUtil.parseAndRequire(ShopAddress.class, addressJson, new String[]{"id", "userAddress", "isDefault", "sendLon", "sendLat"});
            return callOrderServer(MY_INFO_UPDATE_SHOP_ADDRESS, jsonObject);
        } catch (Exception e) {
            LOGGER.error("入参转成实体类异常：" + e.getMessage(), e);
            return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_SYSTEM_ERROR);
        }
    }


    @ApiOperation(value = "更换手机号接口", notes = "更换手机号接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "手机动态验证码", paramType = "query", required = true),
            @ApiImplicitParam(name = "verifyCode", value = "图片验证码", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/modifyMobile")
    public JSONObject modifyMobile(@RequestParam(value = "mobile") String mobile,
                                   @RequestParam(value = "dynamicCode") String dynamicCode,
                                   @RequestParam(value = "verifyCode") String verifyCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dynamicCode", dynamicCode);
        jsonObject.put("mobile", mobile);
        jsonObject.put("verifyCode", verifyCode);
        return callOrderServer(MY_INFO_MODIFY_MOBILE, jsonObject);
    }


}