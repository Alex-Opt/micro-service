package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserFeedbackImagesService {
    /**
     * @Description 保存UserFeedbackImages
     * @Author taoye
     */
    ResponseJson insertUserFeedbackImages(JSONObject jsonObject);

    /**
     * @Description 删除UserFeedbackImages
     * @Author taoye
     */
    ResponseJson deleteUserFeedbackImages(JSONObject jsonObject);

    /**
     * @Description 更新UserFeedbackImages
     * @Author taoye
     */
    ResponseJson updateUserFeedbackImages(JSONObject jsonObject);

    /**
     * @Description 查询UserFeedbackImages
     * @Author taoye
     */
    ResponseJson getUserFeedbackImages(JSONObject jsonObject);
}