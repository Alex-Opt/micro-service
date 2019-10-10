package com.ly.mt.order.server.user.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserProfitLogsMapper {
   int addBatchUserProfitLogs(List<JSONObject> jsonObjectList);
}
