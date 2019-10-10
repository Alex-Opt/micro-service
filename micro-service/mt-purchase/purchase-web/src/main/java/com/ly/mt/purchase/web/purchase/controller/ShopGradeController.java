package com.ly.mt.purchase.web.purchase.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.purchase.web.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ly.mt.core.common.method.PurchaseMethodEnum.PURCHASE_SHOP_GRADE_LIST;

/**
 * 会员等级情况
 * @author xiaobei
 * @date 2019-06-21 08:16:16
 */
@Api(description = "会员等级情况")
@RestController
@RequestMapping("m/grade")
public class ShopGradeController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShopGradeController.class);


    @ApiOperation(value = "查询会员等级配置情况")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/list")
    public JSONObject listAll() {
        JSONObject jsonObject = new JSONObject();
        return callPurchaseServer(PURCHASE_SHOP_GRADE_LIST, jsonObject);
    }
}
