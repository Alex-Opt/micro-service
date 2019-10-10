package com.ly.mt.core.data.activity.dao;

import com.ly.mt.core.data.activity.entity.ActivitySecurityCode;

/**
 * ActivitySecurityCode操作接口
 *
 * @author taoye
 */
public interface ActivitySecurityCodeDao {
    /**
     * 新增防伪码校验记录
     *
     * @param activitySecurityCode 新增数据
     * @author taoye
     */
    void insertActivitySecurityCode(ActivitySecurityCode activitySecurityCode);

    /**
     * 根据防伪码查询防伪码校验记录
     *
     * @param securityCode 防伪码
     * @return ActivitySecurityCode
     * @author taoye
     */
    ActivitySecurityCode getActivitySecurityCodeBySecurityCodeFromRedis(String securityCode);
}