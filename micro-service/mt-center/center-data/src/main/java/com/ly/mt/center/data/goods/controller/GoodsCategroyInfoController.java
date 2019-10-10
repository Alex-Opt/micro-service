package com.ly.mt.center.data.goods.controller;

import com.ly.mt.center.data.goods.entity.GoodsCategroyInfo;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "类目信息接口")
@RestController
@RequestMapping("/data/center/goodsCategroyInfo")
public class GoodsCategroyInfoController {
    @ApiOperation(value = "保存GoodsCategroyInfo")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserGoodsCategroyInfo")
    public ResponseJson insertGoodsCategroyInfo(@RequestBody GoodsCategroyInfo goodsCategroyInfo) {
        return null;
    }


    @ApiOperation(
            value = "删除GoodsCategroyInfo",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteGoodsCategroyInfo")
    public ResponseJson deleteGoodsCategroyInfo() {
        return null;
    }


    @ApiOperation(
            value = "更新GoodsCategroyInfo",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateGoodsCategroyInfo")
    public ResponseJson updateGoodsCategroyInfo(@RequestBody GoodsCategroyInfo goodsCategroyInfo) {
        return null;
    }


    @ApiOperation(value = "查询GoodsCategroyInfo", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getGoodsCategroyInfo")
    public ResponseJson getGoodsCategroyInfo() {
        return null;
    }


    @ApiOperation(value = "根据上级id查询所有自己类目数据", notes = "查询条件parent_id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parent_id", value = "上级id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getGoodsCategroyInfoByParentId")
    public ResponseJson getGoodsCategroyInfoByParentId() {
        return null;
    }
}