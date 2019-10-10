package com.ly.mt.order.server.user.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserProfitsMapper {
    JSONObject getByUserId(@Param("userId") Long userId);

    int updateRefundProfits(JSONObject jsonObject);

    JSONObject getUserById(@Param("userId")Long userId);
}
