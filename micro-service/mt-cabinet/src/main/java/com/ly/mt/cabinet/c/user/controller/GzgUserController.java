package com.ly.mt.cabinet.c.user.controller;

import com.ly.mt.cabinet.c.user.entity.GzgUserLoginVo;
import com.ly.mt.cabinet.c.user.entity.GzgUserVo;
import com.ly.mt.cabinet.c.user.service.GzgLoginService;
import com.ly.mt.core.base.entity.ResponseCode;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/cabinet/c/gzg/user")
@Api(value = "GZGUserController", tags = "user", description = "GzgUser Controller")
public class GzgUserController {
    @Resource
    GzgLoginService gzgLoginService;
    private static final Logger LOGGER = LoggerFactory.getLogger(GzgUserController.class);

    @ApiOperation(value = "用户手机动态验证码登录", notes = "登录成功后返回result实体")
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/mobileLogin")
    public ResponseJson mobileLogin(@RequestBody GzgUserLoginVo userLoginVo, HttpServletRequest request) {
        String mobile = userLoginVo.getMobile();
        String dynamicCode = userLoginVo.getDynamicCode();
        // 校验参数
        if (!StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数错误");
        }

        try {
            ResponseJson responseJson = gzgLoginService.mobileLogin(userLoginVo);
            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 用户登录保存session
     * @Author taoye
     */
    private void saveSession(HttpServletRequest request, GzgUserVo user) {
        // 存储常用信息
        HttpSession session = request.getSession();
        session.setAttribute("token",  user.getToken());
        session.setAttribute("userId", user.getId());
        session.setAttribute("mobile", user.getMobile());
        LOGGER.info("保存session:userId=" + user.getId());
        // 刷新session有效时间30分钟
        session.setMaxInactiveInterval(10800);
    }


}