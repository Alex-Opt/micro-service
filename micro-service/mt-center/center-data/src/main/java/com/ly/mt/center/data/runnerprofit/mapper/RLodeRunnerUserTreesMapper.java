package com.ly.mt.center.data.runnerprofit.mapper;


import com.ly.mt.center.data.runnerprofit.entity.RLodeRunnerUserTrees;
import com.ly.mt.center.data.runnerprofit.entity.dto.RSimpRunnerTreesDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RLodeRunnerUserTreesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RLodeRunnerUserTrees record);

    int insertSelective(RLodeRunnerUserTrees record);

    RLodeRunnerUserTrees selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RLodeRunnerUserTrees record);

    int updateByPrimaryKey(RLodeRunnerUserTrees record);

    List<RSimpRunnerTreesDto> findRunnerTressByUserId(@Param("userId") Long userId, @Param("threadName") String threadName);
}