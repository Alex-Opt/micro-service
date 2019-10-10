package com.ly.mt.center.data.activity.mapper;

import com.ly.mt.center.data.activity.entity.ActivityUserGradeDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityUserGradeDetailMapper {
    /**
     * @Description 保存ActivityUserGradeDetail
     * @Author taoye
     */
    void insertActivityUserGradeDetail(ActivityUserGradeDetail activityUserGradeDetail);

    /**
     * @Description 删除ActivityUserGradeDetail
     * @Author taoye
     */
    void deleteActivityUserGradeDetail(ActivityUserGradeDetail activityUserGradeDetail);

    /**
     * @Description 更新ActivityUserGradeDetail
     * @Author taoye
     */
    int updateActivityUserGradeDetail(ActivityUserGradeDetail activityUserGradeDetail);

    /**
     * @Description 查询ActivityUserGradeDetail
     * @Author taoye
     */
    ActivityUserGradeDetail getActivityUserGradeDetail(ActivityUserGradeDetail activityUserGradeDetail);
}