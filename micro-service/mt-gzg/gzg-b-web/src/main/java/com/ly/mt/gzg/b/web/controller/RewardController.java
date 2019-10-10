package com.ly.mt.gzg.b.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.gzg.GzgReplenishOrder;
import com.ly.mt.core.common.method.GzgBMethodEnum;
import com.ly.mt.gzg.b.web.base.controller.BaseController;
import com.ly.mt.gzg.b.web.config.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {"补货奖励"})
@RestController
@RequestMapping("/gzgb/reward")
public class RewardController extends BaseController {

    @GetMapping("/replenishReward")
    @ApiOperation("补货奖励")
    @Permission
    public JSONObject replenishReward(HttpServletRequest request){
        long userId = (Long) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        json.put("userId",userId);
        return callGzgBServer(GzgBMethodEnum.GZG_REPLENISH_REWARD,json);
    }
}
