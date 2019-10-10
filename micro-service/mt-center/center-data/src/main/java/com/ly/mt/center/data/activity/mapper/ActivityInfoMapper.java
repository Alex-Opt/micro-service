package com.ly.mt.center.data.activity.mapper;

import com.ly.mt.center.data.activity.entity.ActivityInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityInfoMapper {
    /**
     * @Description 保存ActivityInfo
     * @Author taoye
     */
    void insertActivityInfo(ActivityInfo activityInfo);

    /**
     * @Description 删除ActivityInfo
     * @Author taoye
     */
    void deleteActivityInfo(ActivityInfo activityInfo);

    /**
     * @Description 更新ActivityInfo
     * @Author taoye
     */
    int updateActivityInfo(ActivityInfo activityInfo);

    /**
     * @Description 查询ActivityInfo
     * @Author taoye
     */
    ActivityInfo getActivityInfo(ActivityInfo activityInfo);

    /**
     * @Description 根据spuId查询优惠活动数据
     * @Author xinguangzhi
     */
    Object getActivityInfoBySpuId(String spuId);
}