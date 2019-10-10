package com.ly.mt.activity.partner.controller;

import com.ly.mt.activity.partner.service.PartnerService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_PARAM_NEED;

/**
 * @author zhanglifeng
 * @date 2019-08-21
 * @description 活动页保存moti合伙人信息。
 */
@Api(description = "MOTI合伙人信息接口")
@RestController
@RequestMapping("/activity1/partner/")
public class PartnerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PartnerController.class);
    @Resource
    private PartnerService partnerService;

    @ApiOperation(value = "保存合伙人信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "partnerName", value = "合伙人姓名", paramType = "query", required = true),
            @ApiImplicitParam(name = "partnerMobile", value = "合伙人手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "agentCityName", value = "合伙人代理城市名字", paramType = "query"),
            @ApiImplicitParam(name = "source", value = "数据来源", paramType = "query", required = true),
           /* @ApiImplicitParam(name = "channel", value = "数据的渠道", paramType = "query", required = true)*/
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 6, message = "参数缺失!"),
            @ApiResponse(code = 17, message = "该手机号已提交过申请，无需重复提交！"),
    })
    @PostMapping("/addPartner")
    public ResponseJson addPartner(@RequestParam(value = "partnerName") String partnerName,
                                   @RequestParam(value = "partnerMobile") String partnerMobile,
                                   @RequestParam(value = "agentCityName") String agentCityName,
                                   @RequestParam(value = "source") String source
                                   /*@RequestParam(value = "channel") String channel*/) {
        //参数校验
        if (StringUtil.isEmpty(partnerName) || StringUtil.isEmpty(partnerMobile) || StringUtil.isEmpty(source)) {
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "参数缺失!");
        }
        return partnerService.addPartnerInfo(partnerName, partnerMobile, agentCityName, source);
    }

}
