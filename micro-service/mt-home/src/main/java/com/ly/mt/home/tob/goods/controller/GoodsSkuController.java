package com.ly.mt.home.tob.goods.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.tob.goods.service.GoodsSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品sku控制层
 *
 * @author: linan
 * @date: 2019/7/26
 */
@Api(value = "商品sku控制层", tags = {"商品sku控制层"})
@RestController
@RequestMapping("/home/goodsSku")
public class GoodsSkuController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    GoodsSkuService goodsSkuService;

    @ApiOperation(value = "商品sku数据查询接口", notes = "商品sku数据查询")
    @PostMapping("/queryGoodsSku")
    public ResponseJson queryGoodsSku(@ApiParam(value = "商品spuId", required = true) @RequestParam(value = "spuId") String spuId,
                                      @ApiParam(value = "商品属性id组合", required = true) @RequestParam(value = "attributes") String attributes) {
        Assert.hasText(spuId, "参数为空");
        Assert.hasText(attributes, "参数为空");
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,
                goodsSkuService.getGoodsSkuInfoBySpuIdAndAttr(spuId, attributes));
    }
}
