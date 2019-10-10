package com.ly.mt.home.tob.buycar.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.home.tob.buycar.service.BuyCarService;
import com.ly.mt.home.tob.buycar.vo.CarVo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 购物车操作
 *
 * @author linan
 * @date 20190709
 */
@Api(value = "商家购物车操作接口", tags = {"商家购物车操作接口"})
@RestController
@RequestMapping("/home/buycar")
public class BuyCarController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    BuyCarService buyCarService;

    @ApiOperation(value = "取得购物车信息", notes = "返回购物车类BuyCar")
    @PostMapping(value = "/getCar")
    @ResponseBody
    public ResponseJson getCar() {
        return buyCarService.getBuyCar();
    }

    @ApiOperation(value = "增加购物车信息", notes = "操作成功返回 '操作成功'")
    @PostMapping(value = "/addCar")
    @ResponseBody
    public ResponseJson addCar(@RequestBody CarVo carVo) {
        return buyCarService.addCar(carVo.getParam());
    }

    @ApiOperation(value = "减少购物车商品数量", notes = "操作成功返回 '操作成功'")
    @PostMapping(value = "/subCarNum")
    @ResponseBody
    public ResponseJson subCarNum(@ApiParam(value = "skuId", required = true) @RequestParam(value = "skuId") String skuId,
                                  @ApiParam(value = "num", required = true) @RequestParam(value = "num") String num) {
        Assert.hasText(skuId, "参数为空");
        Assert.isTrue(StringUtil.isNumber(num), "参数格式异常");
        return buyCarService.subCarSkuNum(skuId, num);
    }

    @ApiOperation(value = "批量删除购物车商品", notes = "操作成功返回 '操作成功'")
    @PostMapping(value = "/subCar")
    @ResponseBody
    public ResponseJson subCar(@ApiParam(value = "商品skuId的集合，如果多个商品，用逗号隔开（不是数组形式），比如：36456641,4651324", required = true)
                               @RequestParam(value = "skuId") String skuId) {
        Assert.hasText(skuId, "参数为空");
        return buyCarService.subCarSku(skuId);
    }

}
