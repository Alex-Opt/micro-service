package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserCertificationService {

    /**
     * 插入用户认证信息
     * @param jsonObject
     * @return
     */
    ResponseJson insertUserCertification(JSONObject jsonObject);


    /**
     * 更新用户认证信息
     * @param jsonObject
     * @return
     */
    ResponseJson  updateUserCertification(JSONObject jsonObject);


    /**
     * 查询用户认证信息
     * @param jsonObject
     * @return
     */
    ResponseJson selectUserCertification(JSONObject jsonObject);

    /**
     * 查询用户是否已认证成功
     * @param jsonObject
     * @return
     */
    ResponseJson getUserCertificationByIdCardMobileName(JSONObject jsonObject);

    /**
     * 根据身份证号查询是否已经存在与用户认证中。
     * @param jsonObject
     * @return
     */
    ResponseJson selectUserCertificationByCardId(JSONObject jsonObject);
}
