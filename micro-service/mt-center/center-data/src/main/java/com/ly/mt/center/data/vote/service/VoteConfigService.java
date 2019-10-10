package com.ly.mt.center.data.vote.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface VoteConfigService {
    /**
     * @Description 保存VoteConfig
     * @Author taoye
     */
    ResponseJson insertVoteConfig(JSONObject jsonObject);

    /**
     * @Description 删除VoteConfig
     * @Author taoye
     */
    ResponseJson deleteVoteConfig(JSONObject jsonObject);

    /**
     * @Description 更新VoteConfig
     * @Author taoye
     */
    ResponseJson updateVoteConfig(JSONObject jsonObject);

    /**
     * @Description 查询VoteConfig
     * @Author taoye
     */
    ResponseJson getVoteConfig(JSONObject jsonObject);
}