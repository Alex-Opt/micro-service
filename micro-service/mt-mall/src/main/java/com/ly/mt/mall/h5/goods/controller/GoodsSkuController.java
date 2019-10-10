package com.ly.mt.mall.h5.goods.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.h5.goods.service.GoodsSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;


/**
 * 商品sku控制层
 */
@Api(description = "商品sku控制层")
@RestController
@RequestMapping("/mall/h5/goodsSku")
public class GoodsSkuController {

    private final static Logger Logger = LoggerFactory.getLogger(GoodsSkuController.class);

    @Resource
    GoodsSkuService goodsSkuService;

    @ApiOperation(value = "商品sku数据查询接口", notes = "商品sku数据查询")
    @PostMapping("/queryGoodsSku")
    public ResponseJson queryGoodsSku(@ApiParam(value = "商品spuId", required = true) @RequestParam(value = "spuId") String spuId,
                                      @ApiParam(value = "商品属性id组合", required = true) @RequestParam(value = "attributes") String attributes) {
        try {
            if (StringUtil.isEmpty(spuId) || StringUtil.isEmpty(attributes)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return goodsSkuService.getGoodsSkuInfoBySpuIdAndAttr(spuId, attributes);
        } catch (Exception e) {
            Logger.error("商品sku数据查询接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "活动推广页根据spu信息查询所有的sku信息", notes = "反显sku信息")
    @ApiImplicitParam(name = "request", value = "商品spu数据")
    @PostMapping("/goodsSkuInfo")
    public ResponseJson goodsSkuInfo(@ApiParam(value = "商品spuId", required = true)
                                     @RequestParam(value = "spuId") String spuId) {
        try {
            if (StringUtil.isEmpty(spuId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return goodsSkuService.getSkuInfoBySpuIdForOk(spuId);
        } catch (Exception e) {
            Logger.error("活动推广页根据spu信息查询所有的sku信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
