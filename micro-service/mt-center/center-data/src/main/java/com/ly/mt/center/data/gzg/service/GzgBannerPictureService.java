package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgBannerPictureService {
    /**
     * @Description 保存GzgBannerPicture
     * @Author taoye
     */
    ResponseJson insertGzgBannerPicture(JSONObject jsonObject);

    /**
     * @Description 删除GzgBannerPicture
     * @Author taoye
     */
    ResponseJson deleteGzgBannerPicture(JSONObject jsonObject);

    /**
     * @Description 更新GzgBannerPicture
     * @Author taoye
     */
    ResponseJson updateGzgBannerPicture(JSONObject jsonObject);

    /**
     * @Description 查询GzgBannerPicture
     * @Author taoye
     */
    ResponseJson getGzgBannerPicture(JSONObject jsonObject);
}