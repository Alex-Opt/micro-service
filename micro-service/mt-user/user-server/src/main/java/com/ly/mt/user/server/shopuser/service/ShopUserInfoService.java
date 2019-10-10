package com.ly.mt.user.server.shopuser.service;

import com.alibaba.fastjson.JSONObject;

/**
 * b端用户信息操作接口
 */
public interface ShopUserInfoService {

    /**
     * 补全店铺信息
     * @param json
     * @return
     */
    JSONObject finishInfo(String json);

    /**
     * 保存营业执照
     * @param json
     * @return
     */
    JSONObject saveLicenses(String json);

    /**
     * 保存身份信息
     * @return
     */
    JSONObject saveIdCard(String json);


    /**
     * b端用户审核通过
     * @param json
     * @return
     */
    JSONObject updateStatus(String json);

    /**
     * 三要素检测
     * @param json
     * @return
     */
    JSONObject threeElementCheck(String json);


    /**
     * 人像对比
     * @param json  json字符串 真实姓名  身份证号  sdk获取的人脸加密数据包
     * @return
     */
    JSONObject portraitCompare(String json);


    /**
     * B端用户绑定微信
     * @param json
     * @return
     */
    JSONObject bindWx(String json);

    /**
     * 意见反馈
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject userFeedBack(String json) throws Exception;
}
