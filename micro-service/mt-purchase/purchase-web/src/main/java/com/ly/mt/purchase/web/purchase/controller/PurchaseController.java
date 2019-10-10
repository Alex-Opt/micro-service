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

import static com.ly.mt.core.common.method.PurchaseMethodEnum.PURCHASE_MEMBER_INFO_BY_USERID;
import static com.ly.mt.core.common.method.PurchaseMethodEnum.PURCHASE_NEWEST_INFO;

/**
 * 购买情况
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-17 23:06:06
 */
@Api(description = "B端购买情况")
@RequestMapping("/b/purchase")
@RestController
public class PurchaseController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);


    @ApiOperation(value = "查询B端最新购买情况")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/newestInfo")
    public JSONObject getNewestPurchasesInfo() {
        JSONObject jsonObject = new JSONObject();
        return callPurchaseServer(PURCHASE_NEWEST_INFO, jsonObject);
    }
}
