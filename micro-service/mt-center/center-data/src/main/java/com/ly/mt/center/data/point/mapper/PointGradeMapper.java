package com.ly.mt.center.data.point.mapper;

import com.ly.mt.center.data.point.entity.PointGrade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointGradeMapper {
    /**
     * @Description 插入PointGrade
     * @Author taoye
     */
    void insertPointGrade(PointGrade pointGrade);

    /**
     * @Description 根据id删除PointGrade
     * @Author taoye
     */
    void deletePointGradeById(PointGrade pointGrade);

    /**
     * @Description 根据id更新PointGrade
     * @Author taoye
     */
    int updatePointGradeById(PointGrade pointGrade);

    /**
     * @Description 根据条件查询PointGrade
     * @Author taoye
     */
    PointGrade getPointGrade(PointGrade pointGrade);

    /**
     * @Description 根据id查询PointGrade
     * @Author taoye
     */
    PointGrade getPointGradeById(PointGrade pointGrade);

    /**
     * 根据score查询积分等级数据
     * @param pointGrade
     * @return
     */
    List<PointGrade> getPointGradeByScore(PointGrade pointGrade);

}