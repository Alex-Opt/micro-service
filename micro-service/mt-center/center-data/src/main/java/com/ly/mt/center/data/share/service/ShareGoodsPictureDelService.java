package com.ly.mt.center.data.share.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShareGoodsPictureDelService {
    /**
     * @Description 保存ShareGoodsPictureDel
     * @Author taoye
     */
    ResponseJson insertShareGoodsPictureDel(JSONObject jsonObject);

    /**
     * @Description 删除ShareGoodsPictureDel
     * @Author taoye
     */
    ResponseJson deleteShareGoodsPictureDel(JSONObject jsonObject);

    /**
     * @Description 更新ShareGoodsPictureDel
     * @Author taoye
     */
    ResponseJson updateShareGoodsPictureDel(JSONObject jsonObject);

    /**
     * @Description 查询ShareGoodsPictureDel
     * @Author taoye
     */
    ResponseJson getShareGoodsPictureDel(JSONObject jsonObject);
}