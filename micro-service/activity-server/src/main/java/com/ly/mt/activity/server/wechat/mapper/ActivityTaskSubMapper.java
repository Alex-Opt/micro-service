package com.ly.mt.activity.server.wechat.mapper;

import com.ly.mt.core.common.entity.hd.ActivityTaskSub;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityTaskSubMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityTaskSub record);

    int insertSelective(ActivityTaskSub record);

    int updateByPrimaryKeySelective(ActivityTaskSub record);

    int updateByPrimaryKeyWithBLOBs(ActivityTaskSub record);

    int updateByPrimaryKey(ActivityTaskSub record);

    @Select("SELECT * from activity_task_sub WHERE task_id = #{taskMasterId}")
    List<ActivityTaskSub> findByTaskMasterId(@Param("taskMasterId") Long taskMasterId);
}