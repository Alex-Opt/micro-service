package com.ly.mt.center.data.hd.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface HdOperatorInfoService {
    /**
     * @Description 保存HdOperatorInfo
     * @Author taoye
     */
    ResponseJson insertHdOperatorInfo(JSONObject jsonObject);

    /**
     * @Description 删除HdOperatorInfo
     * @Author taoye
     */
    ResponseJson deleteHdOperatorInfo(JSONObject jsonObject);

    /**
     * @Description 更新HdOperatorInfo
     * @Author taoye
     */
    ResponseJson updateHdOperatorInfo(JSONObject jsonObject);

    /**
     * @Description 查询HdOperatorInfo
     * @Author taoye
     */
    ResponseJson getHdOperatorInfo(JSONObject jsonObject);
}