package com.ly.mt.purchase.web.purchase.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.purchase.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ly.mt.core.common.method.PurchaseMethodEnum.PURCHASE_MEMBER_INFO_BY_USERID;

/**
 * B端会员信息控制器
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-16 22:07:07
 */
@Api(description = "B端会员信息")
@RequestMapping("/b/member")
@RestController
public class MemberInfoController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MemberInfoController.class);

    @ApiOperation(value = "根据用户id查询会员信息")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/info")
    public JSONObject getInfoByUserId() {
        JSONObject jsonObject = new JSONObject();
        return callPurchaseServer(PURCHASE_MEMBER_INFO_BY_USERID, jsonObject);
    }

}
