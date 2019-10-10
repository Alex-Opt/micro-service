package com.ly.mt.center.data.basic.controller;

import com.ly.mt.center.data.basic.entity.BasicArea;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "行政区域信息接口")
@RestController
@RequestMapping("/data/center/basicArea")
public class BasicAreaController {
    @ApiOperation(value = "保存BasicArea")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserBasicArea")
    public ResponseJson insertBasicArea(@RequestBody BasicArea basicArea) {
        return null;
    }


    @ApiOperation(
            value = "删除BasicArea",
            notes = "删除条件id、m_id不能同时为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteBasicArea")
    public ResponseJson deleteBasicArea() {
        return null;
    }


    @ApiOperation(
            value = "更新BasicArea",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id、m_id不能同时为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateBasicArea")
    public ResponseJson updateBasicArea(@RequestBody BasicArea basicArea) {
        return null;
    }


    @ApiOperation(
            value = "查询BasicArea",
            notes = "1、查询条件id、m_id、pid、name、m_name不能为空  \n" +
                    "2、name、m_name为模糊查询,并且只支持末尾模糊查询"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管易区域主键id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "管易区域名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "m_id", value = "商城区域主键id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "m_name", value = "商城区域名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pid", value = "区域父id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getBasicArea")
    public ResponseJson getBasicArea() {
        return null;
    }
}