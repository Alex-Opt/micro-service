package com.ly.mt.mis.home.order.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.home.order.service.HomebOrderSellService;
import com.ly.mt.mis.home.order.util.HomeOrderExcelUtil;
import com.ly.mt.mis.home.order.vo.HomebOrderSellDatagridVo;
import com.ly.mt.mis.home.order.vo.HomebOrderSellInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * MOTI商家端-订单管理-售卖订单
 *
 * @author taoye
 */
@Controller
@RequestMapping("/homeb/order/sell")
public class HomebOrderSellController {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomebOrderSellController.class);
    @Resource
    private HomebOrderSellService service;

    /**
     * 跳转订单列表页面
     *
     * @author taoye
     */
    @RequestMapping("/order")
    public String order() {
        return "/home/order/homeb_order_sell";
    }


    /**
     * 跳转订单详情页面
     *
     * @author taoye
     */
    @RequestMapping("/orderInfo")
    public String orderInfo(HttpServletRequest request, Model model) {
        String id = RequestUtil.getJSONObject(request).getString("id");
        // 订单信息
        HomebOrderSellInfoVo vo = null;
        try {
            vo = service.loadOrderInfo(id);
        } catch (Exception e) {
            LOGGER.error("OrderHbsController.orderInfo loadOrderInfo error ", e);
        }
        model.addAttribute("order", vo);
        return "/home/order/homeb_order_sell_info";
    }


    /**
     * 订单列表分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadOrderDatagrid")
    public ResponseJson loadOrderDatagrid(HttpServletRequest request) {
        try {
            return service.loadOrderDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("OrderHbsController.loadOrderDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 导出Excel
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/exportOrder")
    public void exportOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<HomebOrderSellDatagridVo> list = service.listOrderExcel(RequestUtil.getJSONObject(request));
            HomeOrderExcelUtil.exportOrderHbs(response, list);
        } catch (Exception e) {
            LOGGER.error("OrderHbsController.exportOrder error ", e);
        }
    }
}