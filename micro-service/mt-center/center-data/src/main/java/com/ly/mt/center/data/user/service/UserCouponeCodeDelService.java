package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserCouponeCodeDelService {
    /**
     * @Description 保存UserCouponeCodeDel
     * @Author taoye
     */
    ResponseJson insertUserCouponeCodeDel(JSONObject jsonObject);

    /**
     * @Description 删除UserCouponeCodeDel
     * @Author taoye
     */
    ResponseJson deleteUserCouponeCodeDel(JSONObject jsonObject);

    /**
     * @Description 更新UserCouponeCodeDel
     * @Author taoye
     */
    ResponseJson updateUserCouponeCodeDel(JSONObject jsonObject);

    /**
     * @Description 查询UserCouponeCodeDel
     * @Author taoye
     */
    ResponseJson getUserCouponeCodeDel(JSONObject jsonObject);
}