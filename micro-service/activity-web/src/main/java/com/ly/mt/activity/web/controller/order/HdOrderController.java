package com.ly.mt.activity.web.controller.order;


import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.web.annotations.ShopLoginAuth;
import com.ly.mt.activity.web.controller.BaseController;
import com.ly.mt.core.common.entity.hd.request.BuyerOrderRequestBody;
import com.ly.mt.core.common.entity.hd.request.ChangeOrderRequestBody;
import com.ly.mt.core.common.entity.hd.request.ShopOrderRequestBody;
import com.ly.mt.core.common.method.ActivityMethodEnum;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author panjingtian
 * @description 活动订单web层
 * @date 2019/6/17 11:11 AM
 */
@Api(description = "活动模块订单操作")
@RestController
@RequestMapping("/activity/hdOrder")
public class HdOrderController extends BaseController {


    /**
     * 商家查询所有的订单
     *
     * @param
     * @return
     */
    //@ShopLoginAuth
    @ApiOperation(value = "门店管理查询当前门店订单")
    @PostMapping("/shopOrders")
    public JSONObject shopOrders(@RequestBody ShopOrderRequestBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.SHOP_ORDER_FINDALL, jsonObject);
    }


    /**
     * @description 门店修改订单状态
     * @author panjingtian
     * @date 2019/6/17 2:49 PM
     */
    //@ShopLoginAuth
    @ApiOperation(value = "门店商家修改订单状态")
    @PostMapping("/shop/oder/status")
    public JSONObject changeOrder(@RequestBody ChangeOrderRequestBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.SHOP_ORDER_CHANGE, jsonObject);
    }


    /**
     * 买家查询接口
     * @param body
     * @return
     */
    @ApiOperation(value = "买家查询订单信息")
    @PostMapping("/buyer/order")
    public JSONObject buyerOrder(@RequestBody BuyerOrderRequestBody body){
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.BUYER_ORDER_FIND,jsonObject);
    }


}
