package com.ly.mt.center.data.cabinet.controller;

import com.ly.mt.center.data.cabinet.service.CabinetOptionsService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

@Api(tags = "cabinet options")
@RestController
@RequestMapping("/cabinet/b")
public class CabinetOptionsController {

    private static final Logger log = LoggerFactory.getLogger(CabinetOptionsController.class);

    @Resource
    private CabinetOptionsService cabinetOptionsService;

    @ApiOperation("get cooperation options")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "操作失败")
    })
    @PostMapping("/cooperation/getOptions")
    public ResponseJson getOptions(){
        log.info("call method getOptions of CabinetOptionsController at {}", LocalDateTime.now());
        try {
            long start = System.currentTimeMillis();
            ResponseJson options = cabinetOptionsService.getOptions(null);
            long end = System.currentTimeMillis();
            log.info("call center-data method getOptions cost {} mills,the responseJson is {}",(end - start),options);
            return options;
        }catch (Exception e){
            log.error("call center-data method getOptions has some errors , the errors message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }
}
