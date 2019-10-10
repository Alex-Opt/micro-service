//package com.ly.mt.cabinet.c.programme.controller;
//
//import com.ly.mt.cabinet.c.good.service.GzgGoodsService;
//import com.ly.mt.core.base.entity.ResponseJson;
//import com.ly.mt.core.base.util.ResponseUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
//
//@RestController
//@RequestMapping("/gzg/goods")
//@Api(value = "goods controller", tags = {"goods"})
//public class GzgProgrammeController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(GzgProgrammeController.class);
//
//    @Autowired
//    GzgGoodsService gzgGoodsService;
//
//    /**
//     * 格子柜商品列表
//     *
//     * @param gzgCode
//     * @return
//     */
//    @ApiOperation(value = "落地页商品列表", notes = "扫描格子柜二维码获取商品列表")
//    @GetMapping("/goodsList")
//    @ApiImplicitParams({
//             @ApiImplicitParam(name = "gzgCode", value = "格子柜编号", paramType = "query", required = true),
//            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = false)
//    })
//    public ResponseJson goodsList( @RequestParam(required = true) String gzgCode,@RequestParam(required = false) String userId) {
//        LOGGER.info("格子柜用户落地页商品列表，controller层接接收到的数据：gzgCode ={}，userId = {}", gzgCode,userId);
//        if (StringUtils.isBlank(gzgCode) || StringUtils.equals("undefined", gzgCode)) {
//            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
//        }
//        ResponseJson responseJson = null;
//        try {
//            responseJson = gzgGoodsService.gzgGoodsList(gzgCode,userId);
//            LOGGER.info("格子柜落地页商品列表，controller层返回给前段的数据{}", responseJson);
//            return responseJson;
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.info("格子柜落地页商品列表，controller层异常，异常结果{}", e.getMessage());
//            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
//        }
//
//    }
//
//
//
//
//    @ApiOperation(value = "goods description detail")
//    @PostMapping("/goodsDetail")
//    public ResponseJson goodsDetail(@RequestParam String  skuId){
//        LOGGER.info("查询商品详情，controller层接接收到的数据：skuId ={}", skuId);
//        if (StringUtils.isBlank(skuId) || StringUtils.equals("undefined", skuId)) {
//            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
//        }
//        try {
//            ResponseJson responseJson = gzgGoodsService.gzgGoodsDetail(skuId);
//            return responseJson;
//        }catch (Exception e){
//            e.printStackTrace();
//            LOGGER.info("查询商品详情，controller层异常，异常结果{}", e.getMessage());
//            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
//        }
//
//    }
//
//}
