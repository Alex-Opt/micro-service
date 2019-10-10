package com.ly.mt.center.data.point.mapper;

import com.ly.mt.center.data.point.entity.PointMallRuleDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointMallRuleDetailMapper {
    /**
     * @Description 保存PointMallRuleDetail
     * @Author taoye
     */
    void insertPointMallRuleDetail(PointMallRuleDetail pointMallRuleDetail);

    /**
     * @Description 删除PointMallRuleDetail
     * @Author taoye
     */
    void deletePointMallRuleDetail(PointMallRuleDetail pointMallRuleDetail);

    /**
     * @Description 更新PointMallRuleDetail
     * @Author taoye
     */
    int updatePointMallRuleDetail(PointMallRuleDetail pointMallRuleDetail);

    /**
     * @Description 查询PointMallRuleDetail
     * @Author taoye
     */
    PointMallRuleDetail getPointMallRuleDetail(PointMallRuleDetail pointMallRuleDetail);
}