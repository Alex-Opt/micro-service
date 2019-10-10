package com.ly.mt.mis.home.order.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.home.order.service.HomebOrderBuyService;
import com.ly.mt.mis.home.order.util.HomeOrderExcelUtil;
import com.ly.mt.mis.home.order.vo.HomebOrderBuyDatagridVo;
import com.ly.mt.mis.home.order.vo.HomebOrderBuyInfoVo;
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
 * MOTI商家端-订单管理-进货订单
 *
 * @author taoye
 */
@Controller
@RequestMapping("/homeb/order/buy")
public class HomebOrderBuyController {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomebOrderBuyController.class);
    @Resource
    private HomebOrderBuyService homebOrderBuyService;

    /**
     * 跳转订单列表页面
     *
     * @author taoye
     */
    @RequestMapping("/order")
    public String order() {
        return "/home/order/homeb_order_buy";
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
        HomebOrderBuyInfoVo vo = new HomebOrderBuyInfoVo();
        try {
            vo = homebOrderBuyService.loadOrderInfo(id);
        } catch (Exception e) {
            LOGGER.error("OrderHbpController.orderInfo loadOrderInfo error ", e);
        }
        model.addAttribute("order", vo);
        return "/home/order/homeb_order_buy_info";
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
            return homebOrderBuyService.loadOrderDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("OrderHbpController.loadOrderDatagrid error ", e);
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
            List<HomebOrderBuyDatagridVo> list = homebOrderBuyService.listOrderExcel(RequestUtil.getJSONObject(request));
            HomeOrderExcelUtil.exportOrderHbp(response, list);
        } catch (Exception e) {
            LOGGER.error("OrderHbpController.exportOrder error ", e);
        }
    }
}