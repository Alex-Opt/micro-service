package com.ly.mt.center.third.gt.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author zhanglifeng
 * @description 个推接口层
 */
public interface GtPushMessageService {
    /**
     * @Description 推送消息给个人
     * @Author zhanglifeng
     */
    ResponseJson pushSingle(JSONObject jsonObject) throws Exception;

    /**
     * @Description 推送消息给多个人
     * @Author zhanglifeng
     */
    ResponseJson pushList(JSONObject jsonObject) throws Exception;


    /**
     * @Description 推送消息给一个区域，某个分组的app用户
     * @Author zhanglifeng
     */
    ResponseJson pushAPP(JSONObject jsonObject) throws Exception;
}