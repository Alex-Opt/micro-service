package com.ly.mt.activity.web.controller.buyer;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.web.controller.BaseController;
import com.ly.mt.core.common.entity.hd.request.UserDownOrderRequestBody;
import com.ly.mt.core.common.entity.hd.request.UserSendCOdeRequestBody;
import com.ly.mt.core.common.method.ActivityMethodEnum;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author panjingtian
 * @description 买家controller
 * 活动商品页面
 * String shopActivityUrl = model.getShopActivityUrl() + "?activityid=" + model.getActivityId() + "&shopid=" + model.getShopId();
 * @date 2019/6/15 2:01 PM
 */
@Api(description = "门店活动买家模块")
@RestController
@RequestMapping("/activity/activity/buyer")
public class ActivityUserController extends BaseController {


    /**
     * 发送验证码
     * @param body
     * @return
     */
    @ApiOperation(value = "发送下单验证码")
    @PostMapping("/dynamicCode")
    public JSONObject sendDynamic(@RequestBody UserSendCOdeRequestBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.BUYER_SEND_ORDER_DYNAMICCODE,jsonObject);
    }


    /**
     * 买家下单
     * 先注册再下单
     * @param body
     * @return
     */
    @ApiOperation(value = "买家下单")
    @PostMapping("/downOrder")
    public JSONObject downOrder(@RequestBody UserDownOrderRequestBody body){
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.BUYER_REGIST_ORDER,jsonObject);
    }


    /**
     * hdShopAttDetailId
     * phone
     * @param
     *
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping("/attUser/msg")
    public JSONObject getAttUserMsg(@RequestParam("hdShopAttDetailId")Long hdShopAttDetailId,@RequestParam("phone")String phone){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hdShopAttDetailId",hdShopAttDetailId);
        jsonObject.put("phone",phone);
        return callActivityServer(ActivityMethodEnum.BUYER_MSG,jsonObject);
    }

}
