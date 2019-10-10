package com.ly.mt.marketing.web.profits.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.dict.ShopProfitsLogTypeEnum;
import com.ly.mt.core.common.method.MarketingMethodEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.marketing.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;

/**
 *@Description 收益日志controller
 *@Author  zhuyh
 */

@Api(description = "收益接口")
@RestController
@RequestMapping("/marketing/profits/logs")
public class ShopProfitsLogsController extends BaseController {

    @ApiOperation(value = "获取有奖励的订单列表", notes = "分页获取")
    @PostMapping("/rewards/orders")
    public JSONObject getRewardOrders(HttpServletRequest request,
                                      @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                      @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_LOGS_REWARD_ORDER, jsonObject);
    }

    @ApiOperation(value = "分页获取奖励明细")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/rewards")
    public JSONObject getRewardLogs(HttpServletRequest request,
                                    @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                    @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows,
                                    @ApiParam(value = "类型 4：抢单奖励、13：抢单奖励提现") @RequestParam(value = "tp") Integer tp,
                                    @ApiParam(value = "开始时间(YYYY-MM-DD)") @RequestParam(value = "startDate") String startDate,
                                    @ApiParam(value = "结束时间(YYYY-MM-DD)") @RequestParam(value = "endDate") String endDate){
        if ((tp != null && !(ShopProfitsLogTypeEnum.REWARD.getId().equals(tp) || ShopProfitsLogTypeEnum.REWARD_CASH.getId().equals(tp)))
                || StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)
        ){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        jsonObject.put("tp", tp);
        jsonObject.put("startDate", startDate);
        jsonObject.put("endDate", endDate);
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_LOGS_REWARD, jsonObject);
    }

    @ApiOperation(value = "查询奖励明细累计")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/rewards/total")
    public JSONObject getSumReward(HttpServletRequest request,
                                    @ApiParam(value = "类型 4：抢单奖励、13：抢单奖励提现") @RequestParam(value = "tp") Integer tp,
                                    @ApiParam(value = "开始时间(YYYY-MM-DD)") @RequestParam(value = "startDate") String startDate,
                                    @ApiParam(value = "结束时间(YYYY-MM-DD)") @RequestParam(value = "endDate") String endDate){
        if ((tp != null && !(ShopProfitsLogTypeEnum.REWARD.getId().equals(tp) || ShopProfitsLogTypeEnum.REWARD_CASH.getId().equals(tp)))
                || StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)
        ){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tp", tp);
        jsonObject.put("startDate", startDate);
        jsonObject.put("endDate", endDate);
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_LOGS_REWARD_TOTAL, jsonObject);
    }

    @ApiOperation(value = "分页获取淘金明细")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/lodes")
    public JSONObject getLodesLogs(HttpServletRequest request,
                                    @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                    @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows,
                                    @ApiParam(value = "类型 1：分享淘金、11：提现") @RequestParam(value = "tp") Integer tp,
                                    @ApiParam(value = "开始时间(YYYY-MM-DD)") @RequestParam(value = "startDate") String startDate,
                                    @ApiParam(value = "结束时间(YYYY-MM-DD)") @RequestParam(value = "endDate") String endDate){
        if ((tp != null && !(ShopProfitsLogTypeEnum.LODE_CASH.getId().equals(tp) || ShopProfitsLogTypeEnum.INVITE.getId().equals(tp)))
                || StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)
        ){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        jsonObject.put("tp", tp);
        jsonObject.put("startDate", startDate);
        jsonObject.put("endDate", endDate);
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_LOGS_LODE, jsonObject);
    }

    @ApiOperation(value = "查询奖励明细累计")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/lodes/total")
    public JSONObject getSumLode(HttpServletRequest request,
                                 @ApiParam(value = "类型 1：分享淘金、11：提现") @RequestParam(value = "tp") Integer tp,
                                    @ApiParam(value = "开始时间(YYYY-MM-DD)") @RequestParam(value = "startDate") String startDate,
                                    @ApiParam(value = "结束时间(YYYY-MM-DD)") @RequestParam(value = "endDate") String endDate){
        if ((tp != null && !(ShopProfitsLogTypeEnum.LODE_CASH.getId().equals(tp) || ShopProfitsLogTypeEnum.INVITE.getId().equals(tp)))
                || StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)
        ){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tp", tp);
        jsonObject.put("startDate", startDate);
        jsonObject.put("endDate", endDate);
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_LOGS_LODE_TOTAL, jsonObject);
    }

    @ApiOperation(value = "查询抢单结算周期累计")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/grabs/cycle/sum")
    public JSONObject getGrabsCycleSum(HttpServletRequest request){
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_LOGS_GRABS_CYCLE_SUM, new JSONObject());
    }


    @ApiOperation(value = "分页获取抢单明细")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/grabs")
    public JSONObject getGrabsLogs(HttpServletRequest request,
                                   @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                   @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows,
                                   @ApiParam(value = "开始时间(YYYY-MM-DD)") @RequestParam(value = "startDate") String startDate){
        if (StringUtil.isEmpty(startDate)){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        jsonObject.put("startDate", startDate);
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_LOGS_GRABS, jsonObject);
    }

    @ApiOperation(value = "查询专属单结算周期累计")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/orders/cycle/sum")
    public JSONObject getOrdersCycleSum(HttpServletRequest request){
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_LOGS_ORDER_CYCLE_SUM, new JSONObject());
    }


    @ApiOperation(value = "分页获取专属单明细")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/orders")
    public JSONObject getOrdersLogs(HttpServletRequest request,
                                   @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                   @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows,
                                   @ApiParam(value = "开始时间(YYYY-MM-DD)") @RequestParam(value = "startDate") String startDate){
        if (StringUtil.isEmpty(startDate)){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        jsonObject.put("startDate", startDate);
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_LOGS_ORDERS, jsonObject);
    }
}