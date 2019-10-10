package com.ly.mt.center.data.profits.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ProfitsUserProfitsService {
    /**
    * @Description: 查询赚钱详情
    * @Author:         zhuyh
    */
    ResponseJson getProfitsDetails(JSONObject jsonObject);
}
