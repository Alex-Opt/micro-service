package com.ly.mt.mall.h5.wxshare.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.h5.wxshare.service.impl.WxShareServiceImpl;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_PARAM_NEED;

/**
 * @Author wanglong
 */
@Api("微信分享")
@RestController
@RequestMapping("/mall/h5/wxshare")
public class WxShareController {

    @Resource
    private WxShareServiceImpl wxShareService;

    @ApiOperation(value = "获取分享需要的参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "分享的页面url", paramType = "query", required = true)
    })
    @PostMapping("/getShareParam")
    public ResponseJson getShareParam(@RequestParam(value = "url", required = true) String url) {
        return wxShareService.getShareParam(url);
    }

    /**
     * 微信分享的二维码生成页面。
     *
     * @param scene
     * @param page
     * @param width
     * @param auto_color
     * @param line_color
     * @param is_hyaline
     * @param request
     * @return
     */
    @ApiOperation(value = "微信小程序：分享二维码生成接口", notes = "通过该接口生成的小程序码（太阳码），永久有效，数量暂无限制")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scene", value = "前端传入的二维码参数", paramType = "query", required = true),
            @ApiImplicitParam(name = "page", value = "必须是已经发布的小程序存在的页面", paramType = "query", required = true),
            @ApiImplicitParam(name = "width", value = "二维码的宽度，单位 px，最小 280px，最大 1280px", paramType = "query", required = false),
            @ApiImplicitParam(name = "auto_color", value = "自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false", paramType = "query", required = false),
            @ApiImplicitParam(name = "line_color", value = "auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {\"r\":\"xxx\",\"g\":\"xxx\",\"b\":\"xxx\"} 十进制表示", paramType = "query", required = false),
            @ApiImplicitParam(name = "is_hyaline", value = "是否需要透明底色，为 true 时，生成透明底色的小程序\n", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/getWxaCodeUnLimit")
    public ResponseJson getWxaCodeUnLimit(@RequestParam(value = "scene") String scene,
                                          @RequestParam(value = "page") String page,
                                          @RequestParam(value = "width", required = false) String width,
                                          @RequestParam(value = "auto_color", required = false) String auto_color,
                                          @RequestParam(value = "line_color", required = false) String line_color,
                                          @RequestParam(value = "is_hyaline", required = false) String is_hyaline,
                                          HttpServletRequest request) {
        // 校验参数
        if (StringUtil.isEmpty(scene) || StringUtil.isEmpty(page)) {
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "参数缺失!");
        }
        JSONObject params = new JSONObject();
        params.put("scene", scene);
        params.put("page", page);
        if (StringUtil.isNotEmpty(width)) {
            params.put("width", width);
        }
        if (StringUtil.isNotEmpty(auto_color)) {
            params.put("auto_color", auto_color);
        }
        if (StringUtil.isNotEmpty(line_color)) {
            params.put("line_color", line_color);
        }
        if (StringUtil.isNotEmpty(is_hyaline)) {
            params.put("is_hyaline", is_hyaline);
        }
        return wxShareService.getWxaCodeUnLimit(params);
    }
}
