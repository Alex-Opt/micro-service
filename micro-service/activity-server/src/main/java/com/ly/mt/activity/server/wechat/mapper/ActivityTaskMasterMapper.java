package com.ly.mt.activity.server.wechat.mapper;

import com.ly.mt.core.common.entity.hd.ActivityTaskMaster;
import com.ly.mt.core.common.entity.hd.dto.ActivityTaskMasterDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityTaskMasterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityTaskMaster record);

    int insertSelective(ActivityTaskMaster record);

    int updateByPrimaryKeySelective(ActivityTaskMaster record);

    int updateByPrimaryKey(ActivityTaskMaster record);

    /**
     * 根据活动id查询全部任务信息
     * @param activityId
     * @return
     */
    @Select("SELECT * FROM activity_task_master WHERE activity_id = #{activityId}")
    List<ActivityTaskMasterDto> findTasks(@Param("activityId") Long activityId);

    @Select("SELECT * FROM activity_task_master WHERE id = #{id}")
    ActivityTaskMasterDto findById(@Param("id") Long taskMasterId);
}