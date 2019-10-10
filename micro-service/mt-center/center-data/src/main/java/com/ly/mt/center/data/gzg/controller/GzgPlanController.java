package com.ly.mt.center.data.gzg.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.entity.GzgPlan;
import com.ly.mt.center.data.gzg.service.GzgGoodsPlanService;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "接口")
@RestController
@RequestMapping("/data/center/gzgPlan")
public class GzgPlanController {

    @Autowired
    private  GzgGoodsPlanService gzgGoodsPlanService;

    @ApiOperation(value = "保存GzgPlan")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserGzgPlan")
    public ResponseJson insertGzgPlan(@RequestBody GzgPlan gzgPlan) {
        return null;
    }


    @ApiOperation(
            value = "删除GzgPlan",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteGzgPlan")
    public ResponseJson deleteGzgPlan() {
        return null;
    }


    @ApiOperation(
            value = "更新GzgPlan",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateGzgPlan")
    public ResponseJson updateGzgPlan(@RequestBody GzgPlan gzgPlan) {
        return null;
    }


    @ApiOperation(value = "查询GzgPlan", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getGzgPlan")
    public ResponseJson getGzgPlan() {
        return null;
    }



    @PostMapping("/updateStock")
    public  ResponseJson updateStock(@RequestParam("imei")String imei,
                         @RequestParam("cabinet_no") String cabinet_no){
        JSONObject o = new JSONObject();
        o.put("imei",imei);
        o.put("cabinet_no",cabinet_no);
        return gzgGoodsPlanService.updateGzgGoodsPlanNumByCondation(o);
    }
}