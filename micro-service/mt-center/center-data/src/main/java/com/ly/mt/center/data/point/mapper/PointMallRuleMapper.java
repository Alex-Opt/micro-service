package com.ly.mt.center.data.point.mapper;

import com.ly.mt.center.data.point.entity.PointMallRule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointMallRuleMapper {
    /**
     * @Description 保存PointMallRule
     * @Author taoye
     */
    void insertPointMallRule(PointMallRule pointMallRule);

    /**
     * @Description 删除PointMallRule
     * @Author taoye
     */
    void deletePointMallRule(PointMallRule pointMallRule);

    /**
     * @Description 更新PointMallRule
     * @Author taoye
     */
    int updatePointMallRule(PointMallRule pointMallRule);

    /**
     * @Description 查询PointMallRule
     * @Author taoye
     */
    PointMallRule getPointMallRule(PointMallRule pointMallRule);
}