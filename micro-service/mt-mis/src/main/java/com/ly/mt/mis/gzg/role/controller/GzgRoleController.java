package com.ly.mt.mis.gzg.role.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.gzg.role.service.GzgRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 格子柜角色权限
 *
 * @author taoye
 */
@Controller
@RequestMapping("/gzg/role")
public class GzgRoleController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgRoleController.class);
    @Resource
    private GzgRoleService service;

    /**
     * 跳转权限设置页面
     *
     * @author taoye
     */
    @RequestMapping("/right")
    public String roleRight() {
        return "/gzg/role/gzg_role_right";
    }


    /**
     * 新增
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/insertRoleArea")
    public ResponseJson insertRoleArea(HttpServletRequest request) {
        try {
            return service.insertRoleArea(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("RoleGbController.insertRoleArea error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * tree
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/listRoleArea")
    public ResponseJson listRoleArea(HttpServletRequest request) {
        try {
            return service.listRoleArea(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("RoleGbController.listRoleArea error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}