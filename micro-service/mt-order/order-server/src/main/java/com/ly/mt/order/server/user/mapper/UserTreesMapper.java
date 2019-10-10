package com.ly.mt.order.server.user.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTreesMapper {
    JSONObject getByUserId(Long userId);
}
