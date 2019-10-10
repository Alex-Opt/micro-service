package com.ly.mt.center.data.vote.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface VoteCityService {
    /**
     * @Description 保存VoteCity
     * @Author taoye
     */
    ResponseJson insertVoteCity(JSONObject jsonObject);

    /**
     * @Description 删除VoteCity
     * @Author taoye
     */
    ResponseJson deleteVoteCity(JSONObject jsonObject);

    /**
     * @Description 更新VoteCity
     * @Author taoye
     */
    ResponseJson updateVoteCity(JSONObject jsonObject);

    /**
     * @Description 查询VoteCity
     * @Author taoye
     */
    ResponseJson getVoteCity(JSONObject jsonObject);

    /**
     * 添加投票信息
     * @param  jsonObject  key=voteCity  {@link com.ly.mt.center.data.vote.entity.VoteCity}
     * @return
     */
    ResponseJson insert(JSONObject jsonObject);


    /**
     * 查询一小时达
     * 根据主键id
     * @param jsonObject key="userId" {@link Long} 用户id
     *                   key="areaId" {@link Long} 区域id
     * @return
     */
    ResponseJson find(JSONObject jsonObject);


    /**
     * 查询投票数
     * @param jsonObject  key="areaId" {@link Long} 区域id
     * @return
     */
    ResponseJson findCount(JSONObject jsonObject);


}
