package com.ly.mt.mis.home.order.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.mis.home.order.vo.HomebOrderBuyDatagridVo;
import com.ly.mt.mis.home.order.vo.HomebOrderBuyInfoVo;

import java.util.List;

/**
 * MOTI商家端-订单管理-进货订单
 *
 * @author taoye
 */
public interface HomebOrderBuyService {
    /**
     * 订单详情
     *
     * @author taoye
     */
    HomebOrderBuyInfoVo loadOrderInfo(String id) throws Exception;

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
    List<HomebOrderBuyDatagridVo> listOrderExcel(JSONObject jsonObject) throws Exception;
}