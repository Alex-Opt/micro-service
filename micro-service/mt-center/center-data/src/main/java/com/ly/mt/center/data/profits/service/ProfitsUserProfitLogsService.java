package com.ly.mt.center.data.profits.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
* @Description: 赚钱-用户赚钱日志
* @Author:         zhuyh
*/
public interface ProfitsUserProfitLogsService {

    /**
    * @Description: 获取赚钱日志记录
    * @Author:         zhuyh
    */
    ResponseJson getLogs(JSONObject object);

    /**
    * @Description: 查询赚钱的排行
    * @Author:         zhuyh
    */
    ResponseJson getFriednsOrderRank(JSONObject object);
}
