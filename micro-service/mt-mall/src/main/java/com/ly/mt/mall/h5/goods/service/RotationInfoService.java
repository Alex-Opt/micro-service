package com.ly.mt.mall.h5.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 轮播图业务层
 */
public interface RotationInfoService {

    /**
     * 查询轮播图列表数据
     *
     * @param
     * @return
     * @throws Exception
     */
    ResponseJson queryRotationInfoList() throws Exception;

    /**
     * 添加轮播图数据
     *
     * @return
     * @throws Exception
     */
    ResponseJson rotationInfoSave(String json) throws Exception;

}
