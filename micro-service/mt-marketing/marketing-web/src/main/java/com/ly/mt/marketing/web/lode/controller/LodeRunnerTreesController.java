package com.ly.mt.marketing.web.lode.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.dict.LodeRunnerTpEnum;
import com.ly.mt.core.common.method.MarketingMethodEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.marketing.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;

/**
 *@Description  淘金人树
 *@Author  zhuyh
 */

@Api(description = "淘金人树接口")
@RestController
@RequestMapping("/marketing/lode/runner")
public class LodeRunnerTreesController extends BaseController {
    @ApiOperation(value = "分页获取淘金奖励明细")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/details")
    public JSONObject getDetails(HttpServletRequest request,
                                    @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                    @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows,
                                    @ApiParam(value = "类型，invite：邀请、address_book：通讯录、traffic：流量", required = true) @RequestParam(value = "tp") String tp){
        LodeRunnerTpEnum tpEnum = LodeRunnerTpEnum.getIdByTp(tp);
        if (tpEnum == null){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        jsonObject.put("userSource", tpEnum.getId());
        return callMarketingServer(MarketingMethodEnum.MARKETING_LODE_DETAILS, jsonObject);
    }

    /**
     *@Description 获取粉丝团长详情
     *@Author  zhuyh
     */
    @ApiOperation(value = "分页获取淘金-粉丝团长明细")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/team/head/details")
    public JSONObject getTeamHeadDetails(HttpServletRequest request,
                                     @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                     @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        return callMarketingServer(MarketingMethodEnum.MARKETING_LODE_TEAM_HEAD_DETAILS, jsonObject);
    }

    @ApiOperation(value = "我的淘金")
    @PostMapping("/my")
    public JSONObject getMyDetails(HttpServletRequest request){
        return callMarketingServer(MarketingMethodEnum.MARKETING_LODE_MY, new JSONObject());
    }

}
