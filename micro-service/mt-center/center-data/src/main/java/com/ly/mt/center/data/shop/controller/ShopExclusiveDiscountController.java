package com.ly.mt.center.data.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.service.ShopExclusiveDiscountService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "商家专属优惠")
@RestController
@RequestMapping("/data/center/shopExclusiveDiscount")
public class ShopExclusiveDiscountController {

    @Resource
    ShopExclusiveDiscountService shopExclusiveDiscountService;

    @ApiOperation(value = "查询ShopInfo", notes = "查询条件id不能为空")
    @PostMapping("/getShopExclusiveDiscount/{userId}")
    public ResponseJson getShopExclusiveDiscount(@PathVariable String userId) {
        return shopExclusiveDiscountService.getShopExclusiveDiscount(new JSONObject() {
                {
                    put("userId", userId);
                }
            });
    }
}