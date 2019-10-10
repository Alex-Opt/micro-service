package com.ly.mt.gzg.b.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.method.GzgBMethodEnum;
import com.ly.mt.gzg.b.web.base.controller.BaseController;
import com.ly.mt.gzg.b.web.excel.ExcelUtil;
import com.ly.mt.gzg.b.web.excel.model.HotelStockExcelModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * gzgb hotelStock控制层
 */
@Api("gzgb hotelStock控制层")
@RestController
@RequestMapping("/gzgb/hotelStock")
public class GzgHotelStockController extends BaseController {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(GzgHotelStockController.class);

    @ApiOperation(value = "获取sku列表", notes = "获取sku列表")
    @GetMapping("/findSkuIdAndnameList")
    public JSONObject findSkuIdAndnameList(@ApiParam(value = "酒店ID", name = "hotelId", required = true) @RequestParam Long hotelId){
        Logger.info("GzgHotelStockController=>findSkuIdAndnameList:hotelId{}",hotelId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hotelId", hotelId);
        return callGzgBServer(GzgBMethodEnum.GZG_GET_SKUID_NAME_LIST,jsonObject);
    }

    @ApiOperation(value = "保存库存", notes = "保存库存")
    @PostMapping("/saveStock")
    public JSONObject saveStock(@RequestBody JSONObject jsonObject){
        Logger.info("GzgHotelStockController=>saveStock:param{}",jsonObject);
        return callGzgBServer(GzgBMethodEnum.GZG_SAVE_HOTEL_STOCK,jsonObject);
    }

    @ApiOperation(value = "获取库存列表", notes = "获取库存列表")
    @GetMapping("/getListByHotelId")
    public JSONObject getListByHotelId(@ApiParam(value = "酒店ID", name = "hotelId", required = true) @RequestParam("hotelId") Long hotelId){
        Logger.info("GzgHotelStockController=>getListByHotelId:hotelId{}",hotelId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hotelId",hotelId);
        return callGzgBServer(GzgBMethodEnum.GZG_GET_ALL_BY_HOTELID,jsonObject);
    }

    @ApiOperation(value = "导出库存列表", notes = "导出库存列表")
    @GetMapping("/writeExcel")
    public void writeExcel(HttpServletResponse response,@ApiParam(value = "酒店ID", name = "hotelId", required = true) @RequestParam("hotelId") Long hotelId){
        Logger.info("GzgHotelStockController=>getListByHotelId:hotelId{}",hotelId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hotelId",hotelId);
        JSONObject result = callGzgBServer(GzgBMethodEnum.GZG_GET_ALL_BY_HOTELID, jsonObject);
        JSONArray orderArr = result.getJSONArray("result");
        List<HotelStockExcelModel> hotelStockExcelModels = orderArr.toJavaList(HotelStockExcelModel.class);
        ExcelUtil.writeExcel(response, hotelStockExcelModels, "hotelStockExcel", "sheet1", new HotelStockExcelModel());
    }

}
