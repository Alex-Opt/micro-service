package com.ly.mt.mis.mis.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 系统管理-用户管理
 *
 * @author taoye
 */
public interface MisUserService {
    /**
     * 用户信息加载
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadUserInfo(JSONObject jsonObject) throws Exception;

    /**
     * 用户信息分页表格
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadUserDatagrid(JSONObject jsonObject) throws Exception;
}
