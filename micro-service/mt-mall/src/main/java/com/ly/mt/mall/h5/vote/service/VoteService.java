package com.ly.mt.mall.h5.vote.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @description
 * 投票操作
 * @author panjingtian
 * @date 2019/7/4 2:54 PM
 */
public interface VoteService {


    /**
     * 查询是否可以投票
     * @param userId
     * @param areaId
     * @return 0 不可以 1可以
     */
    public ResponseJson ifVote(Long userId,Long areaId);


    /**
     * 进行投票
     * @param userId
     * @param areaId
     * @return
     */
    public ResponseJson vote(Long userId,Long areaId);


    /**
     * 查询区域投票数
     * 查询所有区域则 传0
     * @param areaId
     * @return
     */
    public ResponseJson findCount(Long areaId);

    /**
     * 查看是否开通了一小时达
     * @param areaId
     * @return areaOpen  true已经开通 false 未开通
     */
    public ResponseJson isOpenOneHourArea(Long areaId);


}
