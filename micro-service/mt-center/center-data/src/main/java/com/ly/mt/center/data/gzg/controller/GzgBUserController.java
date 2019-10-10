package com.ly.mt.center.data.gzg.controller;

import com.ly.mt.center.data.gzg.entity.GzgBUser;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "格子柜B端用户接口")
@RestController
@RequestMapping("/data/center/gzgBUser")
public class GzgBUserController {
    @ApiOperation(value = "保存GzgBUser")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserGzgBUser")
    public ResponseJson insertGzgBUser(@RequestBody GzgBUser gzgBUser) {
        return null;
    }


    @ApiOperation(
            value = "删除GzgBUser",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteGzgBUser")
    public ResponseJson deleteGzgBUser() {
        return null;
    }


    @ApiOperation(
            value = "更新GzgBUser",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateGzgBUser")
    public ResponseJson updateGzgBUser(@RequestBody GzgBUser gzgBUser) {
        return null;
    }


    @ApiOperation(value = "查询GzgBUser", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getGzgBUser")
    public ResponseJson getGzgBUser() {
        return null;
    }
}