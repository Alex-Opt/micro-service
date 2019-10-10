package com.ly.mt.mis.customer.order.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.customer.order.service.CustomerOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 客服后台-查询工具-订单查询
 *
 * @author taoye
 */
@Controller
@RequestMapping("/customer/order")
public class CustomerOrderController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerOrderController.class);
    @Resource
    private CustomerOrderService service;

    /**
     * 跳转订单查询页面
     *
     * @author taoye
     */
    @RequestMapping("/order")
    public String order() {
        return "/customer/order/customer_order";
    }


    /**
     * 订单信息分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadOrderDatagrid")
    public ResponseJson loadOrderDatagrid(HttpServletRequest request) {
        try {
            return service.loadOrderDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("OrderController.loadOrderDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}