package com.ly.mt.order.server.activity.mapper;


import com.ly.mt.core.base.entity.activity.ActivityUserGradeDetail;
import com.ly.mt.core.base.entity.point.PointGrade;
import com.ly.mt.core.base.entity.user.UserPointGrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityUserGradeDetailServiceMapper {

    int insert(ActivityUserGradeDetail record);

    ActivityUserGradeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ActivityUserGradeDetail record);

    int batchInsertActivityUserGradeDetail(@Param("list") List<ActivityUserGradeDetail> list);

    /**
     * 根据用户id获取用户等级
     * @param userId
     * @return
     */
    UserPointGrade getUserGradeByUserId(@Param("userId") Long userId);

    /**
     * 查询用户等级对应的等级信息
     * @param gradId
     * @return
     */
    PointGrade getUserGradeNameByGradId(@Param("gradId") String gradId);
}