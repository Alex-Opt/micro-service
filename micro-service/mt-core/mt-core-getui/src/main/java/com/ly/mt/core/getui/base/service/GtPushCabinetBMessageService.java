package com.ly.mt.core.getui.base.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author wanghongliang
 * @description 个推接口层
 */
public interface GtPushCabinetBMessageService {
    /**
     * @Description 推送消息给个人
     */
    ResponseJson pushSingle(JSONObject jsonObject) throws Exception;

    /**
     * @Description 推送消息给多个人
     */
    ResponseJson pushList(JSONObject jsonObject) throws Exception;


    /**
     * @Description 推送消息给一个区域，某个分组的app用户
     */
    ResponseJson pushAPP(JSONObject jsonObject) throws Exception;
}