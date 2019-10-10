package com.ly.mt.mis.home.order.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.mis.home.order.vo.HomebOrderSellDatagridVo;
import com.ly.mt.mis.home.order.vo.HomebOrderSellInfoVo;

import java.util.List;

/**
 * MOTI商家端-订单管理-售卖订单
 *
 * @author taoye
 */
public interface HomebOrderSellService {
    /**
     * 订单详情
     *
     * @param id 订单ID
     * @return HomebOrderSellInfoVo
     * @throws Exception 异常信息
     * @author taoye
     */
    HomebOrderSellInfoVo loadOrderInfo(String id) throws Exception;

    /**
     * 订单列表分页表格
     *
     * @param jsonObject 查询条件
     * @return ResponseJson
     * @throws Exception 异常信息
     * @author taoye
     */
    ResponseJson loadOrderDatagrid(JSONObject jsonObject) throws Exception;

    /**
     * 导出Excel数据
     *
     * @param jsonObject 查询条件
     * @return List<HomebOrderSellDatagridVo>
     * @throws Exception 异常信息
     * @author taoye
     */
    List<HomebOrderSellDatagridVo> listOrderExcel(JSONObject jsonObject) throws Exception;
}