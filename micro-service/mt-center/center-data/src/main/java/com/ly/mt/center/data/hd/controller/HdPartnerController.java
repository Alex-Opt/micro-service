package com.ly.mt.center.data.hd.controller;

import com.ly.mt.center.data.hd.entity.HdActivityUser;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhanglifeng
 * @date 2019-08-21
 */
@Api(description = "接口")
@RestController
@RequestMapping("/data/center/hdPartner")
public class HdPartnerController {
    @ApiOperation(value = "保存合伙人信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "partnerName", value = "合伙人姓名", paramType = "query", required = true),
            @ApiImplicitParam(name = "partnerMobile", value = "合伙人手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "agentCityName", value = "合伙人代理城市名字", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 6, message = "参数缺失!")
    })
    @PostMapping("/addPartner")
    public ResponseJson addPartner(@RequestParam(value = "partnerName") String partnerName,
                                   @RequestParam(value = "partnerMobile") String partnerMobile,
                                   @RequestParam(value = "agentCityName") String agentCityName) {
        return null;
    }

    @ApiOperation(value = "根据手机号查询合伙人是否已经添加过的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "partnerMobile", value = "合伙人手机号", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 6, message = "参数缺失!")
    })
    @PostMapping("/queryPartnerByMobile")
    public ResponseJson queryPartnerByMobile(@RequestParam(value = "partnerMobile") String partnerMobile) {
        return null;
    }
}