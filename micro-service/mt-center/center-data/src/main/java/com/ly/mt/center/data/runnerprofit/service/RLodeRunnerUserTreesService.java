package com.ly.mt.center.data.runnerprofit.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.runnerprofit.entity.dto.RElasticLoadRunnerTreesDto;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author panjingtian
 * @description
 *
 * 到家c赚钱人操作
 *
 * @date 2019/6/28 3:47 PM
 */
public interface RLodeRunnerUserTreesService {


    /**
     * 查询五级别联系人
     *
     * @param jsonObject {@link String}  key userId
     * @return {@link RElasticLoadRunnerTreesDto}
     */
    ResponseJson fiveTrees(JSONObject jsonObject);
}
