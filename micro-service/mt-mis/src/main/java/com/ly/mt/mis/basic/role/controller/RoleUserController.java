package com.ly.mt.mis.basic.role.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.basic.role.service.RoleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 系统管理-角色用户管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/basic/role/user")
public class RoleUserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RoleUserController.class);
    @Resource
    private RoleUserService service;

    /**
     * 跳转角色包含用户页面
     *
     * @author taoye
     */
    @RequestMapping("/roleUser")
    public String roleUser() {
        return "/basic/role/role_user";
    }


    /**
     * 角色包含用户表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadRoleUserDatagrid")
    public ResponseJson loadRoleUserDatagrid(HttpServletRequest request) {
        try {
            return service.loadRoleUserDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("RoleUserController.loadRoleUserDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}