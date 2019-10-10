package com.ly.mt.open.wechat.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.open.wechat.config.WechatAccountConfig;
import com.ly.mt.open.wechat.util.WxUtil;
import com.ly.mt.open.wechat.vo.WechatSingnatureVo;
import com.ly.mt.open.wechat.vo.WechatUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 微信公众号认证及其jssdk操作
 * <p>
 * 公众号auth认证使用流程，先请求auth方法，参数url为各自业务的需要重定向回去的url，-
 * ，返回响应后前端需要在发响应参数中的redirectUrl进行请求校验，然后info方法将-
 * 返回必要的openid及其其他的user信息。
 * <p>
 * jssdk使用流程，直接请求jsSdk方法，参数为当前需要调起wechat jssdk页面的url
 *
 * @author pjt
 */
@Api("微信公众号认证登录及其jssdk调起验证")
@RestController
@RequestMapping("/open/pub/wechat")
public class WechatAuthContraller {

    private final static Logger log = LoggerFactory.getLogger(WechatAuthContraller.class);

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    /**
     * 微信公众号认证登录
     *
     * @param url 原始业务url，也就是认证登录完成后需要重新定向回去的url
     * @return
     */
    @ApiOperation("公众号认证登录")
    @PostMapping("/jsapi/auth")
    public ResponseJson auth(@ApiParam(name = "url", value = "前台业务页面当前的url或者认证路完成后要跳转的地址") @RequestBody String url,
                             HttpServletRequest request) {

        String serverInnerUrl = wechatAccountConfig.getInnerAuthUrl();
        try {
            String redirect = wxMpService.oauth2buildAuthorizationUrl(serverInnerUrl, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(url, "UTF-8"));
            Map<String, Object> map = new HashMap<>(1);
            map.put("redirectUrl", redirect);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, map);
        } catch (Exception e) {
            log.error("微信认证失败，e={}", e);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_WECHAT_AUTH_ERROR, "认证登录失败");
        }

    }


    /**
     * 获取微信用户信息，由auth方法重定向过来的
     * TODO  @RequestParam("state") String returnUrl 预留
     * 静默方式授权调用该接口会报48001错误,静默方式授权只能获取openid
     *
     * @param code
     * @return
     */
    @ApiOperation("公众号认证登录返回用户及其openid信息")
    @GetMapping("/jsapi/userInfo")
    public ResponseJson userInfo(@RequestParam("code") String code) {
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            WechatUserVo vo = new WechatUserVo();
            BeanUtils.copyProperties(wxMpUser, vo);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, vo);
        } catch (Exception e) {
            log.error("认证失败，e={}", e);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_WECHAT_AUTH_ERROR, "认证登录失败");
        }
    }


    /**
     * @param url 前台调起jssdk页面的地址
     * @return
     * @deprecated 验证jssdk
     * 做微信公众号调起jssdk验证信息使用
     * 前端js可用分享，气泡弹窗等各种组件
     */
    @ApiOperation("调起前端JSSDK")
    @PostMapping("/jsapi/jssdk")
    public ResponseJson jsSdk(@RequestBody String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
            String jsapiTicket = wxMpService.getJsapiTicket();
            WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(url);
            WechatSingnatureVo vo = new WechatSingnatureVo();
            BeanUtils.copyProperties(jsapiSignature, vo);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, vo);
        } catch (Exception e) {
            log.error("微信jssdk获取异常 ={}", e.getMessage());
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_ERROR, "invoking error");
        }
    }

    /**
     * 微信jssdk
     *
     * @param url
     * @return
     */
    @ApiOperation("测试jssdk")
    @GetMapping("/jsapi/jssdk2")
    public ResponseJson jssdk2(@RequestParam String url) {
        try {
            Map<String, String> sign = WxUtil.getSign(url);
            sign.put("appId", "wx80a7401a02e0f8ec");
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, sign);
        } catch (Exception e) {
            log.error("e={}", e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

}

