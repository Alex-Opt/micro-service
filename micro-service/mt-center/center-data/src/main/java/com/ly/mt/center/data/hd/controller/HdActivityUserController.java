package com.ly.mt.center.data.hd.controller;

import com.ly.mt.center.data.hd.entity.HdActivityUser;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "接口")
@RestController
@RequestMapping("/data/center/hdActivityUser")
public class HdActivityUserController {
    @ApiOperation(value = "保存HdActivityUser")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserHdActivityUser")
    public ResponseJson insertHdActivityUser(@RequestBody HdActivityUser hdActivityUser) {
        return null;
    }


    @ApiOperation(
            value = "删除HdActivityUser",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteHdActivityUser")
    public ResponseJson deleteHdActivityUser() {
        return null;
    }


    @ApiOperation(
            value = "更新HdActivityUser",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateHdActivityUser")
    public ResponseJson updateHdActivityUser(@RequestBody HdActivityUser hdActivityUser) {
        return null;
    }


    @ApiOperation(value = "查询HdActivityUser", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getHdActivityUser")
    public ResponseJson getHdActivityUser() {
        return null;
    }
}