package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgSkuPictureService {
    /**
     * @Description 保存GzgSkuPicture
     * @Author taoye
     */
    ResponseJson insertGzgSkuPicture(JSONObject jsonObject);

    /**
     * @Description 删除GzgSkuPicture
     * @Author taoye
     */
    ResponseJson deleteGzgSkuPicture(JSONObject jsonObject);

    /**
     * @Description 更新GzgSkuPicture
     * @Author taoye
     */
    ResponseJson updateGzgSkuPicture(JSONObject jsonObject);

    /**
     * @Description 查询GzgSkuPicture
     * @Author taoye
     */
    ResponseJson getGzgSkuPicture(JSONObject jsonObject);
}