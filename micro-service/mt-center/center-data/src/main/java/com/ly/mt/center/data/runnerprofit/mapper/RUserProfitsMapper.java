package com.ly.mt.center.data.runnerprofit.mapper;


import com.ly.mt.center.data.runnerprofit.entity.RUserProfits;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface RUserProfitsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RUserProfits record);

    int insertSelective(RUserProfits record);

    RUserProfits selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RUserProfits record);

    int updateByPrimaryKey(RUserProfits record);

    /**
     * 批量插入或者更新赚钱人受益
     * TODO  目前没加锁配置先不这么玩
     * @param list
     * @return
     */
    @Deprecated
    Integer insertOrUpdateUserProfits(@Param("list") List<RUserProfits> list);

    /**
     * 插入或者更新赚钱人受益
     * @param rUserProfits
     * @return
     */
    Integer insertOrUpdateUserProfit(@Param("rUserProfits") RUserProfits rUserProfits);

}