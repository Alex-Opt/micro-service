package com.ly.mt.mis.basic.user.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.basic.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 用户管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/basic/user")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService service;


    /**
     * 新增
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/insertUser")
    public ResponseJson insertUser(HttpServletRequest request) {
        try {
            return service.insertUser(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserController.insertUser error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 修改
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public ResponseJson updateUser(HttpServletRequest request) {
        try {
            return service.updateUser(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserController.updateUser error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 禁用/启用
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/updateValidStatus")
    public ResponseJson updateValidStatus(HttpServletRequest request) {
        try {
            return service.updateValidStatus(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserController.updateValidStatus error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 密码重置
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/resetPassword")
    public ResponseJson resetPassword(HttpServletRequest request) {
        try {
            return service.resetPassword(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserController.resetPassword error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 校验帐号是否存在
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/checkLoginName")
    public ResponseJson checkLoginName(HttpServletRequest request) {
        try {
            return service.checkLoginName(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserController.checkLoginName error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 校验帐号是否存在
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/checkMobile")
    public ResponseJson checkMobile(HttpServletRequest request) {
        try {
            return service.checkMobile(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserController.checkMobile error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}