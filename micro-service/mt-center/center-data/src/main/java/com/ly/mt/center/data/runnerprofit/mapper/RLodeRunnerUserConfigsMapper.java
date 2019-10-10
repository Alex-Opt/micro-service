package com.ly.mt.center.data.runnerprofit.mapper;


import com.ly.mt.center.data.runnerprofit.entity.RLodeRunnerUserConfigs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RLodeRunnerUserConfigsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RLodeRunnerUserConfigs record);

    int insertSelective(RLodeRunnerUserConfigs record);

    RLodeRunnerUserConfigs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RLodeRunnerUserConfigs record);

    int updateByPrimaryKey(RLodeRunnerUserConfigs record);

    List<RLodeRunnerUserConfigs> findAll();
}