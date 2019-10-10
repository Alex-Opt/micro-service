package com.ly.mt.home.tob.goods.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.home.tob.goods.service.GoodsSpuService;
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
import javax.servlet.http.HttpServletRequest;


/**
 * 商品spu控制层
 *
 * @author: linan
 * @date: 2019/7/26
 */
@Api(value = "商品spu控制层", tags = {"商品spu控制层"})
@RestController
@RequestMapping("/home/goodsSpu")
public class GoodsSpuController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    GoodsSpuService goodsSpuService;


    @ApiOperation(value = "商品spu信息，根据id查询接口", notes = "根据id查询商品spu数据")
    @PostMapping("/goodsSpuQuery")
    public ResponseJson goodsSpuQuery(@ApiParam(value = "商品spuId", required = true) @RequestParam(value = "id") String id, HttpServletRequest request) {
        Assert.hasText(id, "参数为空");
        return goodsSpuService.queryGoodsSpuById(id);
    }

    @ApiOperation(value = "根据类目查询商品spu数据接口(分类为空查询全部)", notes = "根据类目查询商品spu")
    @PostMapping("/queryGoodsSpuByCategroy")
    public ResponseJson queryGoodsSpuByCategroy(@ApiParam(value = "商品分类的id") @RequestParam(value = "cId", required = false) String cId,
                                                @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                                @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows,
                                                @ApiParam(value = "排序,新品：time,销量：sale", required = true) @RequestParam(value = "sort") String sort) {
        return goodsSpuService.queryGoodsSpuByCategroy(cId, page, rows, sort);
    }

    @ApiOperation(value = "查询雾化单商品spu数据接口", notes = "根据雾化单类目查询商品spu数据")
    @PostMapping("/queryGoodsSpuByAerosol")
    public ResponseJson queryGoodsSpuByAerosol(@ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                               @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows) {
        return goodsSpuService.queryGoodsSpuByAerosol(page, rows);
    }

    @ApiOperation(value = "商品分类查询接口", notes = "商品分类查询")
    @PostMapping("/queryCategroyList")
    public ResponseJson queryCategroyList(@ApiParam(value = "商品分类上级id, 获取第一级分类数据传入参数为-1", required = true) @RequestParam(value = "parentId") String parentId) {
        Assert.hasText(parentId, "参数为空");
        return goodsSpuService.queryCategroyList(parentId);
    }

    @ApiOperation(value = "top5热销榜", notes = "top5热销榜")
    @PostMapping("/queryTopList")
    public ResponseJson queryTopList(@ApiParam(value = "商品分类的id") @RequestParam(value = "cId", required = false) String cId) {
        return goodsSpuService.getListTop5ByCid(cId);
    }

}
