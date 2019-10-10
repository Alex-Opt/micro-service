package com.ly.mt.cabinet.b.controller;


import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.WithdrawalVO;
import com.ly.mt.cabinet.b.common.request.WxUserVo;
import com.ly.mt.cabinet.b.service.WithdrawalService;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Api(tags = "withdrawal")
@RestController
@RequestMapping("/cabinet/b/withdrawal")
public class WithdrawalController extends BaseMessageController{

    private static final Logger log = LoggerFactory.getLogger(WithdrawalController.class);

    @Resource
    private WithdrawalService withdrawalService;

    private static Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$");

    @ApiOperation(value = "检查微信用户信息")
    @PostMapping("/checkWxUser")
    public Resp checkWxUser(HttpServletRequest request) {
        TokenUserMessage token =getTokenUserMessage(request);
        if(token==null){
            return Resp.createByErrorMessage("用户没有登录!");
        }
        return withdrawalService.checkWxUser(String.valueOf(token.getUserId()));
    }

    @ApiOperation(value = "绑定微信用户信息")
    @PostMapping("/addWxUser")
    public Resp addWxUser(@RequestBody WxUserVo wxUserVo, HttpServletRequest request) {
        if(null == wxUserVo|| StringUtil.isEmpty(wxUserVo.getOpenId())){
            return Resp.createByErrorMessage("参数错误!");
        }
        TokenUserMessage token =getTokenUserMessage(request);
        if(token==null){
            return Resp.createByErrorMessage("用户没有登录!");
        }
        wxUserVo.setUserId(String.valueOf(token.getUserId()));
        return withdrawalService.addWxUser(wxUserVo);
    }

    @ApiOperation(value = "解除绑定微信用户信息")
    @PostMapping("/deleteWxUser")
    public Resp deleteWxUser(HttpServletRequest request) {
        TokenUserMessage token =getTokenUserMessage(request);
        if(token==null){
            return Resp.createByErrorMessage("用户没有登录!");
        }
        return withdrawalService.deleteWxUser(token.getUserId());
    }

    @ApiOperation("查询奖励金额,type=1、2、或者空")
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询异常!")
    })
    @PostMapping("/queryReplenishReward")
    public Resp queryReplenishReward(@RequestBody WithdrawalVO withdrawalVO, HttpServletRequest request) {
        TokenUserMessage token =getTokenUserMessage(request);
        if(token==null){
            return Resp.createByErrorMessage("用户没有登录!");
        }
        withdrawalVO.setUserId(String.valueOf(token.getUserId()));
        return withdrawalService.queryReplenishReward(withdrawalVO);
    }


    @ApiOperation("申请提现，withdrawalAmount、type、")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/applyWithdraw")
    public Resp applyWithdraw(@RequestBody WithdrawalVO withdrawalVO, HttpServletRequest request) {
        if(StringUtil.isEmpty(withdrawalVO.getWithdrawalAmount())||StringUtil.isEmpty(withdrawalVO.getType())){
            return Resp.createByErrorMessage("参数错误!");
        }
        if(!pattern.matcher(withdrawalVO.getWithdrawalAmount()).matches()){
            return Resp.createByErrorMessage("参数格式错误!");
        }
       TokenUserMessage token =getTokenUserMessage(request);
        if(token==null){
            return Resp.createByErrorMessage("用户没有登录!");
        }
        String ip = RequestUtil.getIpAddress(request);
        return withdrawalService.applyWithdrawal(withdrawalVO.getWithdrawalAmount(),String.valueOf(token.getUserId()), ip,withdrawalVO.getType());
    }

   /* @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/alWithdrawal")
    public  Resp alWithdrawal(){

        return withdrawalService.alWithdrawal();
    }*/

   /* @ApiOperation("查询提现结果")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/queryWithdrawal")
    public  Resp queryWithdrawal(String tradeNo){
        if(StringUtil.isEmpty(tradeNo)){
            return Resp.createByErrorMessage("参数错误!");
        }
        return withdrawalService.queryWithdrawal(tradeNo);
    }*/

}
