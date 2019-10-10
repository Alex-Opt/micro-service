package com.ly.mt.mis.basic.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.basic.user.service.UserRoleService;
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
 * 运营系统用户角色管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/basic/user/role")
public class UserRoleController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserRoleController.class);
    @Resource
    private UserRoleService service;

    /**
     * 跳转用户角色设置页面
     *
     * @author taoye
     */
    @RequestMapping("/userRole")
    public String userRole(HttpServletRequest request, Model model) {
        JSONObject jsonObject = RequestUtil.getJSONObject(request);
        model.addAttribute("userId", jsonObject.getString("id"));
        model.addAttribute("projectType", jsonObject.getString("projectType"));
        return "/basic/user/user_role";
    }


    /**
     * 新增
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/insertUserRole")
    public ResponseJson insertUserRole(HttpServletRequest request) {
        try {
            return service.insertUserRole(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserMisRoleController.insertUserRole error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 删除
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/deleteUserRole")
    public ResponseJson deleteUserRole(HttpServletRequest request) {
        try {
            return service.deleteUserRole(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserMisRoleController.deleteUserRole error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 已分配角色表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadUserRoleDatagrid")
    public ResponseJson loadUserRoleDatagrid(HttpServletRequest request) {
        try {
            return service.loadUserRoleDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserMisRoleController.loadUserRoleDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 未分配角色表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadRoleDatagrid")
    public ResponseJson loadRoleDatagrid(HttpServletRequest request) {
        try {
            return service.loadRoleDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserMisRoleController.loadRoleDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}