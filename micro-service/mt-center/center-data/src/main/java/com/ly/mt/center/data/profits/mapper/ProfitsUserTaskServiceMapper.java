package com.ly.mt.center.data.profits.mapper;

import com.ly.mt.center.data.profits.entity.ProfitsUserTask;
import com.ly.mt.center.data.profits.entity.ProfitsUserTaskParamVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfitsUserTaskServiceMapper {

    /**
    * @Description: 查询某人未完成的任务
    * @Author:         zhuyh
    */
    List<ProfitsUserTask> selectNotFinishTaskByUId(ProfitsUserTaskParamVo vo);
}
