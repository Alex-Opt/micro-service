package com.ly.mt.mis.home.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * MOTI到家-客户管理-C端客户
 *
 * @author taoye
 */
public interface HomecUserService {
    /**
     * 客户列表分页表格
     *
     * @param jsonObject 查询条件
     * @return ResponseJson
     * @throws Exception 异常信息
     * @author taoye
     */
    ResponseJson loadUserDatagrid(JSONObject jsonObject) throws Exception;

    /**
     * 客户订单列表分页表格
     *
     * @param jsonObject 查询条件
     * @return ResponseJson
     * @throws Exception 异常信息
     * @author taoye
     */
    ResponseJson loadUserHcOrderDatagrid(JSONObject jsonObject) throws Exception;;
}