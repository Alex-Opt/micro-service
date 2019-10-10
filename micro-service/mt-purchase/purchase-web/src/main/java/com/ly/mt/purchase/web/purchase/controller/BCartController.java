package com.ly.mt.purchase.web.purchase.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.method.PurchaseMethodEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.purchase.web.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_NOT_LOGIN_ERROR;
import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR;
import static com.ly.mt.core.common.method.MMethodEnum.*;
import static com.ly.mt.core.common.method.MMethodEnum.BUYCAR_SYNC_CAR_SKU;
import static com.ly.mt.core.common.method.PurchaseMethodEnum.*;

@Api(description = "B端购物车接口")
@RestController("/b/cart")
public class BCartController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BCartController.class);

    @ApiOperation(value = "取得购物车信息", notes = "返回购物车类BuyCar")
    @PostMapping(value = "/getPurchaseCart")
    @ResponseBody
    public JSONObject getPurchaseCart(HttpServletRequest request) {
        String buyerId = getLoginUserId(request);
        if (StringUtil.isEmpty(buyerId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("buyerId", buyerId);

        try {
            JSONObject jo = super.callPurchaseServer(PURCHASE_CART_GET_CART, jsonObject);
            return jo;
        } catch (Exception e) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }


    @ApiOperation(value = "增加购物车信息", notes = "操作成功返回 '操作成功'")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品skuId", paramType = "query", required = true),
            @ApiImplicitParam(name = "num", value = "增加的商品数量", paramType = "query", required = true),
    })
    @PostMapping(value = "/addCart")
    @ResponseBody
    public JSONObject addCart(@RequestParam(value = "skuId") String skuId,
                             @RequestParam(value = "num") String num,
                             HttpServletRequest request) {
        String userId = getLoginUserId(request);
        if (StringUtil.isEmpty(userId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        if (StringUtil.isEmpty(skuId) || StringUtil.isEmpty(num) || !StringUtil.isNumber(num)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("skuId", skuId);
        jsonObject.put("num", num);
        return callPurchaseServer(PURCHASE_CART_ADD_CART_SKU, jsonObject);
    }

    @ApiOperation(value = "减少购物车商品数量", notes = "操作成功返回 '操作成功'")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品skuId", paramType = "query", required = true),
            @ApiImplicitParam(name = "num", value = "减少的商品数量", paramType = "query", required = true),
    })
    @PostMapping(value = "/subCartSkuNum")
    @ResponseBody
    public JSONObject subCartSkuNum(@RequestParam(value = "skuId") String skuId,
                                @RequestParam(value = "num") String num,
                                HttpServletRequest request) {

        String buyerId = getLoginUserId(request);
        if (StringUtil.isEmpty(buyerId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("skuId", skuId);
        jsonObject.put("num", num);
        jsonObject.put("buyerId", buyerId);

        try {
            JSONObject jo = super.callPurchaseServer(PURCHASE_CART_SUB_CART_SKU_NUM, jsonObject);
            return jo;
        } catch (Exception e) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }


    @ApiOperation(value = "批量删除购物车商品", notes = "操作成功返回 '操作成功'")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品skuId的集合，如果多个商品，用逗号隔开（不是数组形式），比如：36456641,4651324", paramType = "query", required = true),
    })
    @PostMapping(value = "/delCartSku")
    @ResponseBody
    public JSONObject delCartSku(@RequestParam(value = "skuId") String skuId,
                             HttpServletRequest request) {
        String buyerId = getLoginUserId(request);
        if (StringUtil.isEmpty(buyerId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", buyerId);
        jsonObject.put("skuId", skuId);
        jsonObject.put("buyerId", buyerId);
        try {
            JSONObject jo = super.callPurchaseServer(PURCHASE_CART_DEL_CART_SKU, jsonObject);
            return jo;
        } catch (Exception e) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }


    @ApiOperation(value = "登录后，同步购物车信息", notes = "参数CarSku类，加两个属性，skuId，num")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carSkuList", value = "商品skuId的集合，如果多个商品，用逗号隔开（不是数组形式），比如：36456641,4651324", paramType = "query", required = true),
    })
    @PostMapping(value = "/syncCart")
    public JSONObject syncCart(@RequestParam(value = "cartSkuList") String cartSkuList,
                                 HttpServletRequest request) {
        String userId = getLoginUserId(request);
        if (StringUtil.isEmpty(userId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("skuIds", cartSkuList);

        try {
            JSONObject jo = super.callPurchaseServer(PURCHASE_CART_SYNC_CART_SKU, jsonObject);
            return jo;
        } catch (Exception e) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }


    private String getRequestParam(String buyerId, HttpServletRequest request) {
        String skuId = request.getParameter("skuId");
        String num = request.getParameter("num");

        Map<String, String> map = new HashMap<>();
        map.put("buyerId", buyerId);
        map.put("skuId", skuId);
        map.put("num", num);

        return JSONObject.toJSONString(map);

    }
}
