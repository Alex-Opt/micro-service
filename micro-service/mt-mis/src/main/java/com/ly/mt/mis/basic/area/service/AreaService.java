package com.ly.mt.mis.basic.area.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 行政区域
 *
 * @author taoye
 */
public interface AreaService {
    /**
     * 行政区域下拉框
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadAreaCombobox(JSONObject jsonObject) throws Exception;

    /**
     * 行政区域树
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadAreaTree(JSONObject jsonObject) throws Exception;
}