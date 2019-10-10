package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserPointGrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPointGradeMapper {
    /**
     * @Description 插入UserPointGrade
     * @Author taoye
     */
    void insertUserPointGrade(UserPointGrade userPointGrade);

    /**
     * @Description 根据id删除UserPointGrade
     * @Author taoye
     */
    void deleteUserPointGradeById(UserPointGrade userPointGrade);

    /**
     * @Description 根据id更新UserPointGrade
     * @Author taoye
     */
    int updateUserPointGradeById(UserPointGrade userPointGrade);

    /**
     * @Description 根据条件查询UserPointGrade
     * @Author taoye
     */
    UserPointGrade getUserPointGrade(UserPointGrade userPointGrade);

    /**
     * @Description 根据id查询UserPointGrade
     * @Author taoye
     */
    UserPointGrade getUserPointGradeById(UserPointGrade userPointGrade);

    /**
     * 根据userId查询用户打卡积分
     * @param userPointGrade
     * @return
     */
    UserPointGrade getUserPointGradeByUserId(UserPointGrade userPointGrade);
}