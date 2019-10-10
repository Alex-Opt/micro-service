package com.ly.mt.mis.gzg.order.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.gzg.order.service.GzgOrderService;
import com.ly.mt.mis.gzg.order.util.GzgOrderExcelUtil;
import com.ly.mt.mis.gzg.order.vo.GzgOrderDatagridVo;
import com.ly.mt.mis.gzg.order.vo.GzgOrderInfoVo;
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
 * MOTI售卖柜-订单管理-订单列表
 *
 * @author taoye
 */
@Controller
@RequestMapping("/gzg/order")
public class GzgOrderController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgOrderController.class);
    @Resource
    private GzgOrderService service;

    /**
     * 跳转订单列表页面
     *
     * @author taoye
     */
    @RequestMapping("/order")
    public String order() {
        return "/gzg/order/gzg_order";
    }

    /**
     * 跳转订单详情页面
     *
     * @author taoye
     */
    @RequestMapping("/orderInfo")
    public String orderInfo(HttpServletRequest request, Model model) {
        String id = RequestUtil.getJSONObject(request).getString("id");
        GzgOrderInfoVo gzgOrderInfoVo = null;
        try {
            gzgOrderInfoVo = service.loadOrderInfo(id);
        } catch (Exception e) {
            LOGGER.error("GzgOrderController.orderInfo loadOrderInfo error ", e);
        }
        model.addAttribute("order", gzgOrderInfoVo);
        return "/gzg/order/gzg_order_info";
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
            LOGGER.error("GzgOrderController.loadOrderDatagrid error ", e);
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
            List<GzgOrderDatagridVo> list = service.listOrderExcel(RequestUtil.getJSONObject(request));
            GzgOrderExcelUtil.exportOrderGzg(response, list);
        } catch (Exception e) {
            LOGGER.error("GzgOrderController.exportOrder error ", e);
        }
    }
}