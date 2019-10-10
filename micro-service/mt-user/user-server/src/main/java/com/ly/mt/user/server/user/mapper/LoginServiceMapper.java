package com.ly.mt.user.server.user.mapper;

import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.entity.user.UserLoginVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginServiceMapper {
    /**
     * @Description 查询用户信息
     * @Author taoye
     */
    User getUser(UserLoginVo user);
}