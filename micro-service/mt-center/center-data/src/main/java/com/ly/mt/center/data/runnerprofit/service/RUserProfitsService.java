package com.ly.mt.center.data.runnerprofit.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.runnerprofit.entity.dto.RUserProfitLogsDto;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author panjingtian
 * @description
 * 到家c端赚钱人受益操作
 *
 * 管理赚钱人日志
 * 管理赚钱人受益信息
 *
 * @date 2019/6/28 3:53 PM
 */
public interface RUserProfitsService {


    /**
     *
     * 受益日志入库，更新受益总汇
     * @param jsonObject {@link RUserProfitLogsDto} key userProfitLogsDto
     * @return
     */
    ResponseJson insterProfitsLogsAndProfits(JSONObject jsonObject);

}
