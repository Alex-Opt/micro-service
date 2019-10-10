package com.ly.mt.purchase.web.purchase.controller;


import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.purchase.web.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;
import static com.ly.mt.core.common.method.PurchaseMethodEnum.*;

/**
 * B端商品详情
 *
 * @author yls
 * @date 20190616
 */
@Api(description = "B端商品接口")
@RestController("/b/commodity")
public class CommodityDetailController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommodityDetailController.class);

    @ApiOperation(value = "获取B端商品信息接口", notes = "获取B端商品信息")
    @PostMapping(value = "/getCommodityInfo")
    @ResponseBody
    public JSONObject getCommodityInfo(@ApiParam(value = "商品类目ID", required = true) @RequestParam(value = "cId") String cId,
                                        @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                        @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows) {
        if (StringUtil.isEmpty(cId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cId", cId);
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        return callPurchaseServer(PURCHASE_COMMODITY_INFO, jsonObject);
    }

    @ApiOperation(value = "通过spuId获取B端商品接口", notes = "通过spuId获取B端商品信息")
    @PostMapping(value = "/getCommodityBySpuId")
    @ResponseBody
    public JSONObject getCommodityBySpuId(@ApiParam(value = "商品spuId", required = true) @RequestParam(value = "spuId") String spuId) {
        if (StringUtil.isEmpty(spuId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("spuId", spuId);
        return callPurchaseServer(PURCHASE_COMMODITY_BY_SPUID, jsonObject);
    }

    @ApiOperation(value = "获取B端商品详情信息接口", notes = "获取B端商品详情信息")
    @PostMapping(value = "/getCommodityDetail")
    @ResponseBody
    public JSONObject getCommodityDetail(@ApiParam(value = "商品skuId", required = true) @RequestParam(value = "skuId") String skuId) {
        if (StringUtil.isEmpty(skuId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("skuId", skuId);
        return callPurchaseServer(PURCHASE_COMMODITY_DETAIL, jsonObject);
    }

    @ApiOperation(value = "获取B端商品阶梯价信息接口", notes = "获取B端商品阶梯价信息")
    @PostMapping(value = "/getCommodityLadderPrice")
    @ResponseBody
    public JSONObject getCommodityLadderPrice(@ApiParam(value = "商品skuId", required = true) @RequestParam(value = "skuId") String skuId) {
        if (StringUtil.isEmpty(skuId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("skuId", skuId);
        return callPurchaseServer(PURCHASE_COMMODITY_LADDER_PRICE, jsonObject);
    }

    @ApiOperation(value = "查询B端店铺优惠劵信息接口", notes = "查询B端店铺优惠劵信息")
    @PostMapping(value = "/getShopCouponInfo")
    @ResponseBody
    public JSONObject getShopCouponInfo(@ApiParam(value = "店铺shopId", required = true) @RequestParam(value = "shopId") String shopId,
                                        HttpServletRequest request) {
        if (StringUtil.isEmpty(shopId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        String userId = getLoginUserId(request);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shopId", shopId);
        jsonObject.put("userId", userId);
        return callPurchaseServer(PURCHASE_COMMODITY_LADDER_PRICE, jsonObject);
    }


}
