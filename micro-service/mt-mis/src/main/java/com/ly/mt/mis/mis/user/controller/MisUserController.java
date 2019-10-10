package com.ly.mt.mis.mis.user.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.mis.user.service.MisUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 运营系统用户管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/mis/user")
public class MisUserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MisUserController.class);
    @Resource
    private MisUserService service;

    /**
     * 跳转用户管理页面
     *
     * @author taoye
     */
    @RequestMapping("/user")
    public String user() {
        return "/mis/user/mis_user";
    }


    /**
     * 跳转新增页面
     *
     * @author taoye
     */
    @RequestMapping("/userAdd")
    public String userAdd() {
        return "/mis/user/mis_user_add";
    }


    /**
     * 跳转修改页面
     *
     * @author taoye
     */
    @RequestMapping("/userEdit")
    public String userEdit() {
        return "/mis/user/mis_user_edit";
    }


    /**
     * 跳转用户信息查看页面
     *
     * @author taoye
     */
    @RequestMapping("/userInfo")
    public String userInfo() {
        return "/mis/user/mis_user_info";
    }


    /**
     * 用户信息加载
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadUserInfo")
    public ResponseJson loadUserInfo(HttpServletRequest request) {
        try {
            return service.loadUserInfo(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserMisController.loadUserInfo error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 用户信息分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadUserDatagrid")
    public ResponseJson loadUserDatagrid(HttpServletRequest request) {
        try {
            return service.loadUserDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserMisController.loadUserDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}