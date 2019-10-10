package com.ly.mt.activity.server.product.mapper;

import com.ly.mt.core.common.entity.hd.FrontCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FrontCategoryMapper {
    int insert(FrontCategory record);
}