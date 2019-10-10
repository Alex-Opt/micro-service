package com.ly.mt.mis.gzg.user.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.gzg.user.service.GzgUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * MOTI售卖柜-用户管理-用户列表
 *
 * @author taoye
 */
@Controller
@RequestMapping("/gzg/user")
public class GzgUserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgUserController.class);
    @Resource
    private GzgUserService service;

    /**
     * 跳转用户管理页面
     *
     * @author taoye
     */
    @RequestMapping("/user")
    public String userGb() {
        return "/gzg/user/gzg_user";
    }


    /**
     * 跳转新增页面
     *
     * @author taoye
     */
    @RequestMapping("/userAdd")
    public String userAdd() {
        return "/gzg/user/gzg_user_add";
    }


    /**
     * 跳转修改页面
     *
     * @author taoye
     */
    @RequestMapping("/userEdit")
    public String userEdit() {
        return "/gzg/user/gzg_user_edit";
    }


    /**
     * 客户列表分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadUserDatagrid")
    public ResponseJson loadUserDatagrid(HttpServletRequest request) {
        try {
            return service.loadUserDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserGbController.loadUserDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}