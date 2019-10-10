package com.ly.mt.mall.h5.storecar.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.h5.storecar.service.StoreCarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 用户收藏车接口
 */
@Api(description = "用户收藏车操作接口")
@RestController
@RequestMapping("/mall/h5/store")
public class StoreCarController {

    private final static Logger Logger = LoggerFactory.getLogger(StoreCarController.class);

    @Resource
    StoreCarService storeCarService;

    @ApiOperation(value = "取得收藏车信息", notes = "返回收藏车类BuyCar")
    @PostMapping(value = "/getStore")
    @ResponseBody
    public ResponseJson getCar(HttpServletRequest request) {
        try {
            return storeCarService.getStoreCar();
        } catch (Exception e) {
            Logger.error("取得收藏车信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "增加收藏车信息", notes = "操作成功返回 '操作成功'")
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
            return storeCarService.addStoreCar(skuId, num);
        } catch (Exception e) {
            Logger.error("增加收藏车信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "删除收藏车商品", notes = "操作成功返回 '操作成功'")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品skuId", paramType = "query", required = true),
    })
    @PostMapping(value = "/subCar")
    @ResponseBody
    public ResponseJson subCar(@RequestParam(value = "skuId") String skuId,
                               HttpServletRequest request) {
        try {
            if (StringUtil.isEmpty(skuId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return storeCarService.subStoreCarSku(skuId);
        } catch (Exception e) {
            Logger.error("删除收藏车商品出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

}
