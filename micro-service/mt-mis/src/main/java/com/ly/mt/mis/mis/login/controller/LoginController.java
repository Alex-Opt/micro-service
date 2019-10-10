package com.ly.mt.mis.mis.login.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.mis.login.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 登录
 *
 * @author taoye
 */
@Controller
@RequestMapping("/mis/login")
public class LoginController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private LoginService service;

    /**
     * 浏览器限制提醒页面
     *
     * @author taoye
     */
    @RequestMapping("/browser")
    public String browser() {
        return "/mis/login/browser";
    }

    /**
     * 登录页面
     *
     * @author taoye
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        String errorCount = "0";
        try {
            errorCount = service.getLoginErrorCount(request);
        } catch (Exception e) {
            LOGGER.error("LoginController.login getLoginErrorCount error ", e);
        }
        model.addAttribute("error", errorCount);
        return "/mis/login/login";
    }

    /**
     * 登录
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loginIn")
    public ResponseJson loginIn(HttpServletRequest request) {
        try {
            return service.loginIn(request);
        } catch (Exception e) {
            LOGGER.error("LoginController.loginIn error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 登出
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loginOut")
    public ResponseJson loginOut(HttpServletRequest request) {
        try {
            return service.loginOut(request);
        } catch (Exception e) {
            LOGGER.error("LoginController.loginOut error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}