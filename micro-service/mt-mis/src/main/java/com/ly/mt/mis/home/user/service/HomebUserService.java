package com.ly.mt.mis.home.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * MOTI商家端-商家管理-商家列表
 *
 * @author taoye
 */
public interface HomebUserService {
    /**
     * 商家列表分页表格
     *
     * @param jsonObject 查询条件
     * @return ResponseJson
     * @throws Exception 异常信息
     * @author taoye
     */
    ResponseJson loadUserDatagrid(JSONObject jsonObject) throws Exception;

    /**
     * 商家售卖订单列表分页表格
     *
     * @param jsonObject 查询条件
     * @return ResponseJson
     * @throws Exception 异常信息
     * @author taoye
     */
    ResponseJson loadUserHbsOrderDatagrid(JSONObject jsonObject) throws Exception;

    /**
     * 商家进货订单列表分页表格
     *
     * @param jsonObject 查询条件
     * @return ResponseJson
     * @throws Exception 异常信息
     * @author taoye
     */
    ResponseJson loadUserHbpOrderDatagrid(JSONObject jsonObject) throws Exception;
}