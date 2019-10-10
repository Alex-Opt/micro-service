package com.ly.mt.center.data.rotation.controller;

import com.ly.mt.center.data.rotation.entity.RotationInfo;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "轮播图接口")
@RestController
@RequestMapping("/data/center/rotationInfo")
public class RotationInfoController {
    @ApiOperation(value = "保存RotationInfo")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserRotationInfo")
    public ResponseJson insertRotationInfo(@RequestBody RotationInfo rotationInfo) {
        return null;
    }


    @ApiOperation(
            value = "删除RotationInfo",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteRotationInfo")
    public ResponseJson deleteRotationInfo() {
        return null;
    }


    @ApiOperation(
            value = "更新RotationInfo",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateRotationInfo")
    public ResponseJson updateRotationInfo(@RequestBody RotationInfo rotationInfo) {
        return null;
    }


    @ApiOperation(value = "查询RotationInfo", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getRotationInfo")
    public ResponseJson getRotationInfo() {
        return null;
    }


    @ApiOperation("查询RotationInfo集合")
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/listRotationInfo")
    public ResponseJson listRotationInfo() {
        return null;
    }
}