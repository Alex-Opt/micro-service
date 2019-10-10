package com.ly.mt.center.data.activity.mapper;

import com.ly.mt.center.data.activity.entity.ActivityUserGrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityUserGradeMapper {
    /**
     * @Description 保存ActivityUserGrade
     * @Author taoye
     */
    void insertActivityUserGrade(ActivityUserGrade activityUserGrade);

    /**
     * @Description 删除ActivityUserGrade
     * @Author taoye
     */
    void deleteActivityUserGrade(ActivityUserGrade activityUserGrade);

    /**
     * @Description 更新ActivityUserGrade
     * @Author taoye
     */
    int updateActivityUserGrade(ActivityUserGrade activityUserGrade);

    /**
     * @Description 查询ActivityUserGrade
     * @Author taoye
     */
    ActivityUserGrade getActivityUserGrade(ActivityUserGrade activityUserGrade);
}