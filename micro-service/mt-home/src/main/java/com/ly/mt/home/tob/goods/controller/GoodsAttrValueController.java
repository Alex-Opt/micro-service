package com.ly.mt.home.tob.goods.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.tob.goods.service.GoodsAttrValueService;
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
 * 商品规格接口
 *
 * @author: linan
 * @date: 2019/7/26
 */
@Api(value = "商品规格接口", tags = {"商品规格接口"})
@RestController
@RequestMapping("/home/goodsAttr")
public class GoodsAttrValueController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    GoodsAttrValueService goodsAttrValueService;

    @ApiOperation(value = "商品规格接口查询接口", notes = "商品规格接口")
    @PostMapping("/getAttrBySpu")
    public ResponseJson queryGoodsSku(@ApiParam(value = "商品spuId", required = true) @RequestParam(value = "spuId") String spuId) {
        Assert.hasText(spuId, "参数为空");
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,
                goodsAttrValueService.getGoodsSpuAttrValueBySpuId(spuId));
    }
}
