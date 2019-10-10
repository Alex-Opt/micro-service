package com.ly.mt.mis.home.order.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.mis.home.order.vo.HomecOrderDatagridVo;
import com.ly.mt.mis.home.order.vo.HomecOrderInfoVo;

import java.util.List;

/**
 * MOTI到家-订单管理-订单列表
 *
 * @author taoye
 */
public interface HomecOrderService {
    /**
     * 订单详情
     *
     * @param id 订单ID
     * @return HomecOrderInfoVo
     * @throws Exception 异常信息
     * @author taoye
     */
    HomecOrderInfoVo loadOrderInfo(String id) throws Exception;

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
     * @return List<HomecOrderDatagridVo>
     * @throws Exception 异常信息
     * @author taoye
     */
    List<HomecOrderDatagridVo> listOrderExcel(JSONObject jsonObject) throws Exception;
}