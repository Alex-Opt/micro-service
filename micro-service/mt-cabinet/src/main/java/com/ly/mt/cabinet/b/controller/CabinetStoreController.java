package com.ly.mt.cabinet.b.controller;

import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.WarehouseKeeperVo;
import com.ly.mt.cabinet.b.common.response.CreateCooperationResVO;
import com.ly.mt.cabinet.b.service.CabinetStoreService;
import com.ly.mt.cabinet.c.order.service.GzgOrderSyncCabinetProfitService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "cabinetStore")
@RestController
@RequestMapping("/cabinet/b/cabinetStore")
public class CabinetStoreController extends BaseMessageController{

    private static final Logger log = LoggerFactory.getLogger(CabinetStoreController.class);

    @Resource
    private CabinetStoreService cabinetStoreService;
    @Autowired
    GzgOrderSyncCabinetProfitService gzgOrderSyncCabinetProfitService;

    @ApiOperation(value = "库管通过自己的user_id查询自己所负责区域所有的商户信息")
    @PostMapping("/getCabinetStoreList")
    @ApiResponses({
            @ApiResponse(code = 0,message = "request success"),
            @ApiResponse(code = 1,message = "request fail")
    })
    public ResponseJson getCabinetStoreByUserId(@ApiParam(name = "WarehouseKeeperVo", value = "库管本人的user_id和店铺状态", required = true)
                                                    @RequestBody WarehouseKeeperVo warehouseKeeperVo, HttpServletRequest request){
        log.info("start call method CabinetStoreController.getCabinetStoreByUserId  ,controller receive param is {}" ,warehouseKeeperVo);


        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        Long user_id = tokenUserMessage.getUserId();
        Integer pageNum = warehouseKeeperVo.getPageNum();
        Integer pageSize = warehouseKeeperVo.getPageSize();
        if(pageNum==null){
            warehouseKeeperVo.setPageNum(1);
        }
        if(pageSize==null){
            warehouseKeeperVo.setPageSize(10);
        }

        try {
            ResponseJson responseJson = cabinetStoreService.getCabinetStoreByUserId(warehouseKeeperVo,user_id+"");
            log.info("CabinetStoreController.getCabinetStoreByUserId 返回给前端的数据 ：{}",responseJson);
            return  responseJson;
        }catch (Exception e){
            log.error("call method CabinetStoreController.getCabinetStoreByUserId has some errors,the error message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "库管查询自己所负责区域单个商户信息详情")
    @PostMapping("/getCabinetStoreDetail")
    @ApiResponses({
            @ApiResponse(code = 0,message = "request success"),
            @ApiResponse(code = 1,message = "request fail")
    })
    public ResponseJson<CreateCooperationResVO> getCabinetStoreDetail(@ApiParam(name = "storeId", value = "店铺的id", required = true,example = "94244466564987092009")
                                                @RequestBody Map<String ,String> map) {
        String storeId = map.get("storeId");
        if(StringUtil.isEmpty(storeId)){
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        log.info("start call method CabinetStoreController.getCabinetStoreDetail  ,controller receive param is {}" ,storeId);
        try {
            ResponseJson responseJson = cabinetStoreService.getCabinetStoreDetail(storeId);
            log.info("CabinetStoreController.getCabinetStoreDetail 返回给前端的数据 ：{}",responseJson);
            return  responseJson;
        }catch (Exception e){
            log.error("call method CabinetStoreController.getCabinetStoreDetail has some errors,the error message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "测试店铺收益详情")
    @PostMapping("/testCabinetStoreProfit")
    @ApiResponses({
            @ApiResponse(code = 0,message = "request success"),
            @ApiResponse(code = 1,message = "request fail")
    })
    public ResponseJson testCabinetStoreProfit(@ApiParam(name = "orderId", value = "店铺的id", required = true)
                                              @RequestBody String orderId){
        log.info("start call method CabinetStoreController.testCabinetStoreProfit  ,controller receive param is {}" ,orderId);

        try {
            gzgOrderSyncCabinetProfitService.syncCabinetStoreProfit(orderId);
            return  ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
        }catch (Exception e){
            log.error("call method CabinetStoreController.getCabinetStoreDetail has some errors,the error message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


}
