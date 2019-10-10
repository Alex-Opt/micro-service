package com.ly.mt.mis.home.user.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.home.user.service.HomebUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * MOTI商家端-商家管理-商家列表
 *
 * @author taoye
 */
@Controller
@RequestMapping("/homeb/user")
public class HomebUserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomebUserController.class);
    @Resource
    private HomebUserService service;

    /**
     * 跳转商家管理页面
     *
     * @author taoye
     */
    @RequestMapping("/user")
    public String user() {
        return "/home/user/homeb_user";
    }

    /**
     * 跳转商家售卖订单查看页面
     *
     * @author taoye
     */
    @RequestMapping("/userHbsOrder")
    public String userHbsOrder() {
        return "/home/user/homeb_user_order_sell";
    }

    /**
     * 跳转商家进货订单查看页面
     *
     * @author taoye
     */
    @RequestMapping("/userHbpOrder")
    public String userHbpOrder() {
        return "/home/user/homeb_user_order_buy";
    }


    /**
     * 商家列表分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadUserDatagrid")
    public ResponseJson loadUserDatagrid(HttpServletRequest request) {
        try {
            return service.loadUserDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserHbController.loadUserDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 商家售卖订单列表分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadUserHbsOrderDatagrid")
    public ResponseJson loadUserHbsOrderDatagrid(HttpServletRequest request) {
        try {
            return service.loadUserHbsOrderDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserHbController.loadUserHbsOrderDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 商家进货订单列表分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadUserHbpOrderDatagrid")
    public ResponseJson loadUserHbpOrderDatagrid(HttpServletRequest request) {
        try {
            return service.loadUserHbpOrderDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("UserHbController.loadUserHbpOrderDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}