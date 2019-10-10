package com.ly.mt.center.data.gzg.controller;

import com.ly.mt.center.data.gzg.entity.GzgHotel;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "格子柜酒店信息接口")
@RestController
@RequestMapping("/data/center/gzgHotel")
public class GzgHotelController {
    @ApiOperation(value = "保存GzgHotel")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserGzgHotel")
    public ResponseJson insertGzgHotel(@RequestBody GzgHotel gzgHotel) {
        return null;
    }


    @ApiOperation(
            value = "删除GzgHotel",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteGzgHotel")
    public ResponseJson deleteGzgHotel() {
        return null;
    }


    @ApiOperation(
            value = "更新GzgHotel",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateGzgHotel")
    public ResponseJson updateGzgHotel(@RequestBody GzgHotel gzgHotel) {
        return null;
    }


    @ApiOperation(value = "查询GzgHotel", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getGzgHotel")
    public ResponseJson getGzgHotel() {
        return null;
    }
}