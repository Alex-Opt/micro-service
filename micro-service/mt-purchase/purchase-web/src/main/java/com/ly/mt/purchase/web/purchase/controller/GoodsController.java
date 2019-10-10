package com.ly.mt.purchase.web.purchase.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.purchase.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ly.mt.core.common.method.PurchaseMethodEnum.PURCHASE_CATEGROY_LIST_BY_PARENTID;
import static com.ly.mt.core.common.method.PurchaseMethodEnum.PURCHASE_LISTTOP5_BY_CID;

/**
 * 商品相关
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-18 06:25:25
 */
@Api(description = "B端进货商品信息")
@RequestMapping("/b/goods")
@RestController
public class GoodsController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

    @ApiOperation(value = "B端根据类目父id查询所有一级子类目")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/categroyList")
    public JSONObject queryCategroyListByParentId() {
        JSONObject jsonObject = new JSONObject();
        return callPurchaseServer(PURCHASE_CATEGROY_LIST_BY_PARENTID, jsonObject);
    }

    @ApiOperation(value = "B端根据类目id查询当前类目下销量top5")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "类目id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/listTop5")
    public JSONObject queryListTop5ByCid(@RequestParam(value = "cid") String cid) {
        LOGGER.info("当前的类目id为{}",cid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cid", cid);
        return callPurchaseServer(PURCHASE_LISTTOP5_BY_CID, jsonObject);
    }



}
