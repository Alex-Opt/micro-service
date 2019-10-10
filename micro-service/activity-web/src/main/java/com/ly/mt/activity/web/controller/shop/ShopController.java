package com.ly.mt.activity.web.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.web.annotations.ShopLoginAuth;
import com.ly.mt.activity.web.controller.BaseController;
import com.ly.mt.core.common.entity.hd.request.HdOperatorRequestBody;
import com.ly.mt.core.common.entity.hd.request.HdShopInfoRequestBody;
import com.ly.mt.core.common.entity.hd.request.RegistShopActivityRequestBody;
import com.ly.mt.core.common.entity.hd.request.ShopSendRegistCodeRequestBody;
import com.ly.mt.core.common.method.ActivityMethodEnum;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Api(description = "活动模块商家操作")
@RestController
@RequestMapping("/activity/activity/shop")
@Slf4j
public class ShopController extends BaseController {


    /**
     * 发送登录验验证码
     *
     * @param body
     * @return
     */
    @ApiOperation(value = "发送验证码,只传递phone，activityId")
    @PostMapping("/send/login/dynamicCode")
    public JSONObject sendDynamic(@RequestBody ShopSendRegistCodeRequestBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.SHOP_LOGIN_CODE, jsonObject);

    }


    /**
     * @description 活动管理员登录
     * @author panjingtian
     * @date 2019/6/17 10:41 AM
     */
    @ApiOperation("活动管理员登录,只传phone，dynamicCode，activityId")
    @PostMapping("/login")
    public JSONObject login(@RequestBody ShopSendRegistCodeRequestBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.SHOP_ADMIN_LOGIN, jsonObject);
    }


    /**
     * 查询二维码地址
     *
     * @param jsonObject
     * @return
     */
    //@ShopLoginAuth
    @ApiOperation("获取活动信息二维码链接")
    @ApiImplicitParam(name = "shopAttDetailId", value = "门店活动主键id,登录时获取的id", required = true)
    @PostMapping("/attDetail")
    public JSONObject getShopAttDetailVo(@RequestBody JSONObject jsonObject) {
        return callActivityServer(ActivityMethodEnum.SHOP_GET_ACTIVITY, jsonObject);
    }


    /**
     * 门店活动注册
     *
     * @param body
     * @return
     */
    @ApiOperation("活动注册")
    @PostMapping("/activity/regist")
    public JSONObject registShopAtt(@RequestBody RegistShopActivityRequestBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.SHOP_REGIST_ACTIVITY, jsonObject);
    }


    /**
     * 活动注册验证码发送
     *
     * @param body
     * @return
     */
    @ApiOperation("发送活动注册验证码")
    @PostMapping("/shop/attRegist/dynamicCode")
    public JSONObject sendAttRegistDynamicCode(@RequestBody ShopSendRegistCodeRequestBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.SHOP_SEND_ACTIVITY, jsonObject);
    }


    /**
     * 展示当前活动下的商品
     *
     * @param activityId
     * @return
     */
    @ApiOperation("买家获取商家店铺商品")
    @GetMapping("/products")
    public JSONObject getProduct(@ApiParam(name = "activityId", value = "活动主键id", required = true) @RequestParam("activityId") Integer activityId,
                                 @ApiParam(name = "shopId", value = "门店主键id", required = true) @RequestParam("shopId") String shopId) {
        JSONObject jsonObject = new JSONObject(2);
        jsonObject.put("activityId", activityId);
        jsonObject.put("shopId", shopId);
        return callActivityServer(ActivityMethodEnum.SHOP_SHOW_PRODUCT, jsonObject);
    }


    /**
     * 查询代理商门店
     *
     * @param operatorId
     * @return
     */
    @ApiOperation("根据代理商查询门店")
    @GetMapping("/operator/{operatorId}")
    @ApiImplicitParam(name = "operatorId", value = "代理商id")
    public JSONObject shopsInfo(@PathVariable("operatorId") Long operatorId) {
        log.info("来了兄弟，参数{}",operatorId);
        JSONObject jsonObject = new JSONObject(1);
        jsonObject.put("operatorId", operatorId);
        return callActivityServer(ActivityMethodEnum.SHOP_INFO_FIND, jsonObject);
    }


    /**
     * 获取门店一些烂七八糟的信息
     *
     * @param shopId
     * @param activityId
     * @return
     */
    @ApiOperation("获取注册门店信息主键id")
    @GetMapping("/getAttDetailId")
    public JSONObject getAttDetail(
            @ApiParam(name = "shopId", value = "门店主键") @RequestParam("shopId") String shopId,
            @ApiParam(name = "activityId", value = "活动id") @RequestParam("activityId") Long activityId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shopId", shopId);
        jsonObject.put("activityId", activityId);
        return callActivityServer(ActivityMethodEnum.SHOP_GET_ATT_DETAIL_ID, jsonObject);

    }


    /**
     * 获取全部代理商
     * @return
     */
    @ApiOperation("获取全部代理商")
    @GetMapping("/operators")
    public JSONObject getOperators(){
        return callActivityServer(ActivityMethodEnum.SHUO_GET_OPERATORS,new JSONObject(0));
    }

    /*********************录入代理商and门店，后期功能迭代到后台管理中**************************/

    /**
     * 添加代理商
     * @param body
     * @return
     */
    @ApiOperation("添加代理商")
    @PostMapping("/operator")
    public JSONObject addoOperator(@RequestBody HdOperatorRequestBody body){
        return callActivityServer(ActivityMethodEnum.SHOP_ADD_OPERATOR,JSONObject.parseObject(JSONObject.toJSONString(body)));
    }

    /**
     * 添加门店
     * @param body
     * @return
     */
    @ApiOperation("添加门店")
    @PostMapping("/shop")
    public JSONObject addShop(@RequestBody HdShopInfoRequestBody body){
        return callActivityServer(ActivityMethodEnum.SHOP_ADD_SHOPINFO,JSONObject.parseObject(JSONObject.toJSONString(body)));
    }
}
