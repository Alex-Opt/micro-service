package com.ly.mt.center.data.activity.controller;

import com.ly.mt.center.data.activity.entity.ActivityInfo;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "促销优惠活动信息接口")
@RestController
@RequestMapping("/data/center/activityInfo")
public class ActivityInfoController {
    @ApiOperation(value = "保存ActivityInfo")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserActivityInfo")
    public ResponseJson insertActivityInfo(@RequestBody ActivityInfo activityInfo) {
        return null;
    }


    @ApiOperation(
            value = "删除ActivityInfo",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteActivityInfo")
    public ResponseJson deleteActivityInfo() {
        return null;
    }


    @ApiOperation(
            value = "更新ActivityInfo",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateActivityInfo")
    public ResponseJson updateActivityInfo(@RequestBody ActivityInfo activityInfo) {
        return null;
    }


    @ApiOperation(value = "查询ActivityInfo", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getActivityInfo")
    public ResponseJson getActivityInfo() {
        return null;
    }


    @ApiOperation(value = "根据spuId查询优惠活动数据", notes = "查询条件spuId不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spu_id", value = "spuId", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getActivityInfoBySpuId")
    public ResponseJson getActivityInfoBySpuId() {
        return null;
    }
}