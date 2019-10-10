package com.ly.mt.blue.tooth.wxshop.web.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.wxshop.app.service.WxShopH5Service;
import com.ly.mt.blue.tooth.wxshop.app.vo.WxShopH5Vo;
import com.ly.mt.blue.tooth.wxshop.web.service.WxShopWebService;
import com.ly.mt.blue.tooth.wxshop.web.vo.WxShopWebVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Api(description = "微信店铺Web操作接口")
@RestController
@RequestMapping("/bluetooth/web/wxshop")
public class WxShopWebController {
    private final static Logger Logger = LoggerFactory.getLogger(WxShopWebController.class);
    @Resource
    WxShopWebService wxShopWebService;


    @ApiOperation("WEB-获取店铺列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authStatus", value = "授权状态", paramType = "query", required = false),
            @ApiImplicitParam(name = "cityName", value = "城市名称", paramType = "query", required = false),
            @ApiImplicitParam(name = "name", value = "店铺名称", paramType = "query", required = false),
            @ApiImplicitParam(name = "shopAddress", value = "店铺地址", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/getWxShopList")
    public ResponseJson getWxShopList(String authStatus, String cityName, String shopName, String shopAddress) {
        List<WxShopWebVo> list = new ArrayList<>();
        try {
            list = wxShopWebService.getWxShopList(authStatus,cityName,shopName,shopAddress);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,list);
        } catch (Exception e) {
            Logger.error("蓝牙APP/微信店铺Web-获取店铺列表出错", e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"系统异常请您稍后再试!");
        }
    }

    @ApiOperation("WEB-授权店铺")
    @ApiResponses({
            @ApiResponse(code = 0, message = "授权成功!"),
            @ApiResponse(code = 1, message = "授权失败!")
    })
    @CustomLog
    @PostMapping("/authWxshop")
    public ResponseJson authWxshop(String shopId) {
        // 校验参数
        if (StringUtil.isEmpty(shopId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"店铺id不能为空");
        }
        try {
            int i= wxShopWebService.authWxShop(shopId);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            Logger.error("蓝牙APP/微信店铺Web-授权店铺出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    @ApiOperation("WEB-拒绝授权店铺")
    @ApiResponses({
            @ApiResponse(code = 0, message = "拒绝授权成功!"),
            @ApiResponse(code = 1, message = "拒绝授权失败!")
    })
    @CustomLog
    @PostMapping("/refuseAuthWxshop")
    public ResponseJson refuseAuthWxshop(String shopId) {
        // 校验参数
        if (StringUtil.isEmpty(shopId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"店铺id不能为空");
        }
        try {
            int i= wxShopWebService.noAuthWxShop(shopId);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            Logger.error("蓝牙APP/微信店铺Web-拒绝授权店铺出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("WEB-删除店铺")
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @CustomLog
    @PostMapping("/deleteWxshop")
    public ResponseJson deleteWxshop(String shopId) {
        // 校验参数
        if (StringUtil.isEmpty(shopId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"店铺id不能为空");
        }
        try {
            int i= wxShopWebService.deleteWxShop(shopId);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            Logger.error("蓝牙APP/微信店铺Web-删除店铺出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("WEB-查询店铺信息")
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @CustomLog
    @PostMapping("/getWxshop")
    public ResponseJson getWxshop(String shopId) {
        // 校验参数
        if (StringUtil.isEmpty(shopId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"店铺id不能为空");
        }
        try {
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,wxShopWebService.getWxShop(shopId));
        } catch (Exception e) {
            Logger.error("蓝牙APP/微信店铺Web-查询店铺信息出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("WEB-更新店铺信息")
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @CustomLog
    @PostMapping("/updateWxshop")
    public ResponseJson updateWxshop(@RequestBody WxShopWebVo wxShopWebVo) {
        // 校验参数
        if (StringUtil.isEmpty(wxShopWebVo.getShopId())
                ||StringUtil.isEmpty(wxShopWebVo.getName())
                ||StringUtil.isEmpty(wxShopWebVo.getMobile())
                ||StringUtil.isEmpty(wxShopWebVo.getShopName())
        ||StringUtil.isEmpty(wxShopWebVo.getShopAddress())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"店铺id/店铺联系人/手机号/店铺名称/店铺地址/不能为空");
        }
        try {
            int i= wxShopWebService.updateWxShop(wxShopWebVo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            Logger.error("蓝牙APP/微信店铺Web-删除店铺出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}