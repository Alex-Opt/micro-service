package com.ly.mt.order.server.configs.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfigsMapper {

    List<JSONObject> getConfigs();

}
