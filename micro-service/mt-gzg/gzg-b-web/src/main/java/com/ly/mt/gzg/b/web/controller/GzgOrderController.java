package com.ly.mt.gzg.b.web.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.method.GzgBMethodEnum;
import com.ly.mt.gzg.b.web.base.controller.BaseController;
import com.ly.mt.gzg.b.web.excel.ExcelUtil;
import com.ly.mt.gzg.b.web.excel.model.OrderExcelModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * gzgb info控制层
 */
@Api("gzgb gzgorder控制层")
@RestController
@RequestMapping("/gzgb/gzgOrder")
public class GzgOrderController extends BaseController {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(GzgOrderController.class);
    @ApiOperation(value = "获取订单列表", notes = "获取订单列表")
    @PostMapping("/getOrders")
    public JSONObject getOrders(@ApiParam(value = "开始时间", name = "startTime", required = false) @RequestParam(required = false) Long startTime,
                                @ApiParam(value = "结束时间", name = "endTime", required = false) @RequestParam(required = false) Long endTime,
                                @ApiParam(value = "格子柜code", name = "code", required = true) @RequestParam String code,
                                @ApiParam(value = "酒店ID", name = "hotelId", required = true) @RequestParam String hotelId,
                                @ApiParam(value = "订单状态", name = "orderStatus", required = false) @RequestParam(required = false) Integer orderStatus){
        Logger.info("GzgOrderController=>getOrders:startTime=" + startTime
                + " endTime=" + endTime
                + " code=" + code
                + " hotelId=" + hotelId
                + " orderStatus=" + orderStatus
        );
        return getOrdersPri(startTime,endTime,code,hotelId,orderStatus);
    }

    @ApiOperation(value = "导出订单列表", notes = "导出订单列表")
    @GetMapping("/writeExcel")
    public void writeExcel(HttpServletResponse response,
                           @ApiParam(value = "开始时间", name = "startTime", required = false) @RequestParam(required = false) Long startTime,
                           @ApiParam(value = "结束时间", name = "endTime", required = false) @RequestParam(required = false) Long endTime,
                           @ApiParam(value = "格子柜code", name = "code", required = true) @RequestParam String code,
                           @ApiParam(value = "酒店ID", name = "hotelId", required = true) @RequestParam String hotelId,
                           @ApiParam(value = "订单状态", name = "orderStatus", required = false) @RequestParam(required = false) Integer orderStatus){
        Logger.info("GzgOrderController=>writeExcel:startTime=" + startTime
                + " endTime=" + endTime
                + " code=" + code
                + " hotelId=" + hotelId
                + " orderStatus=" + orderStatus
        );
        JSONObject jsonOrders = getOrdersPri(startTime, endTime, code, hotelId, orderStatus);
        JSONArray orderArr = jsonOrders.getJSONArray("result");
        List<OrderExcelModel> orderExcelModels = orderArr.toJavaList(OrderExcelModel.class);
        ExcelUtil.writeExcel(response, orderExcelModels, "orderExcel", "sheet1", new OrderExcelModel());
    }

    //todo 缺少退款接口

    private JSONObject getOrdersPri(Long startTime,Long endTime,String code,String hotelId,Integer orderStatus){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("startTime",startTime);
        jsonObject.put("endTime",endTime);
        jsonObject.put("code",code);
        jsonObject.put("hotelId",hotelId);
        jsonObject.put("orderStatus",orderStatus);
        return callGzgBServer(GzgBMethodEnum.GZG_GET_ORDER_LIST,jsonObject);
    }
}
