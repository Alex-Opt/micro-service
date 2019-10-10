package com.ly.mt.mall.h5.buycar.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.h5.buycar.service.BuyCarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 购物车操作
 *
 * @author ypmu
 * @date 20190520
 */
@Api(description = "用户购物车操作接口")
@RestController
@RequestMapping("/mall/h5/car")
public class BuyCarController {

    private final static Logger Logger = LoggerFactory.getLogger(BuyCarController.class);

    @Resource
    BuyCarService buyCarService;

    @ApiOperation(value = "取得购物车信息", notes = "返回购物车类BuyCar")
    @PostMapping(value = "/getCar")
    @ResponseBody
    public ResponseJson getCar(HttpServletRequest request) {
        try {
            return buyCarService.getBuyCar();
        } catch (Exception e) {
            Logger.error("取得购物车信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "增加购物车信息", notes = "操作成功返回 '操作成功'")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品skuId", paramType = "query", required = true),
            @ApiImplicitParam(name = "num", value = "增加的商品数量", paramType = "query", required = true),
    })
    @PostMapping(value = "/addCar")
    @ResponseBody
    public ResponseJson addCar(@RequestParam(value = "skuId") String skuId,
                               @RequestParam(value = "num") String num,
                               HttpServletRequest request) {
        try {
            if (StringUtil.isEmpty(skuId) || StringUtil.isEmpty(num) || !StringUtil.isNumber(num)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return buyCarService.addCar(skuId, num);
        } catch (Exception e) {
            Logger.error("增加购物车信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "减少购物车商品数量", notes = "操作成功返回 '操作成功'")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品skuId", paramType = "query", required = true),
            @ApiImplicitParam(name = "num", value = "减少的商品数量", paramType = "query", required = true),
    })
    @PostMapping(value = "/subCarNum")
    @ResponseBody
    public ResponseJson subCarNum(@RequestParam(value = "skuId") String skuId,
                                  @RequestParam(value = "num") String num,
                                  HttpServletRequest request) {
        try {
            if (StringUtil.isEmpty(skuId) || StringUtil.isEmpty(num) || !StringUtil.isNumber(num)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return buyCarService.subCarSkuNum(skuId, num);
        } catch (Exception e) {
            Logger.error("减少购物车商品数量出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "批量删除购物车商品", notes = "操作成功返回 '操作成功'")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品skuId的集合，如果多个商品，用逗号隔开（不是数组形式），比如：36456641,4651324", paramType = "query", required = true),
    })
    @PostMapping(value = "/subCar")
    @ResponseBody
    public ResponseJson subCar(@RequestParam(value = "skuId") String skuId,
                               HttpServletRequest request) {
        try {
            if (StringUtil.isEmpty(skuId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return buyCarService.subCarSku(skuId);
        } catch (Exception e) {
            Logger.error("批量删除购物车商品出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "登录后，同步购物车信息", notes = "参数CarSku类，加两个属性，skuId，num")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carSkuList", value = "商品skuId的集合，如果多个商品，用逗号隔开（不是数组形式），比如：36456641,4651324", paramType = "query", required = true),
    })
    @PostMapping(value = "/syncBuyCar")
    public ResponseJson syncBuyCar(@RequestParam(value = "carSkuList") String carSkuList,
                                   HttpServletRequest request) {
        try {
            if (StringUtil.isEmpty(carSkuList)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return buyCarService.syncBuyCar(carSkuList);
        } catch (Exception e) {
            Logger.error("同步购物车信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
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
