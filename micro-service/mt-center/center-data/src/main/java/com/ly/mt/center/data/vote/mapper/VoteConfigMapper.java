package com.ly.mt.center.data.vote.mapper;

import com.ly.mt.center.data.vote.entity.VoteConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoteConfigMapper {
    /**
     * @Description 保存VoteConfig
     * @Author taoye
     */
    void insertVoteConfig(VoteConfig voteConfig);

    /**
     * @Description 删除VoteConfig
     * @Author taoye
     */
    void deleteVoteConfig(VoteConfig voteConfig);

    /**
     * @Description 更新VoteConfig
     * @Author taoye
     */
    int updateVoteConfig(VoteConfig voteConfig);

    /**
     * @Description 查询VoteConfig
     * @Author taoye
     */
    VoteConfig getVoteConfig(VoteConfig voteConfig);
}