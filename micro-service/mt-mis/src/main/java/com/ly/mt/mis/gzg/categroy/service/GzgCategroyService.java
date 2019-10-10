package com.ly.mt.mis.gzg.categroy.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 货柜类型管理
 *
 * @author taoye
 */
public interface GzgCategroyService {
    /**
     * 校验货柜名称
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson checkName(JSONObject jsonObject) throws Exception;

    /**
     * 货柜类型新增
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson insertCategroy(JSONObject jsonObject) throws Exception;

    /**
     * 货柜类型修改
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson updateCategroy(JSONObject jsonObject) throws Exception;

    /**
     * 货柜类型分页表格
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadCategroyDatagrid(JSONObject jsonObject) throws Exception;


    /**
     * 货柜类型下拉框
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadCategroyComboboxSelect(JSONObject jsonObject) throws Exception;
}