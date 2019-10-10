package com.ly.mt.mall.h5.goods.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.h5.goods.service.GoodsSpuService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;


/**
 * 商品spu控制层
 */
@Api(description = "商品spu控制层")
@RestController
@RequestMapping("/mall/h5/goodsSpu")
public class GoodsSpuController {

    private final static Logger Logger = LoggerFactory.getLogger(GoodsSpuController.class);

    @Resource
    GoodsSpuService goodsSpuService;


    @ApiOperation(value = "商品spu信息，根据id查询接口", notes = "根据id查询商品spu数据")
    @PostMapping("/goodsSpuQuery")
    public ResponseJson goodsSpuQuery(@ApiParam(value = "商品spuId", required = true) @RequestParam(value = "id") String id) {
        try {
            if (StringUtil.isEmpty(id)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return goodsSpuService.queryGoodsSpuById(id);
        } catch (Exception e) {
            Logger.error("根据id查询商品spu数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "根据类目查询商品spu数据接口", notes = "根据类目查询商品spu")
    @PostMapping("/queryGoodsSpuByCategroy")
    public ResponseJson queryGoodsSpuByCategroy(@ApiParam(value = "商品分类的id", required = true) @RequestParam(value = "cId") String cId,
                                                @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                                @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows) {
        try {
            if (StringUtil.isEmpty(cId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return goodsSpuService.queryGoodsSpuByCategroy(cId, page, rows);
        } catch (Exception e) {
            Logger.error("根据类目查询商品spu数据接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "查询雾化单商品spu数据接口", notes = "根据雾化单类目查询商品spu数据")
    @PostMapping("/queryGoodsSpuByAerosol")
    public ResponseJson queryGoodsSpuByAerosol(@ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                               @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows) {
        try {

            return goodsSpuService.queryGoodsSpuByAerosol( page, rows);
        } catch (Exception e) {
            Logger.error("查询雾化单商品spu数据接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "商品分类查询接口", notes = "商品分类查询")
    @PostMapping("/queryCategroyList")
    public ResponseJson queryCategroyList(@ApiParam(value = "商品分类上级id, 获取第一级分类数据传入参数为-1", required = true) @RequestParam(value = "parentId") String parentId) {
        try {
            if (StringUtil.isEmpty(parentId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return goodsSpuService.queryCategroyList(parentId);
        } catch (Exception e) {
            Logger.error("商品分类查询接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "查询一小时达商品spu信息", notes = "一小时达spu")
    @PostMapping("/queryHourSpu")
    public ResponseJson queryHourSpu( @RequestParam(value="page")int page,@RequestParam(value = "rows")int rows){
        try{
            return goodsSpuService.queryHourSpu(page,rows);
        }catch (Exception e){
            Logger.error("查询一小时达商品spu信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "查询一小时达spu商品信息", notes = "根据spu_id查询商品spu数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品spuId", paramType = "query", required = true)
    })
    @PostMapping("/goodsHourSpuQuery")
    public ResponseJson goodsHourSpuQuery( @RequestParam(value = "id") String id) {
        try {
            if (StringUtil.isEmpty(id)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return goodsSpuService.queryGoodsHourSpuById(id);
        } catch (Exception e) {
            Logger.error("根据spu_id查询商品spu数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "查询商品本月销量统计", notes = "根据spu_id查询商品月销量数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spuId", value = "商品spuId", paramType = "query", required = true)
    })
    @PostMapping("/countGoodsSellerEachMonth")
    public ResponseJson countGoodsSellerEachMonth( @RequestParam(value = "spuId") String spuId) {
        try {
            if (StringUtil.isEmpty(spuId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "商品spuId参数为空");
            }
            return goodsSpuService.countGoodsSellerEachMonth(spuId);
        } catch (Exception e) {
            Logger.error("根据spu_id查询商品月销量数据:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "查询指定功能下配置的商品列表信息", notes = "根据功能类型-查询配置的商品图片，价格等信息的集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemUserType", value = "面向功能类型:1-到家C实名认证后的商品展示页", paramType = "query", required = true)
    })
    @PostMapping("/querySpuConfigGoods")
    public ResponseJson querySpuConfigGoodsByUserType( @RequestParam(value = "systemUserType") String systemUserType) {
        try {
            if (StringUtil.isEmpty(systemUserType)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return goodsSpuService.querySpuConfigGoodsByUserType(systemUserType);
        } catch (Exception e) {
            Logger.error("根据功能类型-查询配置的商品集合异常:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


}
