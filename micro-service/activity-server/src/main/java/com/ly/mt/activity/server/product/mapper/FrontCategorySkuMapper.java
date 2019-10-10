package com.ly.mt.activity.server.product.mapper;


import com.ly.mt.core.common.entity.hd.FrontCategorySku;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FrontCategorySkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FrontCategorySku record);

    int insertSelective(FrontCategorySku record);

    FrontCategorySku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FrontCategorySku record);

    int updateByPrimaryKey(FrontCategorySku record);
}