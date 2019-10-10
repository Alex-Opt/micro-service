package com.ly.mt.center.data.activity.mapper;

import com.ly.mt.center.data.activity.entity.ActivityGoodsDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityGoodsDetailMapper {
    /**
     * @Description 保存ActivityGoodsDetail
     * @Author taoye
     */
    void insertActivityGoodsDetail(ActivityGoodsDetail activityGoodsDetail);

    /**
     * @Description 删除ActivityGoodsDetail
     * @Author taoye
     */
    void deleteActivityGoodsDetail(ActivityGoodsDetail activityGoodsDetail);

    /**
     * @Description 更新ActivityGoodsDetail
     * @Author taoye
     */
    int updateActivityGoodsDetail(ActivityGoodsDetail activityGoodsDetail);

    /**
     * @Description 查询ActivityGoodsDetail
     * @Author taoye
     */
    ActivityGoodsDetail getActivityGoodsDetail(ActivityGoodsDetail activityGoodsDetail);
}