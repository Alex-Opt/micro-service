package com.ly.mt.core.data.activity.mapper;

import com.ly.mt.core.data.activity.entity.ActivitySecurityCode;
import org.apache.ibatis.annotations.Mapper;

/**
 * ActivitySecurityCode操作接口
 *
 * @author taoye
 */
@Mapper
public interface ActivitySecurityCodeMapper {
    /**
     * 新增ActivitySecurityCode
     *
     * @param activitySecurityCode 新增数据
     * @author taoye
     */
    void insertActivitySecurityCode(ActivitySecurityCode activitySecurityCode);

    /**
     * 查询ActivitySecurityCode
     *
     * @param activitySecurityCode 查询条件
     * @return ActivitySecurityCode
     * @author taoye
     */
    ActivitySecurityCode getActivitySecurityCode(ActivitySecurityCode activitySecurityCode);
}