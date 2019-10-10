package com.ly.mt.center.data.activity.mapper;

import com.ly.mt.center.data.activity.entity.ActivitySignUpCabinet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivitySignUpCabinetMapper {
    /**
     * @Description 保存ActivitySignUpCabinet
     * @Author taoye
     */
    void insertActivitySignUpCabinet(ActivitySignUpCabinet activitySignUpCabinet);

    /**
     * @Description 删除ActivitySignUpCabinet
     * @Author taoye
     */
    void deleteActivitySignUpCabinet(ActivitySignUpCabinet activitySignUpCabinet);

    /**
     * @Description 更新ActivitySignUpCabinet
     * @Author taoye
     */
    int updateActivitySignUpCabinet(ActivitySignUpCabinet activitySignUpCabinet);

    /**
     * @Description 查询ActivitySignUpCabinet
     * @Author taoye
     */
    ActivitySignUpCabinet getActivitySignUpCabinet(ActivitySignUpCabinet activitySignUpCabinet);
}