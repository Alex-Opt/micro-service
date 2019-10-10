package com.ly.mt.user.server.user.mapper;

import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.entity.user.UserActivityH5RegistVo;
import com.ly.mt.core.common.entity.user.UserAddressActivityH5RegistVo;
import com.ly.mt.core.common.entity.user.UserMH5RegistVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserServiceMapper {
    /**
     * @Description 根据用户名/手机号获取用户信息
     * @Author taoye
     */
    User getUser(User user);

    /**
     * @Description M端H5商城注册
     * @Author taoye
     */
    void mH5Regist(UserMH5RegistVo user);

    /**
     * @Description 活动H5页面注册-保存用户信息
     * @Author taoye
     */
    void activityH5Regist(UserActivityH5RegistVo user);

    /**
     * @Description 活动H5页面注册-保存用户地址信息
     * @Author taoye
     */
    void activityH5RegistAddress(UserAddressActivityH5RegistVo address);
}