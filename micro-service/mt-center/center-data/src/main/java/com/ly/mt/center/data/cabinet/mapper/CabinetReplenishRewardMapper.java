package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetReplenishReward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 个人奖励汇总持久层
 * @author zhanglifeng
 * @date 2019-08-28
 */
@Mapper
public interface CabinetReplenishRewardMapper {
    /**
     * 插入一条数据
     * @param cabinetReplenishReward
     * @return
     */
    int insertOne(CabinetReplenishReward cabinetReplenishReward);

    /**
     * 查询用户的奖励信息,分类型为一条数据，不分就是大于一条记录
     * @param user_id
     * @param type 奖励类型 不传就是部分类型，全部查询出来
     * @return
     */
    List<CabinetReplenishReward> queryUserCabinetRewardByUserId(Long user_id,Integer type);

    /**
     * 根据主键id查询一条奖励数据
     * @param id
     * @return
     */
    CabinetReplenishReward queryCabinetRewardById(Long id);


    /**
     * 根据id更新用户的奖励信息
     * @param cabinetReplenishReward
     * @return
     */
    int updateRewardById(CabinetReplenishReward cabinetReplenishReward);
}
