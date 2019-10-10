package com.ly.mt.marketing.web.profits.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.dict.ShopProfitsTpEnum;
import com.ly.mt.core.common.method.MarketingMethodEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.marketing.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;

/**
 *@Description
 *@Author  zhuyh
 */
@Api(description = "收益接口")
@RestController
@RequestMapping("/marketing/profits")
public class ShopProfitsController extends BaseController {


    @ApiOperation(value = "获取人员的总收益", notes = "根据用户id查询总收益")
    @ApiImplicitParam(name = "request", value = "用户id,这里后台可以从session中获取，不用前端传入参")
    @PostMapping("/sum")
    public JSONObject getMemberSum() {
        JSONObject jsonObject = new JSONObject();
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_SUM, jsonObject);
    }

    @ApiOperation(value = "获取收益金额排行", notes = "用tp参数区分查询类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topValue", value = "查询条数,如果为空，则默认20", paramType = "query", required = true),
            @ApiImplicitParam(name = "tp", value = "类型：grab：抢单成交额、reward：奖励成交额、lode：淘金成交额", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/top")
    public JSONObject getTop(@RequestParam(value = "topValue") Integer topValue,
                                 @RequestParam(value = "tp") String tp){
        if(ShopProfitsTpEnum.getById(tp) == null){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        if (topValue == null){
            jsonObject.put("topValue", 20);
        } else {
            jsonObject.put("topValue", topValue);
        }
        jsonObject.put("tp", tp);
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_TOP, jsonObject);
    }


    @ApiOperation(value = "查询奖励收益详情")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @PostMapping("/reward/details")
    public JSONObject getRewardDetails(){
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_REWARD_DETAILS, new JSONObject());
    }

    @ApiOperation(value = "查询抢单收益详情")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @PostMapping("/grab/details")
    public JSONObject getGrabDetails(){
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_GRAB_DETAILS, new JSONObject());
    }

    @ApiOperation(value = "查询专属订单收益详情")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @PostMapping("/order/details")
    public JSONObject getOrderDetails(){
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_ORDER_DETAILS, new JSONObject());
    }
    @ApiOperation(value = "查询淘金收益详情")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @PostMapping("/lode/details")
    public JSONObject getLodeDetails(){
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_LODE_DETAILS, new JSONObject());
    }


    @ApiOperation(value = "查询正在抢单赚取的人数")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @PostMapping("/users/count")
    public JSONObject getUserCount(){
        return callMarketingServer(MarketingMethodEnum.MARKETING_PROFITS_USER_COUNT, new JSONObject());
    }



}
