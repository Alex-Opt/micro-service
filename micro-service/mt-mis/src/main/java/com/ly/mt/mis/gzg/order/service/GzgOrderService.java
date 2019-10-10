package com.ly.mt.mis.gzg.order.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.mis.gzg.order.vo.GzgOrderDatagridVo;
import com.ly.mt.mis.gzg.order.vo.GzgOrderInfoVo;

import java.util.List;

/**
 * MOTI售卖柜-订单管理-订单列表
 *
 * @author taoye
 */
public interface GzgOrderService {
    /**
     * 订单详情
     *
     * @author taoye
     */
    GzgOrderInfoVo loadOrderInfo(String id) throws Exception;

    /**
     * 订单列表分页表格
     *
     * @author taoye
     */
    ResponseJson loadOrderDatagrid(JSONObject jsonObject) throws Exception;

    /**
     * 导出Excel数据
     *
     * @author taoye
     */
    List<GzgOrderDatagridVo> listOrderExcel(JSONObject jsonObject) throws Exception;
}