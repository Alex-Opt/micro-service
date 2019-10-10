package com.ly.mt.mis.home.user.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.home.user.service.HomecUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * MOTI到家-客户管理-C端客户
 *
 * @author taoye
 */
@Controller
@RequestMapping("/homec/user")
public class HomecUserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomecUserController.class);
    @Resource
    private HomecUserService service;

    /**
     * 跳转用户管理页面
     *
     * @author taoye
     */
    @RequestMapping("/user")
    public String user() {
        return "/home/user/homec_user";
    }

    /**
     * 跳转用户订单查看页面
     *
     * @author taoye
     */
    @RequestMapping("/userOrderInfo")
    public String userOrderInfo() {
        return "/home/user/homec_user_order";
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
            LOGGER.error("UserHcController.loadUserDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 客户订单列表分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadUserHcOrderDatagrid")
    public ResponseJson loadUserHcOrderDatagrid(HttpServletRequest request) {
        try {
            return service.loadUserHcOrderDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserHcController.loadUserOrderDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}