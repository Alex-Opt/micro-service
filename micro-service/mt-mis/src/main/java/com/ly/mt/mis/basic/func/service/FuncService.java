package com.ly.mt.mis.basic.func.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 系统管理-菜单管理
 *
 * @author taoye
 */
public interface FuncService {
    /**
     * 新增
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson insertFunc(JSONObject jsonObject) throws Exception;

    /**
     * 删除
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson deleteFunc(JSONObject jsonObject) throws Exception;

    /**
     * 修改
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson updateFunc(JSONObject jsonObject) throws Exception;

    /**
     * 上移、下移
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson sortFunc(JSONObject jsonObject) throws Exception;

    /**
     * 功能菜单表格树
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadFuncTreegrid(JSONObject jsonObject) throws Exception;

    /**
     * 功能菜单下拉树
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadFuncCombotree(JSONObject jsonObject) throws Exception;

    /**
     * 功能菜单树
     *
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadFuncTree() throws Exception;
}