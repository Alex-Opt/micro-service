package com.ly.mt.cabinet.b.controller;

import com.ly.mt.cabinet.b.common.request.CabinetInfoBindStoreReqVo;
import com.ly.mt.cabinet.b.service.CabinetInfoService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "cabinetInfo")
@RestController
@RequestMapping("/cabinet/b/cabinetInfo")
public class CabinetInfoController {

    private static final Logger log = LoggerFactory.getLogger(CabinetInfoController.class);

    @Resource
    private CabinetInfoService cabinetInfoService;


    @ApiOperation(value = "查询所有的配货方案名称")
    @PostMapping("/getCabinetProgrammeList")
    @ApiResponses({
            @ApiResponse(code = 0,message = "request success"),
            @ApiResponse(code = 1,message = "request fail")
    })
    public ResponseJson getCabinetProgrammeList(){
        log.info("start call method CabinetInfoController.getCabinetProgrammeList  ,controller receive no param " );
        try {
            ResponseJson responseJson = cabinetInfoService.getCabinetProgrammeList();
            return  responseJson;
        }catch (Exception e){
            log.error("call method CabinetInfoController.getCabinetProgrammeList has some errors,the error message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }
    @ApiOperation(value = "库管将格子柜信息与商家信息绑定")
    @PostMapping("/addCabinetInfoBindStore")
    @ApiResponses({
            @ApiResponse(code = 0,message = "request success"),
            @ApiResponse(code = 1,message = "request fail")
    })
    public ResponseJson addCabinetInfoBindStore(@ApiParam(name = "CabinetInfoBindStoreReqVo", value = "库管提交的绑定柜子的参数", required = true) @RequestBody  CabinetInfoBindStoreReqVo cabinetInfoBindStoreReqVo){
        log.info("CabinetInfoController.addCabinetInfoBindStore 库管将格子柜信息与商家信息绑定，controller层接收的数据为  {}", cabinetInfoBindStoreReqVo.toString());
        try {
            ResponseJson responseJson = cabinetInfoService.addCabinetInfoBindStore(cabinetInfoBindStoreReqVo);
            return  responseJson;
        }catch (Exception e){
            log.error("call method CabinetInfoController.addCabinetInfoBindStore has some errors,the error message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }
}
