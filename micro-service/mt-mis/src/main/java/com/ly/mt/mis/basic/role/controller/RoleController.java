package com.ly.mt.mis.basic.role.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.basic.role.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 系统管理-角色管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/basic/role")
public class RoleController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    private final static String LOAD_ROLE_TREEGRID_TYPE = "load";
    private final static String LOAD_TYPE = "type";
    @Resource
    private RoleService service;

    /**
     * 跳转角色管理页面
     *
     * @author taoye
     */
    @RequestMapping("/role")
    public String role() {
        return "/basic/role/role";
    }


    /**
     * 跳转新增页面
     *
     * @author taoye
     */
    @RequestMapping("/roleAdd")
    public String roleAdd() {
        return "/basic/role/role_add";
    }


    /**
     * 跳转修改页面
     *
     * @author taoye
     */
    @RequestMapping("/roleEdit")
    public String roleEdit() {
        return "/basic/role/role_edit";
    }


    /**
     * 新增
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/insertRole")
    public ResponseJson insertRole(HttpServletRequest request) {
        try {
            return service.insertRole(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("RoleController.insertRole error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 修改
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/updateRole")
    public ResponseJson updateRole(HttpServletRequest request) {
        try {
            return service.updateRole(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("RoleController.updateRole error ", e);
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
            LOGGER.error("RoleController.updateValidStatus error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 角色表格树
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadRoleTreegrid")
    public ResponseJson loadRoleTreegrid(HttpServletRequest request) {
        try {
            JSONObject jsonObject = RequestUtil.getJSONObject(request);
            if (LOAD_ROLE_TREEGRID_TYPE.equals(jsonObject.getString(LOAD_TYPE))) {
                return service.loadRoleTreegrid(jsonObject);
            } else {
                return service.restRoleTreegrid(jsonObject);
            }
        } catch (Exception e) {
            LOGGER.error("RoleController.loadRoleTreegrid error ", e);
            return null;
        }
    }


    /**
     * 角色下拉表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadRoleCombotree")
    public ResponseJson loadRoleCombotree(HttpServletRequest request) {
        try {
            return service.loadRoleCombotree(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("RoleController.loadRoleCombotree error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}