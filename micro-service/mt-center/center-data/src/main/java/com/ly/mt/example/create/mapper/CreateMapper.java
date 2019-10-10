package com.ly.mt.example.create.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CreateMapper {
    List<String> listTableName();

    List<Map<String, String>> getTableColumn(String tableName);
}