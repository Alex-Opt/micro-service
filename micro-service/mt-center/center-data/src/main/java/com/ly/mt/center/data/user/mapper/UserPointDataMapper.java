package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserPointData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPointDataMapper {
    /**
     * @Description 插入UserPointData
     * @Author taoye
     */
    void insertUserPointData(UserPointData userPointData);

    /**
     * @Description 根据id删除UserPointData
     * @Author taoye
     */
    void deleteUserPointDataById(UserPointData userPointData);

    /**
     * @Description 根据id更新UserPointData
     * @Author taoye
     */
    int updateUserPointDataById(UserPointData userPointData);

    /**
     * @Description 根据条件查询UserPointData
     * @Author taoye
     */
    UserPointData getUserPointData(UserPointData userPointData);

    /**
     * @Description 根据id查询UserPointData
     * @Author taoye
     */
    UserPointData getUserPointDataById(UserPointData userPointData);

    /**
     * 根据用户id查询用户的打卡记录数据
     * @param userPointData
     * @return
     */
    List<UserPointData> getUserPointDataByUserId(UserPointData userPointData);

    /**
     * 根据用户id查询用户今天的打卡记录数据
     * @param
     * @return
     */
    UserPointData getUserPointDataByUserIdAndToday(@Param("userId") String userId,@Param("todayStartTime") String todayStartTime,@Param("todayEndTime") String todayEndTime);
}