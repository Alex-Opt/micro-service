package com.ly.mt.activity.server.wechat.mapper;

import com.ly.mt.core.common.entity.hd.ActivityCouponCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ActivityCouponCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityCouponCode record);

    int insertSelective(ActivityCouponCode record);

    int updateByPrimaryKeySelective(ActivityCouponCode record);

    int updateByPrimaryKey(ActivityCouponCode record);

    @Select("SELECT * FROM activity_coupon_code as t1 WHERE status = '1' and t1.id>=(RAND()*(SELECT MAX(id) FROM activity_coupon_code))LIMIT 1")
    ActivityCouponCode findRandomByStatusOk();
}