package com.ly.mt.center.data.runnerprofit.mapper;


import com.ly.mt.center.data.runnerprofit.entity.RUserProfitLogs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface RUserProfitLogsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RUserProfitLogs record);

    int inserts(@Param("list") List<RUserProfitLogs> logsList);

    int insertSelective(RUserProfitLogs record);

    RUserProfitLogs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RUserProfitLogs record);

    int updateByPrimaryKey(RUserProfitLogs record);

}