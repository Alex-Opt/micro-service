package com.ly.mt.activity.server.wechat.mapper;

import com.ly.mt.core.common.entity.hd.ActivityCouponCodeToUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityCouponCodeToUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityCouponCodeToUser record);

    int insertSelective(ActivityCouponCodeToUser record);

    ActivityCouponCodeToUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityCouponCodeToUser record);

    int updateByPrimaryKey(ActivityCouponCodeToUser record);

    @Select("SELECT * FROM activity_coupon_code_to_user WHERE activityId = #{activityId} and user_id = #{helpMasterId}")
    List<ActivityCouponCodeToUser> findByUserIdActivityId(@Param("activityId") Long activityId, @Param("helpMasterId") Long helpMasterId);

    @Select("select code from activity_coupon_code_to_user WHERE activityId = #{activityId} and  open_id = #{openId}")
    List<String> findByActivityOpenid(@Param("activityId") Long activityId,@Param("openId") String openId);
}