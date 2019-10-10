package com.ly.mt.center.data.basic.controller;

import com.ly.mt.center.data.basic.entity.CityOneHour;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "一小时达开通城市接口")
@RestController
@RequestMapping("/data/center/cityOneHour")
public class CityOneHourController {
    @ApiOperation(value = "保存CityOneHour")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserCityOneHour")
    public ResponseJson insertCityOneHour(@RequestBody CityOneHour cityOneHour) {
        return null;
    }


    @ApiOperation(
            value = "删除CityOneHour",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteCityOneHour")
    public ResponseJson deleteCityOneHour() {
        return null;
    }


    @ApiOperation(
            value = "更新CityOneHour",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateCityOneHour")
    public ResponseJson updateCityOneHour(@RequestBody CityOneHour cityOneHour) {
        return null;
    }


    @ApiOperation(value = "查询CityOneHour", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getCityOneHour")
    public ResponseJson getCityOneHour() {
        return null;
    }
}