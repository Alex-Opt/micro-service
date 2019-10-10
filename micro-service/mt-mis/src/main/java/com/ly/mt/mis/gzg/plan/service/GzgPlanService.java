package com.ly.mt.mis.gzg.plan.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 配货方案管理
 *
 * @author taoye
 */
public interface GzgPlanService {
    /**
     * 新增
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson checkName(JSONObject jsonObject) throws Exception;

    /**
     * 新增
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson insertPlan(JSONObject jsonObject) throws Exception;

    /**
     * 修改
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson updatePlan(JSONObject jsonObject) throws Exception;

    /**
     * 配货方案分页表格
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadPlanDatagrid(JSONObject jsonObject) throws Exception;
}
