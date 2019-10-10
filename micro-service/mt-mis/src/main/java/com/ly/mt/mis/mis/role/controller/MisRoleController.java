package com.ly.mt.mis.mis.role.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.mis.role.service.MisRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 运营系统角色权限
 *
 * @author taoye
 */
@Controller
@RequestMapping("/mis/role")
public class MisRoleController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MisRoleController.class);
    @Resource
    private MisRoleService service;

    /**
     * 跳转权限设置页面
     *
     * @author taoye
     */
    @RequestMapping("/right")
    public String roleRight() {
        return "/mis/role/mis_role_right";
    }


    /**
     * 新增
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/insertRoleFunc")
    public ResponseJson insertRoleFunc(HttpServletRequest request) {
        try {
            return service.insertRoleFunc(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("RoleFuncController.insertRoleFunc error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * tree
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/listRoleFunc")
    public ResponseJson listRoleFunc(HttpServletRequest request) {
        try {
            return service.listRoleFunc(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("RoleFuncController.listRoleFunc error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}