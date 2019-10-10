package com.ly.mt.center.data.share.controller;

import com.ly.mt.center.data.share.entity.ShareGoodsPictureDel;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "分享商品图片接口")
@RestController
@RequestMapping("/data/center/shareGoodsPictureDel")
public class ShareGoodsPictureDelController {
    @ApiOperation(value = "保存ShareGoodsPictureDel")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShareGoodsPictureDel")
    public ResponseJson insertShareGoodsPictureDel(@RequestBody ShareGoodsPictureDel shareGoodsPictureDel) {
        return null;
    }


    @ApiOperation(
            value = "删除ShareGoodsPictureDel",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShareGoodsPictureDel")
    public ResponseJson deleteShareGoodsPictureDel() {
        return null;
    }


    @ApiOperation(
            value = "更新ShareGoodsPictureDel",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShareGoodsPictureDel")
    public ResponseJson updateShareGoodsPictureDel(@RequestBody ShareGoodsPictureDel shareGoodsPictureDel) {
        return null;
    }


    @ApiOperation(value = "查询ShareGoodsPictureDel", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShareGoodsPictureDel")
    public ResponseJson getShareGoodsPictureDel() {
        return null;
    }
}