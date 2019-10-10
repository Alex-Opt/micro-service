package com.ly.mt.cabinet.b.replenish.controller;

import com.ly.mt.cabinet.b.common.annotation.CustomLog;
import com.ly.mt.cabinet.b.replenish.service.ReplenishmentShowCaseService;
import com.ly.mt.cabinet.b.replenish.vo.CabinetZgReplenishOrderVo;
import com.ly.mt.cabinet.b.replenish.vo.GoodsAddZgVo;
import com.ly.mt.cabinet.b.replenish.vo.GoodsSkuInfoVo;
import com.ly.mt.cabinet.b.replenish.vo.GoodsSpuInfoVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * @author wanghongliang
 * @description 格子柜B-补货-接口层。
 * @date 2019-08-26
 */
@Api(description = "CABINETB端用户-展柜补货操作接口")
@RestController
@RequestMapping("/cabinet/b/showCase/replenish")
public class ReplenishmentShowCaseController {
    private static final Logger logger = LoggerFactory.getLogger(ReplenishmentShowCaseController.class);

    @Resource
    private ReplenishmentShowCaseService replenishmentShowCaseService;


    @ApiOperation(value = "获取展柜待补货列表")
    @CustomLog
    @PostMapping("/getZgReplenishList")
    public ResponseJson<List<CabinetZgReplenishOrderVo>> getZgReplenishList() {
        try {
            return replenishmentShowCaseService.getReplenishZgOrderList();
        } catch (Exception e) {
            logger.error("CABINET-B-APP-获取展柜待补货列表出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "获取spu列表")
    @CustomLog
    @PostMapping("/getSpuInfoList")
    public ResponseJson<GoodsSpuInfoVo> getSpuInfoList() {
        try {
            return replenishmentShowCaseService.getSpuInfoList();
        } catch (Exception e) {
            logger.error("CABINET-B-APP-获取spu列表出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "获取此spu下sku列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spuId", value = "spuId", paramType = "query", required = true),
            @ApiImplicitParam(name = "cabinetCode", value = "货柜编号", paramType = "query", required = true)
    })
    @CustomLog
    @PostMapping("/getSkuInfoList")
    public ResponseJson<GoodsSkuInfoVo> getSkuInfoList(@RequestParam(value = "spuId") String spuId, @RequestParam(value = "cabinetCode") String cabinetCode) {
        // 校验参数
        if (StringUtil.isEmpty(spuId)||StringUtil.isEmpty(cabinetCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"spuId不能为空/货柜编号不能为空!");
        }
        try {
            return replenishmentShowCaseService.getSkuInfoList(spuId,cabinetCode);
        } catch (Exception e) {
            logger.error("CABINET-B-APP-获取此spu下sku列表报错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "添加商品库存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "GoodsAddZgVo", value = "添加商品对象", paramType = "query", required = true)

    })
    @CustomLog
    @PostMapping("/addZgGoodsStock")
    public ResponseJson addZgGoodsStock(@RequestBody GoodsAddZgVo goodsAddZgVo) {
        // 校验参数
        if (StringUtil.isEmpty(goodsAddZgVo.getCabinetCode())||goodsAddZgVo.getGoodsAddVoList()==null||goodsAddZgVo.getGoodsAddVoList().size()==0) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"货柜编号/添加商品对象不能为空!");
        }
        try {
            return replenishmentShowCaseService.addGoodsStock(goodsAddZgVo);
        } catch (Exception e) {
            logger.error("CABINET-B-APP-添加商品库存报错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
