package com.ly.mt.blue.tooth.taste.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 用户烟弹service操作接口
 */
public interface UserTasteService {
    /**
     * 获取烟弹列表
     * @throws Exception
     */
    ResponseJson getTasteList() throws Exception;
    /**
     * 用户添加烟弹
     * @throws Exception
     */
    ResponseJson saveUserTaste(String tasteKey,String addTime,String macAddress) throws Exception;

    /**
     * 获取烟弹最佳匹配
     * @throws Exception
     */
    ResponseJson getBestTaste(String tasteKey) throws Exception;

}