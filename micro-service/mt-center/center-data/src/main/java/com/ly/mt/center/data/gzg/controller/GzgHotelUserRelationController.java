package com.ly.mt.center.data.gzg.controller;

import com.ly.mt.center.data.gzg.entity.GzgHotelUserRelation;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "格子柜用户酒店关系接口")
@RestController
@RequestMapping("/data/center/gzgHotelUserRelation")
public class GzgHotelUserRelationController {
    @ApiOperation(value = "保存GzgHotelUserRelation")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserGzgHotelUserRelation")
    public ResponseJson insertGzgHotelUserRelation(@RequestBody GzgHotelUserRelation gzgHotelUserRelation) {
        return null;
    }


    @ApiOperation(
            value = "删除GzgHotelUserRelation",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteGzgHotelUserRelation")
    public ResponseJson deleteGzgHotelUserRelation() {
        return null;
    }


    @ApiOperation(
            value = "更新GzgHotelUserRelation",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateGzgHotelUserRelation")
    public ResponseJson updateGzgHotelUserRelation(@RequestBody GzgHotelUserRelation gzgHotelUserRelation) {
        return null;
    }


    @ApiOperation(value = "查询GzgHotelUserRelation", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getGzgHotelUserRelation")
    public ResponseJson getGzgHotelUserRelation() {
        return null;
    }
}