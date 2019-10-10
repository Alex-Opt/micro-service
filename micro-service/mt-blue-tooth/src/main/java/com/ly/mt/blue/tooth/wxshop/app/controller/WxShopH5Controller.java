package com.ly.mt.blue.tooth.wxshop.app.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.base.config.YmlConfig;
import com.ly.mt.blue.tooth.base.util.IPUtil;
import com.ly.mt.blue.tooth.wxshop.app.service.WxShopH5Service;
import com.ly.mt.blue.tooth.wxshop.app.vo.WxShopH5Vo;
import com.ly.mt.blue.tooth.wxshop.app.vo.WxSignatureVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "微信店铺h5操作接口")
@RestController
@RequestMapping("/bluetooth/h5/wxshop")
public class WxShopH5Controller {
    private final static Logger Logger = LoggerFactory.getLogger(WxShopH5Controller.class);
    @Resource
    WxShopH5Service wxShopAppletService;
    @Resource
    YmlConfig yml;

    @ApiOperation("获取微信公众号签名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "用到微信公众号签名的URL", paramType = "query", required = false)})
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/getSignature")
    public ResponseJson<WxSignatureVo> getSignature(HttpServletRequest request,@RequestParam(value = "url") String url) {
        try {
//            String requestIp = IPUtil.getIPAddress(request);
//            Logger.info("蓝牙获取signature-请求IP====="+requestIp);
            String requestHost = request.getHeader("host");
            Logger.info("蓝牙获取signature-请求HOST====="+requestHost);

            String configIp = yml.getSignatureUrl();
            Logger.info("蓝牙获取signature-配置ip====="+yml.getSignatureUrl());
            //String url  = String.valueOf(request.getRequestURL());//获得到的是docke请求的完整路径
//            Logger.info("蓝牙APP-获取签名访问绝对路径url为"+url);
           // String url  =request.getScheme()+"://"+configIp+"/lbssrclist/lbslist.html" ;//需要获取签名使用的token
            Logger.info("蓝牙获取signature-需要使用签名的页面路径为====="+url);
            url = URLDecoder.decode(url,"UTF-8");
            Logger.info("蓝牙获取signature-decode需要使用签名的页面路径为====="+url);
//            if(!requestHost.equals(configIp)){
//                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"请求路径不在规定路径出错");
//            }
            return wxShopAppletService.getWxSignature(url);
        } catch (Exception e) {
            Logger.error("蓝牙APP/微信小程序店铺-获取店铺列表出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation("获取店铺列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopAddress", value = "店铺地址", paramType = "query", required = false),
            @ApiImplicitParam(name = "coordinate", value = "腾讯地图坐标", example = "39.894416,116.690420",paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/getWxShopList")
    public ResponseJson<List<WxShopH5Vo>> getWxShopList(String shopAddress, String coordinate) {
        try {
            return wxShopAppletService.getWxShopList(shopAddress,coordinate);
        } catch (Exception e) {
            Logger.error("蓝牙APP/微信小程序店铺-获取店铺列表出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("添加店铺/需要中请求head 中添加Content-Type=application/json")
    @ApiResponses({
            @ApiResponse(code = 0, message = "添加成功!"),
            @ApiResponse(code = 1, message = "添加失败!")
    })
    @CustomLog
    @PostMapping("/addWxShop")
    public ResponseJson addShop(@RequestBody WxShopH5Vo wxShopH5Vo) {
        // 校验参数
        if (StringUtil.isEmpty(wxShopH5Vo.getName()) || StringUtil.isEmpty(wxShopH5Vo.getMobile())
                ||StringUtil.isEmpty(wxShopH5Vo.getShopName())||StringUtil.isEmpty(wxShopH5Vo.getMainBusiness())
        ||StringUtil.isEmpty(wxShopH5Vo.getShopAddress())||StringUtil.isEmpty(wxShopH5Vo.getBusinessHours())
        ||StringUtil.isEmpty(wxShopH5Vo.getPhotoUrl())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"姓名/手机号/店铺名称/主营业务/店铺地址/营业时间/图片不能为空");
        }
        try {
            return wxShopAppletService.addWxShop(wxShopH5Vo);
        } catch (Exception e) {
            Logger.error("蓝牙APP/微信小程序店铺-添加店铺出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("上传门店图片")
    @ApiResponses({
            @ApiResponse(code = 0, message = "上传成功!"),
            @ApiResponse(code = 1, message = "上传失败!")
    })
    @CustomLog
    @PostMapping("/uploadWxShopLogo")
    public ResponseJson uploadWxShopLogo(@ApiParam(value = "门店Logo", name = "file", required = true) MultipartFile file) {
        // 校验参数
        if (null==file) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"图片不能为空");
        }
        try {
            return wxShopAppletService.uploadWxShopLogo(file);
        } catch (Exception e) {
            Logger.error("蓝牙APP/微信小程序店铺-上传门店logo出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}